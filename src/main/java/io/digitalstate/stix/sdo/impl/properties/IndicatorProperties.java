package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.impl.types.KillChainPhase;

import java.time.LocalDateTime;
import java.util.List;

public abstract class IndicatorProperties extends CommonProperties{
    protected String name = null;
    protected String description = null;
    protected String pattern;
    protected LocalDateTime validFrom;
    protected LocalDateTime validUntil;
    protected List<KillChainPhase> killChainPhases = null;

}
