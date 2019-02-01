package io.digitalstate.stix.sdo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.AttackMotivations;
import io.digitalstate.stix.vocabularies.AttackResourceLevels;
import io.digitalstate.stix.vocabularies.ThreatActorLabels;
import io.digitalstate.stix.vocabularies.ThreatActorRoles;
import io.digitalstate.stix.vocabularies.ThreatActorSophistication;

/**
 * threat-actor
 * <p>
 * Threat Actors are actual individuals, groups, or organizations believed to be operating with malicious intent.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("threat-actor")
@DefaultTypeValue(value = "threat-actor", groups = {DefaultValuesProcessor.class})
@JsonSerialize(as = ThreatActor.class) @JsonDeserialize(builder = ThreatActor.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "uses", "name",
        "description", "aliases", "roles", "goals", "sophistication",
        "resource_level", "primary_motivation", "secondary_motivation", "personal_motivations"})
@Redactable
public interface ThreatActorSdo extends DomainObject {

    @Override
    @NotNull @Size(min = 1, message = "Must have at least one value from threat-actor-label-ov")
    @Vocab(ThreatActorLabels.class)
	@JsonPropertyDescription("This field specifies the type of threat actor. Open Vocab - threat-actor-label-ov")
    @Redactable(useMask = true)
    Set<@Size(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
	@JsonPropertyDescription("A name used to identify this Threat Actor or Threat Actor group.")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("A description that provides more details and context about the Threat Actor.")
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("A list of other names that this Threat Actor is believed to use.")
    @Redactable
    Set<String> getAliases();

    @NotNull
    @Vocab(ThreatActorRoles.class)
    @JsonProperty("roles") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("This is a list of roles the Threat Actor plays. Open Vocab - threat-actor-role-ov")
    @Redactable
    Set<String> getRoles();

    @NotNull
    @JsonProperty("goals") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("The high level goals of this Threat Actor, namely, what are they trying to do.")
    @Redactable
    Set<@Size(min = 1) String> getGoals();

    @NotNull
    @Vocab(ThreatActorSophistication.class)
    @JsonProperty("sophistication") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("The skill, specific knowledge, special training, or expertise a Threat Actor must have to perform the attack. Open Vocab - threat-actor-sophistication-ov")
    @Redactable
    Set<String> getSophistication();

    @Vocab(AttackResourceLevels.class)
    @JsonProperty("resource_level") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("This defines the organizational level at which this Threat Actor typically works. Open Vocab - attack-resource-level-ov")
    @Redactable
    Optional<String> getResourceLevel();

    @Vocab(AttackMotivations.class)
    @JsonProperty("primary_motivation") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The primary reason, motivation, or purpose behind this Threat Actor. Open Vocab - attack-motivation-ov")
    @Redactable
    Optional<String> getPrimaryMotivation();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("secondary_motivations") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The secondary reasons, motivations, or purposes behind this Threat Actor. Open Vocab - attack-motivation-ov")
    @Redactable
    Set<String> getSecondaryMotivations();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("personal_motivations") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The personal reasons, motivations, or purposes of the Threat Actor regardless of organizational goals. Open Vocab - attack-motivation-ov")
    @Redactable
    Set<String> getPersonalMotivations();

}
