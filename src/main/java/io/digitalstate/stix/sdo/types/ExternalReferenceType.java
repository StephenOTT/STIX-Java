package io.digitalstate.stix.sdo.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.types.HashesType;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@Value.Style(typeImmutable = "ExternalReference", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = ExternalReference.class) @JsonDeserialize(builder = ExternalReference.Builder.class)
public interface ExternalReferenceType {

    @NotBlank
    @JsonProperty("source_name")
    String getSourceName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getDescription();

    @JsonProperty("url") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getUrl();

    @JsonProperty("hashes") @JsonInclude(NON_EMPTY)
    Set<HashesType> hashes();

    @JsonProperty("external_id") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
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
