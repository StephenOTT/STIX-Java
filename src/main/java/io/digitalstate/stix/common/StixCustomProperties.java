package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Set;

/**
 * Stix Custom Properties
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixCustomProperties {

    // @TODO json property handling
    @JsonIgnore
    Set<HashMap<String,String>> getCustomProperties();
}
