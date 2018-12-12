package io.digitalstate.stix.coo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface CyberObservableObjectCommonProperties {

    @NotBlank
    @JsonProperty("type")
    String getType();

    @JsonProperty("extensions")
    Map<String, String> getExtensions();

}
