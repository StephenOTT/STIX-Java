package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.ThreatActorProperties;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = ThreatActor.Deserializer.class)
public class ThreatActor extends ThreatActorProperties implements StixDomainObject {

    private static final String TYPE = "threat-actor";

    public ThreatActor(String name,
                       LinkedHashSet<String> threatActorLabels){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
        setLabels(threatActorLabels);
    }
    public ThreatActor(String name, String... threatActorLabels){
        this(name, new LinkedHashSet<>(Arrays.asList(threatActorLabels)));
    }


    /**
     * Used for JSON Deserialization
     */
    private ThreatActor() {
    }

    public static class Deserializer extends StdDeserializer<ThreatActor> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public ThreatActor deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            ThreatActor object = new ThreatActor();

            validateAllCommonProperties(node, object, TYPE, true);

            Optional<JsonNode> name = Optional.ofNullable(node.get("name"));
            name.ifPresent(o -> {
                object.setName(o.textValue());
            });
            name.orElseThrow(() -> new IllegalArgumentException("Name is required"));

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
            });

            Optional<JsonNode> aliases = Optional.ofNullable(node.get("aliases"));
            aliases.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> aliasesSet = new LinkedHashSet<>();
                    o.forEach(item -> {
                        aliasesSet.add(item.asText());
                    });
                    object.setAliases(aliasesSet);
                } else {
                    throw new IllegalArgumentException("aliases must be a array of strings");
                }
            });

            Optional<JsonNode> roles = Optional.ofNullable(node.get("roles"));
            roles.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> rolesSet = new LinkedHashSet<>();
                    o.forEach(item -> {
                        rolesSet.add(item.asText());
                    });
                    object.setRoles(rolesSet);
                } else {
                    throw new IllegalArgumentException("roles must be a array of strings");
                }
            });

            Optional<JsonNode> goals = Optional.ofNullable(node.get("goals"));
            goals.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> goalsSet = new LinkedHashSet<>();
                    o.forEach(item -> {
                        goalsSet.add(item.asText());
                    });
                    object.setGoals(goalsSet);
                } else {
                    throw new IllegalArgumentException("goals must be a array of strings");
                }
            });

            Optional<JsonNode> sophistication = Optional.ofNullable(node.get("sophistication"));
            sophistication.ifPresent(o -> {
                object.setSophistication(o.asText());
            });

            Optional<JsonNode> resource_level = Optional.ofNullable(node.get("resource_level"));
            resource_level.ifPresent(o -> {
                object.setResourceLevel(o.asText());
            });

            Optional<JsonNode> primary_motivation = Optional.ofNullable(node.get("primary_motivation"));
            primary_motivation.ifPresent(o -> {
                object.setPrimaryMotivation(o.asText());
            });

            Optional<JsonNode> secondary_motivations = Optional.ofNullable(node.get("secondary_motivations"));
            secondary_motivations.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> secondaryMotivationsSet = new LinkedHashSet<>();
                    o.forEach(item -> {
                        secondaryMotivationsSet.add(item.asText());
                    });
                    object.setSecondaryMotivations(secondaryMotivationsSet);
                } else {
                    throw new IllegalArgumentException("secondary_motivations must be a array of strings");
                }
            });

            Optional<JsonNode> personal_motivations = Optional.ofNullable(node.get("personal_motivations"));
            personal_motivations.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> personalMotivationsSet = new LinkedHashSet<>();
                    o.forEach(item -> {
                        personalMotivationsSet.add(item.asText());
                    });
                    object.setPersonalMotivations(personalMotivationsSet);
                } else {
                    throw new IllegalArgumentException("personal_motivations must be a array of strings");
                }
            });

            return object;
        }
    }
}
