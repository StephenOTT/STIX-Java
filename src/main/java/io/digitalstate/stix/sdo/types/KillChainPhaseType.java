package io.digitalstate.stix.sdo.types;

import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;

@Value.Immutable
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface KillChainPhaseType {

    @NotBlank
    String killChainName();

    @NotBlank
    String phaseName();

}
