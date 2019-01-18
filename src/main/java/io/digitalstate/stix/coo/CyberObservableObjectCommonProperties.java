package io.digitalstate.stix.coo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface CyberObservableObjectCommonProperties {

    @NotBlank
    @JsonProperty("type")
    String getType();

    @NotNull
    @JsonProperty("extensions") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Map<String, String> getExtensions();

}
