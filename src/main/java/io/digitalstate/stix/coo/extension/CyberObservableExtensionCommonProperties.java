package io.digitalstate.stix.coo.extension;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface CyberObservableExtensionCommonProperties {

    /**
     * This property is used for generation of the dictionary during serialization, and used as the "Type" mapping value for polymorphic when deserializing.
     * @return
     */
    @NotBlank
    @JsonIgnore
    @JsonProperty("type")
    @Size(min = 3, max = 250)
    String getType();

}
