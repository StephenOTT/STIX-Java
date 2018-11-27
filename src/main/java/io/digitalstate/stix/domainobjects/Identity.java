package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.IdentityProperties;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = Identity.Deserializer.class)
public class Identity extends IdentityProperties implements StixDomainObject {

    private static final String TYPE = "identity";

    public Identity(String name, String identityClass){
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
        setIdentityClass(identityClass);
    }


    /**
     * Used for JSON Deserialization
     */
    private Identity() {
    }

    public static class Deserializer extends StdDeserializer<Identity> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Identity deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Identity object = new Identity();

            validateAllCommonProperties(node, object, TYPE, false);

            Optional<JsonNode> name = Optional.ofNullable(node.get("name"));
            name.ifPresent(o -> {
                object.setName(o.textValue());
            });
            name.orElseThrow(()-> new IllegalArgumentException("Name is required"));


            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
            });

            Optional<JsonNode> identityClass = Optional.ofNullable(node.get("identity_class"));
            identityClass.ifPresent(o -> {
                object.setIdentityClass(o.textValue());
            });
            identityClass.orElseThrow(() -> new IllegalArgumentException("identity_class is required"));

            Optional<JsonNode> sectors = Optional.ofNullable(node.get("sectors"));
            sectors.ifPresent(o -> {
                if (o.isArray()){
                    LinkedHashSet<String> industrySectors = new LinkedHashSet<>();
                    o.forEach(item->{
                        industrySectors.add(item.asText());
                    });
                    object.setSectors(industrySectors);
                } else {
                    throw new IllegalArgumentException("sectors must be a array of strings");
                }
            });

            Optional<JsonNode> contactInformation = Optional.ofNullable(node.get("contact_information"));
            contactInformation.ifPresent(o -> {
                object.setContactInformation(o.textValue());
            });

            return object;
        }
    }
}
