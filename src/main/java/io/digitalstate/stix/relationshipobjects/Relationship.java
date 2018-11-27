package io.digitalstate.stix.relationshipobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.relationshipobjects.properties.RelationshipProperties;

import java.io.IOException;
import java.util.Optional;

import static io.digitalstate.stix.relationshipobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

/**
 * Defines a Stix Relationship Object (SRO)
 * Has minimal or no business logic.  All logic is handled within the specific SDOs
 */
@JsonDeserialize(using = Relationship.Deserializer.class)
public class Relationship extends RelationshipProperties implements StixRelationshipObject{

    private static final String TYPE = "relationship";

    public Relationship(String relationshipType,
                 StixDomainObject source,
                 StixDomainObject target,
                 String description){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setRelationshipType(relationshipType);
        setSource(source);
        setTarget(target);
        setDescription(description);
    }

    public Relationship(String relationshipType,
                 StixDomainObject source,
                 StixDomainObject target){

        this(relationshipType, source, target, null);
    }


    /**
     * Used for JSON Deserialization
     */
    private Relationship() {
    }

    public static class Deserializer extends StdDeserializer<Relationship> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Relationship deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Relationship object = new Relationship();

            validateAllCommonProperties(node, object, TYPE, false);


            Optional<JsonNode> relationship_type = Optional.ofNullable(node.get("relationship_type"));
            relationship_type.ifPresent(o -> {
                object.setRelationshipType(o.textValue());
            });
            relationship_type.orElseThrow(() -> new IllegalArgumentException("relationship_type is Required"));

            Optional<JsonNode> source = Optional.ofNullable(node.get("source"));
            source.ifPresent(o -> {
                //@TODO
            });
            source.orElseThrow(() -> new IllegalArgumentException("source is Required"));

            Optional<JsonNode> target = Optional.ofNullable(node.get("target"));
            target.ifPresent(o -> {
                //@TODO
            });
            target.orElseThrow(() -> new IllegalArgumentException("target is Required"));

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
            });

            return object;
        }
    }
 }
