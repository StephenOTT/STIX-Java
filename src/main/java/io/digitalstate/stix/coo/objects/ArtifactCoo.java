package io.digitalstate.stix.coo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;

/**
 * artifact
 * <p>
 * The Artifact Object permits capturing an array of bytes (8-bits), as a base64-encoded string string, or linking to a file-like payload.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "artifact", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("artifact")
@JsonSerialize(as = Artifact.class) @JsonDeserialize(builder = Artifact.Builder.class)
@JsonPropertyOrder({"type", "extensions", "mime_type", "payload_bin", "url", "hashes"})
public interface ArtifactCoo extends CyberObservableObject {

	/**
	* The value of this property MUST be a valid MIME type as specified in the IANA Media Types registry.
	* 
	*/
	@JsonProperty("mime_type") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("The value of this property MUST be a valid MIME type as specified in the IANA Media Types registry.")
    @Redactable
    Optional<String> getMimeType();

    @JsonProperty("payload_bin") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the binary data contained in the artifact as a base64-encoded string.")
    @Redactable
    Optional<String> getPayloadBin();

    @JsonProperty("url") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The value of this property MUST be a valid URL that resolves to the unencoded content.")
    @Redactable
    Optional<String> getUrl();

    //@TODO review logic requirements for Redactable on Hash values
    /**
     * hashes
     * <p>
     * This MUST be provided when the url property is present.  Optional if payload_bin is present.
     * 
     */
    @JsonProperty("hashes") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("Specifies a dictionary of hashes for the contents of the url or the payload_bin.")
    @Valid
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

}
