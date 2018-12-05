package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.AttackMotivations;
import io.digitalstate.stix.vocabularies.AttackResourceLevels;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "IntrusionSet", validationMethod = Value.Style.ValidationMethod.NONE)
public interface IntrusionSetSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "intrusion-set";
    }

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases")
    Set<String> getAliases();

    @JsonProperty("first_seen")
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen")
    Optional<Instant> getLastSeen();

    @NotNull
    @JsonProperty("goals")
    Set<String> getGoals();

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
    @JsonIgnore
    Set<String> getAttributedTo();

    @NotNull
    @JsonIgnore
    Set<String> getTargets();

    @NotNull
    @JsonIgnore
    Set<String> getUses();

}
