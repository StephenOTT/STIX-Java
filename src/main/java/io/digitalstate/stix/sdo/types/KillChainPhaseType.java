package io.digitalstate.stix.sdo.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;

@Value.Immutable
@Value.Style(typeImmutable = "KillChainPhase", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = KillChainPhase.class) @JsonDeserialize(builder = KillChainPhase.Builder.class)
public interface KillChainPhaseType {

    @NotBlank
    String killChainName();

    @NotBlank
    String phaseName();

}
