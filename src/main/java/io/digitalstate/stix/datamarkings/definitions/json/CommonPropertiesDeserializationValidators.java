package io.digitalstate.stix.datamarkings.definitions.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import io.digitalstate.stix.datamarkings.StixDataMarking;
import io.digitalstate.stix.datamarkings.markingtypes.StatementMarking;
import io.digitalstate.stix.datamarkings.markingtypes.TlpMarking;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

public class CommonPropertiesDeserializationValidators {

    /**
     *
     * @param node
     * @param jp
     * @param object
     * @param requiredType
     * @throws IllegalArgumentException
     */
    public static void validateAllCommonProperties(JsonNode node,
                                                   JsonParser jp,
                                                   StixDataMarking object,
                                                   String requiredType)
            throws IllegalArgumentException {

        validateType(node, requiredType, object);
        validateId(node, object);
        validateCreatedByRef(node, object);
        validateCreated(node, object);
        validateExternalRefs(node, object);
        validateObjectMarkingRefs(node, object);
        validateGranularMarkings(node, object);
        validateDefinitionType(node, object);
        validateDefinition(node, object, jp);
    }

    /**
     * Validate JSON for type property
     *
     * @param node
     * @param requiredType
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateType(JsonNode node, String requiredType, StixDataMarking object) throws IllegalArgumentException {
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
     *
     * @param node
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateId(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
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

    /**
     * Validate JSON for created_by_ref property
     *
     * @param node
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateCreatedByRef(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
        Optional<JsonNode> created_by_ref = Optional.ofNullable(node.get("created_by_ref"));
        created_by_ref.ifPresent(o -> {
            //@TODO
        });

        return created_by_ref;
    }

    /**
     * Validate JSON for Created property
     *
     * @param node
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateCreated(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
        Optional<JsonNode> created = Optional.ofNullable(node.get("created"));
        created.ifPresent(o -> {
            Instant instant = Instant.parse(o.asText());
            object.setCreated(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
        });
        created.orElseThrow(() -> new IllegalArgumentException("Created is Required"));

        return created;
    }


    /**
     * Validate External Refs JSON
     *
     * @param node
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateExternalRefs(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
        Optional<JsonNode> external_references = Optional.ofNullable(node.get("external_references"));
        external_references.ifPresent(o -> {
            //@TODO
        });
        return external_references;
    }

    /**
     * Validate Object Marking Refs JSON
     *
     * @param node
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateObjectMarkingRefs(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
        Optional<JsonNode> object_marking_refs = Optional.ofNullable(node.get("object_marking_refs"));
        object_marking_refs.ifPresent(o -> {
            //@TODO
        });
        return object_marking_refs;
    }

    /**
     * Validate Granular Markings JSON
     *
     * @param node
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public static Optional<JsonNode> validateGranularMarkings(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
        Optional<JsonNode> granular_markings = Optional.ofNullable(node.get("granular_markings"));
        granular_markings.ifPresent(o -> {
            //@TODO
        });
        return granular_markings;
    }

    public static Optional<JsonNode> validateDefinitionType(JsonNode node, StixDataMarking object) throws IllegalArgumentException {
        Optional<JsonNode> type = Optional.ofNullable(node.get("definition_type"));
        type.ifPresent(o -> {
                object.setDefinitionType(o.asText());
        });
        type.orElseThrow(() -> new IllegalArgumentException("definition_type is Required"));
        return type;
    }

    public static Optional<JsonNode> validateDefinition(JsonNode node, StixDataMarking object, JsonParser jp) throws IllegalArgumentException {
        Optional<JsonNode> definition = Optional.ofNullable(node.get("definition"));
        definition.ifPresent(def -> {
            if (def.isObject()) {
                // Traverse the object to create a new parser
                JsonParser definitionParser = def.traverse();
                definitionParser.setCodec(jp.getCodec());

                try {
                    //@TODO Rebuild to be dynamic
                    // Currently this is hardcoded for only two types of definitions
                    if (node.has("definition_type")){
                        String definitionType = node.get("definition_type").asText();

                        switch (definitionType) {
                            case "tlp":
                                object.setDefinition(definitionParser.readValueAs(TlpMarking.class));
                                break;

                            case "statement":
                                object.setDefinition(definitionParser.readValueAs(StatementMarking.class));
                                break;

                            default:
                                throw new IllegalArgumentException("definition type is not valid");
                        }
                    } else {
                        throw new IllegalArgumentException("unable to read type value for definition object");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException("Unable to parse the definition");
                }

            } else {
                throw new IllegalArgumentException("definition must be object");
            }
        });

        return definition;
    }
}
