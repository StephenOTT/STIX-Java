package io.digitalstate.stix.domainobjects.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.AttackPattern;
import io.digitalstate.stix.helpers.StixDataFormats;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.Optional;

@JsonDeserialize(using = KillChainPhase.Deserializer.class)
@JsonPropertyOrder({"kill_chain_name", "phase_name"})
public class KillChainPhase {
    //@TODO add logic for characters:
    // The name of the kill chain. The value of this property SHOULD be all lowercase (where lowercase is defined by the locality conventions) and SHOULD use hyphens instead of spaces or underscores as word separators.
    // The name of the phase in the kill chain. The value of this property SHOULD be all lowercase (where lowercase is defined by the locality conventions) and SHOULD use hyphens instead of spaces or underscores as word separators.

    @JsonProperty("kill_chain_name")
    private String killChainName;

    @JsonProperty("phase_name")
    private String phaseName;

    public KillChainPhase(String killChainName, String phaseName){
        setKillChainName(killChainName);
        setPhaseName(phaseName);
    }

    /**
     * Used for JSON Deserialization
     */
    private KillChainPhase(){}


    //
    // Getters and Setters
    //

    public String getKillChainName() {
        return killChainName;
    }

    public void setKillChainName(String killChainName) {
        if (StringUtils.isNotBlank(killChainName)){
            this.killChainName = killChainName;
        } else {
            throw new IllegalArgumentException("KillChainName cannot be null or blank");
        }
    }

    public String getPhaseName(){
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        if (StringUtils.isNotBlank(phaseName)){
            this.phaseName = phaseName;
        } else {
            throw new IllegalArgumentException("PhaseName cannot be null or blank");
        }
    }

    @Override
    public String toString(){
        ReflectionToStringBuilder builder = new ReflectionToStringBuilder(this);
        return builder.toString();
    }



    public static class Deserializer extends StdDeserializer<KillChainPhase> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public KillChainPhase deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            KillChainPhase object = new KillChainPhase();

            Optional<JsonNode> kill_chain_name = Optional.ofNullable(node.get("kill_chain_name"));
            kill_chain_name.ifPresent(o->{
                if (!o.isNull()){
                    object.setKillChainName(o.asText());
                } else {
                    throw new IllegalArgumentException("kill_chain_name is required");
                }
            });
            kill_chain_name.orElseThrow(() -> new IllegalArgumentException("kill_chain_name is required"));

            Optional<JsonNode> phase_name = Optional.ofNullable(node.get("phase_name"));
            phase_name.ifPresent(o->{
                if (!o.isNull()){
                    object.setPhaseName(o.asText());
                } else {
                    throw new IllegalArgumentException("phase_name is required");
                }
            });
            phase_name.orElseThrow(() -> new IllegalArgumentException("phase_name is required"));

            return object;
        }
    }
}
