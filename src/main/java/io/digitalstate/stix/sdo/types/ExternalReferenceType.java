package io.digitalstate.stix.sdo.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * external-reference
 * <p>
 * External references are used to describe pointers to information represented outside of STIX.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@Value.Style(typeAbstract="*Type", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, depluralize = true)
@JsonSerialize(as = ExternalReference.class) @JsonDeserialize(builder = ExternalReference.Builder.class)
@JsonPropertyOrder({
	"source_name",
    "description",
    "url",
    "hashes",
    "external_id"
})
public interface ExternalReferenceType extends GenericValidation, Serializable {

    @NotBlank
    @JsonProperty("source_name")
	@JsonPropertyDescription("The source within which the external-reference is defined (system, registry, organization, etc.)")
    String getSourceName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("A human readable description")
    Optional<String> getDescription();

    @JsonProperty("url") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Matches the elements of a URL using a regular expression. Uses Diego Perini's regex from https://gist.github.com/dperini/729294.")
    Optional<String> getUrl();

    @JsonProperty("hashes") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("Specifies a dictionary of hashes for the file.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

    @JsonProperty("external_id") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("An identifier for the external reference content")
   Optional<String> getExternalId();

}
