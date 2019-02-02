package io.digitalstate.stix.coo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface CyberObservableObjectCommonProperties {

    @NotBlank
    @JsonProperty("type")
    @JsonPropertyDescription("Indicates that this object is an Observable Object. The value of this property MUST be a valid Observable Object type name, but to allow for custom objects this has been removed from the schema.")
    @Pattern(regexp = "^\\-?[a-z0-9]+(-[a-z0-9]+)*\\-?$")
    @Size(min = 3, max = 250)
    String getType();

    @JsonProperty("extensions") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies any extensions of the object, as a dictionary.")
    Map<String, String> getExtensions();

}
