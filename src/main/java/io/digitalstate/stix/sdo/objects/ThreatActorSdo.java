package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.*;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@Value.Style(typeImmutable = "ThreatActor", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("threat-actor")
@DefaultTypeValue(value = "threat-actor", groups = {DefaultValuesProcessor.class})
@JsonSerialize(as = ThreatActor.class) @JsonDeserialize(builder = ThreatActor.Builder.class)
public interface ThreatActorSdo extends DomainObject {

    @Override
    @NotNull @Size(min = 1, message = "Must have at least one value from threat-actor-label-ov")
    @Vocab(ThreatActorLabels.class)
    Set<@Size(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases") @JsonInclude(NON_EMPTY)
    Set<String> getAliases();

    @NotNull
    @Vocab(ThreatActorRoles.class)
    @JsonProperty("roles") @JsonInclude(NON_EMPTY)
    Set<String> getRoles();

    @NotNull
    @JsonProperty("goals") @JsonInclude(NON_EMPTY)
    Set<@Size(min = 1) String> getGoals();

    @NotNull
    @Vocab(ThreatActorSophistication.class)
    @JsonProperty("sophistication") @JsonInclude(NON_EMPTY)
    Set<String> getSophistication();

    @Vocab(AttackResourceLevels.class)
    @JsonProperty("resource_level") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getResourceLevel();

    @Vocab(AttackMotivations.class)
    @JsonProperty("primary_motivation") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getPrimaryMotivation();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("secondary_motivations") @JsonInclude(NON_EMPTY)
    Set<String> getSecondaryMotivations();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("personal_motivations") @JsonInclude(NON_EMPTY)
    Set<String> getPersonalMotivations();

}
