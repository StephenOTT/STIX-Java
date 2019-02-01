package io.digitalstate.stix.coo.types;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Optional;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.objects.MimePartType;
import io.digitalstate.stix.coo.objects.MimePartType.Builder;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
 * Specifies a component of a multi-part email body.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "mime-part-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("mime-part-type")
@JsonSerialize(as = MimePartType.class) @JsonDeserialize(builder = MimePartType.Builder.class)
@JsonPropertyOrder({"body", "body_raw_ref", "content_type", "content_disposition"})
public interface MimePartTypeObj {
	// Note: one of either body or body_raw_ref is needed
	
	/**
	 * Contents of body MUST be decoded to Unicode.
	 */
	@JsonProperty("body") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the contents of the MIME part if the content_type is not provided OR starts with text/")
	Optional<String> getBody();

	/**
	 * The object referenced in this property MUST be of type artifact or file. 
	 * For use cases where conveying the actual data contained in the MIME part is of primary importance, artifact SHOULD be used. 
	 * Otherwise, for use cases where conveying metadata about the file-like properties of the MIME part is of primary importance, file SHOULD be used.
	 */
	@JsonProperty("body_raw_ref") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the contents of non-textual MIME parts, that is those whose content_type does not start with text/")
	Optional<String> getBodyRawRef();


	/**
	 * Any additional “Content-Type” header field parameters such as charset SHOULD be included in this property.
	 */
	@JsonProperty("content_type") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the value of the 'Content-Type' header field of the MIME part.")
	Optional<String> getContentType();


	@JsonProperty("content_disposition") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the value of the 'Content-Disposition' header field of the MIME part.")
	Optional<String> getContentDisposition();
}