package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.json.StixParsers;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Value.Immutable
@DefaultTypeValue(value = "bundle", groups = {DefaultValuesProcessor.class})
@JsonTypeName("bundle")
@Value.Style(typeImmutable = "Bundle", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Bundle.class) @JsonDeserialize(builder = Bundle.Builder.class)
@JsonPropertyOrder({"type", "id", "spec_version", "objects"})
public interface BundleObject extends GenericValidation {

    @NotBlank
    @JsonProperty("type")
    String getType();

    @NotBlank
    @JsonProperty("id")
    String getId();

    @NotBlank
    @JsonProperty("spec_version")
    @Value.Default
    default String getSpecVersion(){
        return StixSpecVersion.SPECVERSION;
    }

    @Size(min = 1, message = "Must have at least 1 object in bundle")
    @JsonProperty("objects")
    Set<BundleableObject> getObjects();

    @JsonIgnore
    @Value.Lazy
    default String toJsonString() {
        try {
            return StixParsers.getJsonMapper(true).writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //@TODO followup on https://github.com/immutables/immutables/issues/877
            return null;
        }
    }

}
