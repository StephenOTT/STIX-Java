package io.digitalstate.stix.coo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
 * The Email Message Object represents an instance of an email message.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "email-message", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("email-message")
@JsonSerialize(as = EmailMessage.class) @JsonDeserialize(builder = EmailMessage.Builder.class)
@JsonPropertyOrder({ "type", "extensions", "is_multipart", "date", "content_type", "from_ref", "sender_ref", "to_refs", "cc_refs", "bcc_refs", "subject",
	"received_lines", "additional_header_fields", "body", "body_multipart", "raw_email_ref" })
public interface EmailMessageCoo extends CyberObservableObject {
	
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	@JsonProperty("date")
	@JsonPropertyDescription("Specifies the date/time that the email message was sent.")
	Optional<Instant> getDate();


	@JsonProperty("content_type")
	@JsonPropertyDescription("Specifies the value of the 'Content-Type' header of the email message.")
	String getContentType();


	@JsonProperty("from_ref")
	@JsonPropertyDescription("Specifies the value of the 'From:' header of the email message.")
	String getFromRef();


	@JsonProperty("sender_ref")
	@JsonPropertyDescription("Specifies the value of the 'From' field of the email message")
	String getSenderRef();


	@JsonProperty("to_refs") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("Specifies the mailboxes that are 'To:' recipients of the email message")
	Set<String> getToRefs();


	@JsonProperty("cc_refs") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("Specifies the mailboxes that are 'CC:' recipients of the email message")
	Set<String> getCcRefs();


	@JsonProperty("bcc_refs") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("Specifies the mailboxes that are 'BCC:' recipients of the email message.")
	Set<String> getBccRefs();

	@JsonProperty("subject")
	@JsonPropertyDescription("Specifies the subject of the email message.")
	String getSubject();

	@JsonProperty("received_lines") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("Specifies one or more Received header fields that may be included in the email headers.")
	Set<String> getReceivedLines();

	@JsonProperty("additional_header_fields") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("Specifies any other header fields (except for date, received_lines, content_type, from_ref, sender_ref, to_refs, cc_refs, bcc_refs, and subject) found in the email message, as a dictionary.")
	Map<String, Set<String>> getAdditionalHeaderFields();

	@JsonProperty("raw_email_ref")
	@JsonPropertyDescription("Specifies the raw binary contents of the email message, including both the headers and body, as a reference to an Artifact Object.")
	String getRawEmailRef();

	@JsonProperty("is_multipart") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Indicates whether the email body contains multiple MIME parts.")
	Boolean isMultipart();

	@JsonProperty("body_multipart") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("Specifies a list of the MIME parts that make up the email body.")
	Set<MimePartType> getModyMultipart();

	@JsonProperty("body")
	@JsonPropertyDescription("Specifies a string containing the email body.")
	String getBody();


}
