package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.vocab.AttackMotivation;
import io.digitalstate.stix.sdo.vocab.AttackResourceLevel;

import java.time.LocalDateTime;
import java.util.List;

public abstract class IntrusionSetProperties extends CommonProperties{
    protected String name = null;
    protected String description = null;
    protected List<String> aliases = null;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;
    protected List<String> goals = null;
    protected Enum<AttackResourceLevel> resourceLevel = null;
    protected Enum<AttackMotivation> primaryMotivation = null;
    protected List<Enum<AttackMotivation>> secondaryMotivations = null;
}
