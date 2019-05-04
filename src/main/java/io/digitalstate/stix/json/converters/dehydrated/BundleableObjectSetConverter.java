package io.digitalstate.stix.json.converters.dehydrated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.json.StixParsers;

import java.util.HashSet;
import java.util.Set;

/**
 * Generates a Dehydrated Bundleable Object based on a ID from a Set of BundleableObjects.
 */
public class BundleableObjectSetConverter extends StdConverter<Set<String>, Set<BundleableObject>> {

    @Override
    public Set<BundleableObject> convert(Set<String> values) {
        Set<BundleableObject> bundleableObjectSet = new HashSet<>();
        values.forEach(v -> {
            String[] parsedValue = v.split("--");

            if (parsedValue.length == 2) {
                ObjectMapper mapper = StixParsers.getJsonMapper();
                ObjectNode node = mapper.createObjectNode();

                node.put("type", parsedValue[0]);
                node.put("id", v);
                node.put("hydrated", false);


                try {
                    BundleableObject bundleableObject = mapper.treeToValue(node, BundleableObject.class);
                    //@TODO add more logic
                    bundleableObjectSet.add(bundleableObject);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("Cannot Parse Json: " + e.getMessage());
                }

            } else {
                throw new IllegalArgumentException("Id is not valid format, Cannot Parse Value: " + v);
            }
        });
        return bundleableObjectSet;
    }
}