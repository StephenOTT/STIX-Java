package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

/**
 *
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixModified {

    @NotNull
    @JsonProperty("modified")
    @Value.Default
    default Instant getModified(){
        return Instant.now();
    }
}
