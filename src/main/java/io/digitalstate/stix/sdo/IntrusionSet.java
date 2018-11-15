package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.vocab.AttackMotivation;
import io.digitalstate.stix.sdo.vocab.AttackResourceLevel;

import java.time.LocalDateTime;
import java.util.List;

public interface IntrusionSet extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public List<String> getAliases();
    public void setAliases(List<String> aliases);

    public LocalDateTime getFirstSeen();
    public void setFirstSeen(LocalDateTime firstSeen);

    public LocalDateTime getLastSeen();
    public void setLastSeen(LocalDateTime lastSeen);

    public List<String> getGoals();
    public void setGoals(List<String> goals);

    public Enum<AttackResourceLevel> getResourceLevel();
    public void setResourceLevel(Enum<AttackResourceLevel> resourceLevel);

    public Enum<AttackMotivation> getPrimaryMotivation();
    public void setPrimaryMotivation(Enum<AttackMotivation> primaryMotivation);

    public List<Enum<AttackMotivation>> getSecondaryMotivations();
    public void setSecondaryMotivations(List<Enum<AttackMotivation>> secondaryMotivations);
}
