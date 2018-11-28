package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.properties.ToolProperties;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = Tool.Deserializer.class)
public class Tool extends ToolProperties implements StixDomainObject {

    public static final String TYPE = "tool";

    public Tool(String name,
                LinkedHashSet<String> toolLabels){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
        setLabels(toolLabels);
    }
    public Tool(String name, String... toolLabels){
        this(name, new LinkedHashSet<>(Arrays.asList(toolLabels)));
    }


    /**
     * Used for JSON Deserialization
     */
    private Tool() {
    }

    public static class Deserializer extends StdDeserializer<Tool> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Tool deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Tool object = new Tool();

            validateAllCommonProperties(node, jp, object, TYPE, true);

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

            Optional<JsonNode> tool_version = Optional.ofNullable(node.get("tool_version"));
            tool_version.ifPresent(o -> {
                object.setToolVersion(o.textValue());
            });

            return object;
        }
    }

}
