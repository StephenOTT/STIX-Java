package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.vocab.AttackMotivation;
import io.digitalstate.stix.sdo.vocab.AttackResourceLevel;
import io.digitalstate.stix.sdo.vocab.ThreatActorRole;
import io.digitalstate.stix.sdo.vocab.ThreatActorSophistication;

import java.util.List;

public interface ThreatActor extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public List<String> getAliases();
    public void setAliases(List<String> aliases);

    public List<Enum<ThreatActorRole>> getRoles();
    public void setRoles(List<Enum<ThreatActorRole>> roles);

    public List<String> getGoals();
    public void setGoals(List<String> goals);

    public Enum<ThreatActorSophistication> getSophistication();
    public void setSophistication(Enum<ThreatActorSophistication> sophistication);

    public Enum<AttackResourceLevel> getResourceLevel();
    public void setResourceLevel(Enum<AttackResourceLevel> resourceLevel);

    public Enum<AttackMotivation> getPrimaryMotivation();
    public void setPrimaryMotivation(Enum<AttackMotivation> primaryMotivation);

    public List<Enum<AttackMotivation>> getSecondaryMotivations();
    public void setSecondaryMotivations(List<Enum<AttackMotivation>> secondaryMotivations);

    public List<Enum<AttackMotivation>> getPersonalMotivations();
    public void setPersonalMotivations(List<Enum<AttackMotivation>> personalMotivations);
}
