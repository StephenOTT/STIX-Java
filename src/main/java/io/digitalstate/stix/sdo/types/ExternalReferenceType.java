package io.digitalstate.stix.sdo.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.types.HashesType;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface ExternalReferenceType {

    @NotBlank
    @JsonProperty("source_name")
    String getSourceName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @JsonProperty("url")
    Optional<String> getUrl();

    @JsonProperty("hashes")
    Set<HashesType> hashes();

    @JsonProperty("external_id")
    Optional<String> externalId();

    @JsonIgnore
    @Value.Check
    default void checkUrlHashes() {
//        Preconditions.checkState(resourceLevel().equals("dog"),
//                "Resource Level must be from values of attack-resource-level-ov");
        //@TODO add better check
        // IF Url is used, then the Hashes field should be used as well
        // Create a boolean override that allows someone to bypass this requirement
        // Have the object marked with the boolean so it can be checked downstream
    }

}
