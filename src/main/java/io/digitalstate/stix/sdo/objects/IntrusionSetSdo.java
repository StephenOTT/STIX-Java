package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.AttackMotivations;
import io.digitalstate.stix.vocabularies.AttackResourceLevels;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * intrusion-set
 * <p>
 * An Intrusion Set is a grouped set of adversary behavior and resources with common properties that is believed to be orchestrated by a single organization.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("intrusion-set")
@DefaultTypeValue(value = "intrusion-set", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = IntrusionSet.class) @JsonDeserialize(builder = IntrusionSet.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description", "aliases",
        "first_seen", "last_seen", "goals", "resource_level",
        "primary_motivation", "secondary_motivation"})
@Redactable
public interface IntrusionSetSdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    @JsonPropertyDescription("The name used to identify the Intrusion Set.")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Provides more context and details about the Intrusion Set object.")
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("Alternative names used to identify this Intrusion Set.")
    @Redactable
    Set<String> getAliases();

    @JsonProperty("first_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The time that this Intrusion Set was first seen.")
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The time that this Intrusion Set was last seen.")
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable
    Optional<Instant> getLastSeen();

    @NotNull
    @JsonProperty("goals") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The high level goals of this Intrusion Set, namely, what are they trying to do.")
    @Redactable
    Set<String> getGoals();

    @JsonProperty("resource_level") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("This defines the organizational level at which this Intrusion Set typically works. Open Vocab - attack-resource-level-ov")
    @Redactable
    Optional<@Vocab(AttackResourceLevels.class) String> getResourceLevel();

    @JsonProperty("primary_motivation") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The primary reason, motivation, or purpose behind this Intrusion Set. Open Vocab - attack-motivation-ov")
    @Redactable
    Optional<@Vocab(AttackMotivations.class) String> getPrimaryMotivation();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("secondary_motivations") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The secondary reasons, motivations, or purposes behind this Intrusion Set. Open Vocab - attack-motivation-ov")
    @Redactable
    Set<String> getSecondaryMotivations();

}
