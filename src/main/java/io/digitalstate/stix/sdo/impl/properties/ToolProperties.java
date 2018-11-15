package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.impl.types.KillChainPhase;

import java.time.LocalDateTime;
import java.util.List;

public abstract class ToolProperties extends CommonProperties{
    protected String name;
    protected String description = null;
    protected List<KillChainPhase> killChainPhases = null;
    protected String toolVersion = null;
}
