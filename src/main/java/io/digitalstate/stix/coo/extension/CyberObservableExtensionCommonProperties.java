package io.digitalstate.stix.coo.extension;

import com.fasterxml.jackson.annotation.*;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY)
public interface CyberObservableExtensionCommonProperties {

    @NotBlank
    @JsonIgnore
    @Size(min = 3, max = 250)
    String getType();

}
