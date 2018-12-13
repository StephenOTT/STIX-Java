package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;

/**
 *
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixRevoked {

    @NotNull
    @JsonProperty("revoked")
    @Value.Default
    default boolean getRevoked(){
        return false;
    }

}
