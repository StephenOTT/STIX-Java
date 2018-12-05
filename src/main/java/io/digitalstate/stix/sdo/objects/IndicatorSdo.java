package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.IndicatorLabels;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface IndicatorSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "indicator";
    }

    @Override
    @NotNull @Size(min = 1)
    @Vocab(IndicatorLabels.class)
    Set<@Size(min = 1) String> getLabels();

    @JsonProperty("name")
    Optional<String> getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotBlank
    @JsonProperty("indicator")
    String getPattern();

    @NotNull
    @JsonProperty("valid_from")
    Instant getValidFrom();

    @JsonProperty("valid_until")
    Optional<Instant> getValidUntil();

    @NotNull
    @JsonProperty("kill_chain_phases")
    Set<KillChainPhaseType> getKillChainPhases();

    @NotNull
    @JsonIgnore
    Set<String> getIndicates();

}
