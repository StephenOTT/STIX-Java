package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.impl.types.KillChainPhase;

import java.util.List;

public interface Tool extends CommonProperties {
    public String getName();
    public void setName(String name);

    public List<KillChainPhase> getKillChainPhases();
    public void setKillChainPhases(List<KillChainPhase> killChainPhases);

    public String getToolVersion();
    public void setToolVersion(String toolVersion);
}
