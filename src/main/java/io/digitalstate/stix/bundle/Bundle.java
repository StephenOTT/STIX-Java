package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.datamarkings.StixDataMarking;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.relationshipobjects.Sighting;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.io.IOException;
import java.util.*;

import static io.digitalstate.stix.helpers.IdGeneration.*;

/**
 * A Implementation of a {@link StixBundle} that follows the spec and provides helpers for auto-Bundle population.
 * The Auto-Bundle population will recursively look through a bundle's objects to find nested objects
 * that should be shown in the parent of the bundle.
 */
@JsonDeserialize(using = Bundle.Deserializer.class)
public class Bundle extends BundleProperties implements StixBundle {

    private final String TYPE = "bundle";

    public Bundle(LinkedHashSet<BundleObject> objects){
        setType(TYPE);
        setId(generateUuidAsString());
        setSpecVersion(StixSpecVersion.SPECVERSION);
        setObjects(objects);
    }

    public Bundle(BundleObject... objects){
        this(new LinkedHashSet<>(Arrays.asList(objects)));
    }

    public void autoDetectBundleObjects(){
        boolean noNewItems = true;

        while(noNewItems){
            LinkedHashSet<BundleObject> newItems = autoDectectObjectProcessor(getObjects());

            if (!newItems.isEmpty() && !getObjects().containsAll(newItems)){
                getObjects().addAll(newItems);

            } else {
                noNewItems = false;
            }
        }
    }

    private LinkedHashSet<BundleObject> autoDectectObjectProcessor(LinkedHashSet<BundleObject> objects){
        // !!!@TODO !!! Replace all LinkedHashSet Code with Lists
        // The LinkedHashSets have issues with iteration and transformation at same time
        // This has downstream.

        LinkedHashSet<BundleObject> temp = new LinkedHashSet<>();

        Iterator<BundleObject> iter = getObjects().iterator();
        while (iter.hasNext()){
            BundleObject object = iter.next();

            if (object instanceof StixDomainObject){
                temp.addAll(((StixDomainObject) object).getAllCommonPropertiesBundleObjects());
                temp.addAll(((StixDomainObject) object).getAllObjectSpecificBundleObjects());

            } else if (object instanceof StixDataMarking){
                temp.addAll(((StixDataMarking) object).getAllCommonPropertiesBundleObjects());

            } else if (object instanceof StixRelationshipObject){
                temp.addAll(((StixRelationshipObject) object).getAllCommonPropertiesBundleObjects());
                temp.addAll(((StixRelationshipObject) object).getAllObjectSpecificBundleObjects());
            }
        }

        temp.removeIf(Objects::isNull);

        return temp;
    }

    public void hydrateRelationsWithObjects(){
        LinkedHashSet<BundleObject> bundleObjects = this.getObjects();

        bundleObjects.forEach(bo->{
            if (bo instanceof StixRelationshipObject){
                ((StixRelationshipObject) bo).hydrateRelationsWithObjects(bundleObjects);
                //@TODO add rest of logic and remove the instanceof condition
            }
        });
    }

    public static Bundle parse(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        return StixDataFormats.getJsonMapper().readValue(jsonString, Bundle.class);
    }


    /**
     * Used for JSON Deserialization
     */
    private Bundle() {
    }

    public static class Deserializer extends StdDeserializer<Bundle> {

        private static HashMap<String, Class<? extends BundleObject>> bundleObjectClassMappings = new HashMap<>();

        public static HashMap<String, Class<? extends BundleObject>> getBundleObjectClassMappings() {
            return bundleObjectClassMappings;
        }
        public static void setBundleObjectClassMappings(HashMap<String, Class<? extends BundleObject>> bundleObjectClassMappings) {
            Deserializer.bundleObjectClassMappings = bundleObjectClassMappings;
        }

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Bundle deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Bundle object = new Bundle();

            validateType(node, "bundle", object);
            validateId(node, object);

            Optional<JsonNode> spec_version = Optional.ofNullable(node.get("spec_version"));
            spec_version.ifPresent(o -> {
                object.setSpecVersion(o.textValue());
            });
            spec_version.orElseThrow(()-> new IllegalArgumentException("spec_version is required"));

            Optional<JsonNode> bundleObjects = Optional.ofNullable(node.get("objects"));
            bundleObjects.ifPresent(o -> {
                LinkedHashSet<BundleObject> objectsInBundle = new LinkedHashSet<>();

                getBundleObjectClassMappings().put(AttackPattern.TYPE, AttackPattern.class);
                getBundleObjectClassMappings().put(Campaign.TYPE, Campaign.class);
                getBundleObjectClassMappings().put(CourseOfAction.TYPE, CourseOfAction.class);
                getBundleObjectClassMappings().put(Identity.TYPE, Identity.class);
                getBundleObjectClassMappings().put(Indicator.TYPE, Indicator.class);
                getBundleObjectClassMappings().put(IntrusionSet.TYPE, IntrusionSet.class);
                getBundleObjectClassMappings().put(Malware.TYPE, Malware.class);
                getBundleObjectClassMappings().put(ObservedData.TYPE, ObservedData.class);
                getBundleObjectClassMappings().put(Report.TYPE, Report.class);
                getBundleObjectClassMappings().put(ThreatActor.TYPE, ThreatActor.class);
                getBundleObjectClassMappings().put(Tool.TYPE, Tool.class);
                getBundleObjectClassMappings().put(Vulnerability.TYPE, Vulnerability.class);

                getBundleObjectClassMappings().put(Relationship.TYPE, Relationship.class);
                getBundleObjectClassMappings().put(Sighting.TYPE, Sighting.class);

                getBundleObjectClassMappings().put(MarkingDefinition.TYPE, MarkingDefinition.class);

                 if (o.isArray()){
                    o.forEach(bo->{
                        if (bo.hasNonNull("type")){
                            if (bundleObjectClassMappings.containsKey(bo.get("type").asText())){
                                String typeValue = bo.get("type").asText();

                                JsonParser objectParser = bo.traverse(jp.getCodec());

                                try {

                                    BundleObject parsedObject = objectParser.readValueAs(bundleObjectClassMappings.get(typeValue));
                                    objectsInBundle.add(parsedObject);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    throw new IllegalArgumentException("Unable to parse one of the objects");
                                }
                            }

                        } else {
                            throw new IllegalArgumentException("One of the objects does not have a type or type is null");
                        }
                        // Set the objects values of the bundle with the parsed items
                        object.setObjects(objectsInBundle);

                    });
                } else {
                    throw new IllegalArgumentException("objects property must be a array");
                }

            });
            bundleObjects.orElseThrow(()-> new IllegalArgumentException("objects is required"));
            // Return the parsed bundle
            return object;
        }

        public static Optional<JsonNode> validateType(JsonNode node, String requiredType, StixBundle object) throws IllegalArgumentException{
            Optional<JsonNode> type = Optional.ofNullable(node.get("type"));
            type.ifPresent(o -> {
                if (!o.isNull() && o.asText().equals(requiredType)) {
                    object.setType(o.asText());
                } else {
                    throw new IllegalArgumentException("Invalid Type: " + o.asText());
                }
            });
            type.orElseThrow(() -> new IllegalArgumentException("Type is Required"));
            return type;
        }

        /**
         * Validate JSON for id property
         * @param node
         * @param object
         * @return
         * @throws IllegalArgumentException
         */
        public static Optional<JsonNode> validateId(JsonNode node, StixBundle object) throws IllegalArgumentException{
            Optional<JsonNode> id = Optional.ofNullable(node.get("id"));
            id.ifPresent(o -> {
                if (!o.isNull()) {
                    object.setId(o.asText());
                } else {
                    throw new IllegalArgumentException("Invalid Id, if is null");
                }
            });
            id.orElseThrow(() -> new IllegalArgumentException("Id is Required"));

            return id;
        }
    }
}
