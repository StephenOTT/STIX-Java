package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Stix Custom Properties
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixCustomProperties {

    // @TODO json property handling
    @JsonAnyGetter
    @JsonInclude(NON_EMPTY)
    Set<HashMap<String,String>> getCustomProperties();
}
