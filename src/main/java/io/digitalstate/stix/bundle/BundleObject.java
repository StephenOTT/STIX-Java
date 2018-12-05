package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.helpers.StixSpecVersion;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Value.Immutable
@Value.Style(typeImmutable = "Bundle", validationMethod = Value.Style.ValidationMethod.NONE)
public interface BundleObject {

    @NotBlank
    @JsonProperty("type")
    @Value.Default
    default String getType(){
        return "bundle";
    }

    @NotBlank
    @JsonProperty("id")
    @Value.Default
    default String getId(){
        return String.join("--", getType(), UUID.randomUUID().toString());
    }

    @NotBlank
    @JsonProperty("spec_version")
    @Value.Lazy
    default String getSpecVersion(){
        return StixSpecVersion.SPECVERSION;
    }

    @Size(min = 1, message = "Must have at least 1 object in bundle")
    @JsonProperty("objects")
    Set<BundleableObject> getObjects();

}
