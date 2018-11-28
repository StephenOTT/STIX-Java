package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.CourseOfActionProperties;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = CourseOfAction.Deserializer.class)
public class CourseOfAction extends CourseOfActionProperties implements StixDomainObject {

    public static final String TYPE = "course-of-action";

    public CourseOfAction(String name){
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
    }

    /**
     * Used for JSON Deserialization
     */
    private CourseOfAction() {
    }

    public static class Deserializer extends StdDeserializer<CourseOfAction> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public CourseOfAction deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            CourseOfAction object = new CourseOfAction();

            validateAllCommonProperties(node, jp, object, TYPE, false);

            Optional<JsonNode> name = Optional.ofNullable(node.get("name"));
            name.ifPresent(o -> {
                object.setName(o.textValue());
            });
            name.orElseThrow(() -> new IllegalArgumentException("Name is required"));

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
            });

            Optional<JsonNode> actions = Optional.ofNullable(node.get("action"));
            actions.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> actionsSet = new LinkedHashSet<>();
                    o.forEach(item -> {
                        actionsSet.add(item.asText());
                    });
                    object.setAction(actionsSet);
                } else {
                    throw new IllegalArgumentException("action must be a array of strings");
                }
            });

            return object;
        }
    }

}
