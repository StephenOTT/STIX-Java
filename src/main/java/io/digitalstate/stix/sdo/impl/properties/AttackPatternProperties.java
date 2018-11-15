package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.impl.types.KillChainPhase;

import java.util.List;

public abstract class AttackPatternProperties extends CommonProperties {
    protected String name;
    protected String description = null;
    protected List<KillChainPhase> killChainPhases = null;
}
