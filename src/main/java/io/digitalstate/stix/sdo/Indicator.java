package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.impl.types.KillChainPhase;

import java.time.LocalDateTime;
import java.util.List;

public interface Indicator extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public String getPattern();
    public void setPattern(String pattern);

    public LocalDateTime getValidFrom();
    public void setValidFrom(LocalDateTime validFrom);

    public LocalDateTime getValidUntil();
    public void setValidUntil(LocalDateTime validUntil);

    public List<KillChainPhase> getKillChainPhases();
    public void setKillChainPhases(List<KillChainPhase> killChainPhases);

}
