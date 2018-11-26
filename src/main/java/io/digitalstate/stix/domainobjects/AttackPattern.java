package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.AttackPatternProperties;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.*;
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
