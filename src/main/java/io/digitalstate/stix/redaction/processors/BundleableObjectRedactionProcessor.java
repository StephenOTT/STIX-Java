package io.digitalstate.stix.redaction.processors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.datamarkings.GranularMarkingDm;
import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.datamarkings.StixMarkingObject;
import io.digitalstate.stix.redaction.Redactable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BundleableObjectRedactionProcessor {

    public static String processObject(BundleableObject bundleableObject, String jsonString, Set<StixMarkingObject> subjectsMarkingObjects) {

        String block = Character.toString((char) 0x2588);
        String redactedMaskValue = block + block + "REDACTED" + block + block;

        Set<MarkingDefinitionDm> markingDefinitionsSet = bundleableObject.getObjectMarkingRefs();

        if (!markingDefinitionsSet.isEmpty()) {
            // @TODO look into how to allow injection of annotaiton in multiple locations
//            Redactable classRedactable = Optional.ofNullable(bundleableObject.getClass().getDeclaredAnnotation(Redactable.class))
//                    .orElseThrow(IllegalStateException::new);

            Set<StixMarkingObject> objectMarkings = markingDefinitionsSet.stream()
                    .filter(md -> md.getDefinitionType().equals("tlp"))
                    .map(MarkingDefinitionDm::getDefinition)
                    .collect(Collectors.toSet());

            if (!subjectsMarkingObjects.containsAll(objectMarkings)) {
                return "{}";
            }
        }


        Set<GranularMarkingDm> granularMarkingDms = bundleableObject.getGranularMarkings();

        if (!granularMarkingDms.isEmpty()) {
            Configuration conf = Configuration.builder()
                    .options(Option.AS_PATH_LIST).build();
            DocumentContext doc = JsonPath.using(conf).parse(jsonString);

            Set<Method> getMethods = Arrays.stream(bundleableObject.getClass().getDeclaredMethods())
                    .filter(m -> m.getAnnotation(JsonProperty.class) != null)
                    .filter(m -> m.getName().startsWith("get"))
                    .collect(Collectors.toSet());
            System.out.println("getMethods: " + getMethods.toString());

            granularMarkingDms.forEach(gm -> {
                MarkingDefinitionDm markingDefinition = gm.getMarkingRef();
                StixMarkingObject markingObject = gm.getMarkingRef().getDefinition();
                Set<String> selectors = gm.getSelectors();

                if (markingDefinition.getDefinitionType().equals("tlp") && !subjectsMarkingObjects.contains(markingObject)) {
                    selectors.forEach(s -> {

                        // Parse the selectors into JsonPath
                        List<String> pathLists = doc.read("$." + s);
                        System.out.println("Parsed Selector Matches: " + pathLists.toString());


                        final String regex = "\\$\\['(.*?)'\\]";
                        final Pattern pattern = Pattern.compile(regex);

                        pathLists.forEach(path -> {
                            // @TODO refactor to support multiple levels of inner object redaction

                            Matcher jsonPathPropertyNameMatcher = pattern.matcher(path);
                            boolean matcherResult = jsonPathPropertyNameMatcher.find();
                            if (!matcherResult) {
                                throw new IllegalStateException("Cannot make a match / cannot parse the json path pattern");
                            } else if (jsonPathPropertyNameMatcher.groupCount() > 1) {
                                throw new IllegalStateException("Cannot make a match / Multiple Matches found");
                            }
                            String jsonPathPropertyName = jsonPathPropertyNameMatcher.group(1);


                            Method methodForPath = getMethods.stream()
                                    .filter(m -> m.getDeclaredAnnotation(JsonProperty.class).value().equals(jsonPathPropertyName))
                                    .findFirst().orElseThrow(IllegalStateException::new);
                            String jacksonPropertyName = methodForPath.getAnnotation(JsonProperty.class).value();

                            System.out.println("jsonPathName: " + jsonPathPropertyName);
                            System.out.println("JacksonPathname: " + jacksonPropertyName);

                            Redactable redactableAnn = Optional
                                    .ofNullable(methodForPath.getDeclaredAnnotation(Redactable.class))
                                    .orElseThrow(IllegalStateException::new);

                            // Redaction Logic:
                            if (redactableAnn.useMask()) {
                                doc.set(path, redactableAnn.redactionMask());
                            } else {
                                doc.delete(path);
                            }

                        });
                    });
                }
            });
            return doc.jsonString();
        }
        return jsonString;
    }
}
