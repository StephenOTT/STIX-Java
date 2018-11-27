package io.digitalstate.stix.datamarkings.definitions;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.datamarkings.StixDataMarking;
import io.digitalstate.stix.datamarkings.markingtypes.MarkingObjectType;

import java.io.IOException;

import static io.digitalstate.stix.datamarkings.definitions.json.CommonPropertiesDeserializationValidators.*;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = MarkingDefinition.Deserializer.class)
public class MarkingDefinition extends MarkingDefinitionProperties implements StixDataMarking {

    private static final String TYPE = "marking-definition";

    public MarkingDefinition(MarkingObjectType markingObjectType){
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setDefinition(markingObjectType);
        setDefinitionType(markingObjectType.getType());
    }

    /**
     * Used for JSON Deserialization
     */
    private MarkingDefinition() {
    }

    public static class Deserializer extends StdDeserializer<MarkingDefinition> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public MarkingDefinition deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            MarkingDefinition object = new MarkingDefinition();

            validateAllCommonProperties(node, jp, object, TYPE);

            return object;
        }
    }
}
