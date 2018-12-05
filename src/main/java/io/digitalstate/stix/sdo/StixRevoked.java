package io.digitalstate.stix.sdo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

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
