package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixInstant;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabulary.vocabularies.IndicatorLabels;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * indicator
 * <p>
 * Indicators contain a pattern that can be used to detect suspicious or malicious cyber activity.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("indicator")
@DefaultTypeValue(value = "indicator", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = Indicator.class) @JsonDeserialize(builder = Indicator.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name",
        "description", "pattern", "valid_from", "valid_until",
        "kill_chain_phases"})
@Redactable
public interface IndicatorSdo extends DomainObject {

    @Override
    @NotNull @Size(min = 1)
    @Vocab(IndicatorLabels.class)
    @JsonPropertyDescription("This field is an Open Vocabulary that specifies the type of indicator. Open vocab - indicator-label-ov")
    @Redactable(useMask = true)
    Set<@Length(min = 1) String> getLabels();

    @JsonProperty("name") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The name used to identify the Indicator.")
    @Redactable
    Optional<String> getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("A description that provides more details and context about this Indicator, potentially including its purpose and its key characteristics.")
    @Redactable
    Optional<String> getDescription();

    @NotBlank
    @JsonProperty("pattern")
    @JsonPropertyDescription("The detection pattern for this indicator. The default language is STIX Patterning.")
    @Redactable(useMask = true)
    String getPattern();

    @NotNull
    @JsonProperty("valid_from")
    @JsonPropertyDescription("The time from which this indicator should be considered valuable intelligence.")
    @Redactable(useMask = true)
    StixInstant getValidFrom();

    @JsonProperty("valid_until") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The time at which this indicator should no longer be considered valuable intelligence.")
    @Redactable
    Optional<StixInstant> getValidUntil();

    @NotNull
    @JsonProperty("kill_chain_phases") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The list of kill chain phases for which this attack pattern is used.")
    @Redactable
    Set<KillChainPhaseType> getKillChainPhases();

}
