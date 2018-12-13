package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.IndicatorLabels;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@JsonTypeName("indicator")
@DefaultTypeValue(value = "indicator", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Indicator.class) @JsonDeserialize(builder = Indicator.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name",
        "description", "pattern", "valid_from", "valid_until",
        "kill_chain_phases"})
public interface IndicatorSdo extends DomainObject {

    @Override
    @NotNull @Size(min = 1)
    @Vocab(IndicatorLabels.class)
    Set<@Length(min = 1) String> getLabels();

    @JsonProperty("name") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getDescription();

    @NotBlank
    @JsonProperty("pattern")
    String getPattern();

    @NotNull
    @JsonProperty("valid_from")
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Instant getValidFrom();

    @JsonProperty("valid_until") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getValidUntil();

    @NotNull
    @JsonProperty("kill_chain_phases") @JsonInclude(NON_EMPTY)
    Set<KillChainPhaseType> getKillChainPhases();

}
