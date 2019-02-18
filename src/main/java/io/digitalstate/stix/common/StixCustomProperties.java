package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.digitalstate.stix.validation.contraints.startswith.StartsWith;
import org.hibernate.validator.constraints.Length;

import java.util.Map;

/**
 * Stix Custom Properties
 */
public interface StixCustomProperties {

    /**
     * Custom Properties for STIX Objects.
     * Any object that supports custom properties will have a validation of the custom property prefix (typically "x_").
     * If the additional property in the JSON does not meet the StartsWith condition, then the JSON will be rejected.
     * @return Map of custom properties {@code Map<String, Object>}
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped @JsonAnyGetter
    Map<@StartsWith() @Length(min = 3, max = 250, message = "STIX Custom Properties must have a min key length of 3 and max of 250") String, Object> getCustomProperties();

}
