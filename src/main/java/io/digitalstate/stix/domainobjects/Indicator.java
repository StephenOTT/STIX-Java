package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.IndicatorProperties;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.*;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = Indicator.Deserializer.class)
public class Indicator extends IndicatorProperties implements StixDomainObject {

    public static final String TYPE = "indicator";

    public Indicator(String pattern,
                     ZonedDateTime validFrom,
                     LinkedHashSet<String> indicatorLabels) {

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setPattern(pattern);
        setValidFrom(validFrom);
        setLabels(indicatorLabels);
    }

    public Indicator(String pattern, ZonedDateTime validFrom, String... indicatorLabels) {
        this(pattern, validFrom, new LinkedHashSet<>(Arrays.asList(indicatorLabels)));
    }

    public static Indicator parse(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        return StixDataFormats.getJsonMapper().readValue(jsonString, Indicator.class);
    }


    /**
     * Used for JSON Deserialization
     */
    private Indicator() {
    }

    public static class Deserializer extends StdDeserializer<Indicator> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Indicator deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Indicator object = new Indicator();

            validateAllCommonProperties(node, jp, object, TYPE, true);

            Optional<JsonNode> name = Optional.ofNullable(node.get("name"));
            name.ifPresent(o -> {
                object.setName(o.textValue());
            });
            name.orElseThrow(() -> new IllegalArgumentException("Name is required"));

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
            });

            Optional<JsonNode> pattern = Optional.ofNullable(node.get("pattern"));
            pattern.ifPresent(o -> {
                object.setPattern(o.textValue());
            });
            pattern.orElseThrow(() -> new IllegalArgumentException("pattern is required"));


            Optional<JsonNode> valid_from = Optional.ofNullable(node.get("valid_from"));
            valid_from.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setValidFrom(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });

            Optional<JsonNode> valid_until = Optional.ofNullable(node.get("valid_until"));
            valid_until.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setValidUntil(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });

            Optional<JsonNode> kill_chain_phases = Optional.ofNullable(node.get("kill_chain_phases"));
            kill_chain_phases.ifPresent(items -> {
                // Check if the KCP property is a array
                if (items.isArray()) {
                    LinkedHashSet<KillChainPhase> killChainPhasesSet = new LinkedHashSet<>();
                    // For each object (kill chain phase) in the array:
                    items.forEach(kcp -> {
                        // Traverse the object to create a new parser
                        JsonParser killChainParser = kcp.traverse();
                        killChainParser.setCodec(jp.getCodec());

                        // Attempt to parse the object using the KillChainParser, and if parsed then add it to the set
                        try {
                            killChainPhasesSet.add(killChainParser.readValueAs(KillChainPhase.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new IllegalArgumentException("Unable to one of read a kill_chain_phase object");
                        }
                        // Add the set to the KillChainPhases property in the Attack Pattern
                        object.setKillChainPhases(killChainPhasesSet);
                    });
                } else {
                    throw new IllegalArgumentException("kill_chain_phases must be array");
                }
            });

            return object;
        }
    }


}
