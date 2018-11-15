package io.digitalstate.stix.sdo.impl.properties;

import java.time.LocalDateTime;
import java.util.List;

public abstract class CampaignProperties extends CommonProperties{
    protected String name;
    protected String description = null;
    protected List<String> aliases = null;
    protected LocalDateTime firstSeen = null;
    protected LocalDateTime lastSeen = null;
    protected String objective = null;
}
