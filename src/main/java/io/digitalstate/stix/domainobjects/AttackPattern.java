package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.AttackPatternProperties;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.helpers.StixDataFormats;
import org.camunda.commons.utils.IoUtilException;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = AttackPattern.Deserializer.class)
public class AttackPattern extends AttackPatternProperties implements StixDomainObject {

    private static final String TYPE = "attack-pattern";

    public AttackPattern(String name) {
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
    }

    /**
     * Used for JSON Deserialization
     */
    private AttackPattern() {
    }

    public static class Deserializer extends StdDeserializer<AttackPattern> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public AttackPattern deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            AttackPattern object = new AttackPattern();

            Optional<JsonNode> type = Optional.ofNullable(node.get("type"));
            type.ifPresent(o -> {
                if (!o.isNull() && o.asText().equals(TYPE)) {
                    object.setType(o.asText());
                } else {
                    throw new IllegalArgumentException("Invalid Type: " + o.asText());
                }
            });
            type.orElseThrow(() -> new IllegalArgumentException("Type is Required"));

            Optional<JsonNode> id = Optional.ofNullable(node.get("id"));
            id.ifPresent(o -> {
                if (!o.isNull()) {
                    object.setId(o.asText());
                } else {
                    throw new IllegalArgumentException("Invalid Id, if is null");
                }
            });
            id.orElseThrow(() -> new IllegalArgumentException("Id is Required"));

            Optional<JsonNode> created_by_ref = Optional.ofNullable(node.get("created_by_ref"));
            created_by_ref.ifPresent(o -> {

            });

            Optional<JsonNode> created = Optional.ofNullable(node.get("created"));
            created.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setCreated(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });
            created.orElseThrow(() -> new IllegalArgumentException("Created is Required"));

            Optional<JsonNode> modified = Optional.ofNullable(node.get("modified"));
            modified.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setModified(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });
            modified.orElseThrow(() -> new IllegalArgumentException("Modified is Required"));

            Optional<JsonNode> revoked = Optional.ofNullable(node.get("revoked"));
            revoked.ifPresent(o -> {
                object.setRevoked(o.booleanValue());
            });

            Optional<JsonNode> labels = Optional.ofNullable(node.get("labels"));
            labels.ifPresent(o -> {
                if (o.isArray()) {
                    LinkedHashSet<String> list = new LinkedHashSet<>();
                    o.forEach(item -> {
                        list.add(item.asText());
                    });
                    object.setLabels(list);
                }
            });

            Optional<JsonNode> external_references = Optional.ofNullable(node.get("external_references"));
            external_references.ifPresent(o -> {

            });

            Optional<JsonNode> object_marking_refs = Optional.ofNullable(node.get("object_marking_refs"));
            object_marking_refs.ifPresent(o -> {

            });

            Optional<JsonNode> granular_markings = Optional.ofNullable(node.get("granular_markings"));
            granular_markings.ifPresent(o -> {

            });

            Optional<JsonNode> targets = Optional.ofNullable(node.get("targets"));
            targets.ifPresent(o -> {

            });

            Optional<JsonNode> uses = Optional.ofNullable(node.get("uses"));
            uses.ifPresent(o -> {

            });

            Optional<JsonNode> description = Optional.ofNullable(node.get("description"));
            description.ifPresent(o -> {
                object.setDescription(o.textValue());
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
