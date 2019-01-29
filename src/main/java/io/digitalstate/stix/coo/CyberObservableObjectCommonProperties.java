package io.digitalstate.stix.coo;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface CyberObservableObjectCommonProperties {

    @NotBlank
    @JsonProperty("type")
    @JsonPropertyDescription("Indicates that this object is an Observable Object. The value of this property MUST be a valid Observable Object type name, but to allow for custom objects this has been removed from the schema.")
    @Pattern(regexp = "^\\-?[a-z0-9]+(-[a-z0-9]+)*\\-?$")
    @Size(min = 3, max = 250)
    String getType();

    @NotNull
    @JsonProperty("extensions") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies any extensions of the object, as a dictionary.")
    @Valid
    Map<String, String> getExtensions();

}
