package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;

import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class ToolProperties extends CommonProperties{
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonProperty("kill_chain_phases")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<KillChainPhase> killChainPhases = null;

    @JsonProperty("tool_version")
    @JsonInclude(NON_NULL)
    protected String toolVersion = null;

    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedHashSet<KillChainPhase> getKillChainPhases() {
        return killChainPhases;
    }
    public void setKillChainPhases(LinkedHashSet<KillChainPhase> killChainPhases) {
        this.killChainPhases = killChainPhases;
    }

    public String getToolVersion() {
        return toolVersion;
    }
    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }
}
