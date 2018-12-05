package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.*;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "ThreatActor", validationMethod = Value.Style.ValidationMethod.NONE)
public interface ThreatActorSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "threat-actor";
    }

    @Override
    @NotNull @Size(min = 1, message = "Must have at least one value from threat-actor-label-ov")
    @Vocab(ThreatActorLabels.class)
    Set<@Size(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases")
    Set<String> getAliases();

    @NotNull
    @Vocab(ThreatActorRoles.class)
    @JsonProperty("roles")
    Set<String> getRoles();

    @NotNull
    @JsonProperty("goals")
    Set<@Size(min = 1) String> getGoals();

    @NotNull
    @Vocab(ThreatActorSophistication.class)
    @JsonProperty("sophistication")
    Set<String> getSophistication();

    @Vocab(AttackResourceLevels.class)
    @JsonProperty("resource_level")
    Optional<String> getResourceLevel();

    @Vocab(AttackMotivations.class)
    @JsonProperty("primary_motivation")
    Optional<String> getPrimaryMotivation();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("secondary_motivations")
    Set<String> getSecondaryMotivations();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("personal_motivations")
    Set<String> getPersonalMotivations();

    @NotNull
    @JsonIgnore
    Set<DomainObject> getAttributedTo();

    @NotNull
    @JsonIgnore
    Set<DomainObject> getImpersonates();

    @NotNull
    @JsonIgnore
    Set<DomainObject> getTargets();

    @NotNull
    @JsonIgnore
    Set<DomainObject> getUses();
}
