package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.redaction.Redactable;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 *
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixModified {

    @NotNull
    @JsonProperty("modified")
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Value.Default
    @Redactable
    default Instant getModified(){
        return Instant.now();
    }
}
