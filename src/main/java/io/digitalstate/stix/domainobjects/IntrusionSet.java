package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.IntrusionSetProperties;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = IntrusionSet.Deserializer.class)
public class IntrusionSet extends IntrusionSetProperties implements StixDomainObject {

    public static final String TYPE = "intrusion-set";

    public IntrusionSet(String name) {
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
    }


    /**
     * Used for JSON Deserialization
     */
    private IntrusionSet() {
    }

    public static class Deserializer extends StdDeserializer<IntrusionSet> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public IntrusionSet deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            IntrusionSet object = new IntrusionSet();

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

            Optional<JsonNode> first_seen = Optional.ofNullable(node.get("first_seen"));
            first_seen.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setFirstSeen(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });

            Optional<JsonNode> last_seen = Optional.ofNullable(node.get("last_seen"));
            last_seen.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setLastSeen(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
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

            return object;
        }
    }
}
