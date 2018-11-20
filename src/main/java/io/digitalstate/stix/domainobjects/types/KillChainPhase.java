package io.digitalstate.stix.domainobjects.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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
}
