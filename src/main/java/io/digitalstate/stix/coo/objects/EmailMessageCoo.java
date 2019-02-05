package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.coo.types.MimePartTypeObj;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

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
@BusinessRule(ifExp = "isMultipart() == true", thenExp = "getBody().isPresent() == false", errorMessage = "Email Message cannot have Body when email is Multipart")
public interface EmailMessageCoo extends CyberObservableObject {

    @JsonProperty("is_multipart")
    @JsonPropertyDescription("Indicates whether the email body contains multiple MIME parts.")
    @NotNull
    boolean isMultipart();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("date")
    @JsonPropertyDescription("Specifies the date/time that the email message was sent.")
    Optional<Instant> getDate();


    @JsonProperty("content_type") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the value of the 'Content-Type' header of the email message.")
    Optional<String> getContentType();


    @JsonProperty("from_ref") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the value of the 'From:' header of the email message.")
    Optional<String> getFromRef();


    @JsonProperty("sender_ref") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the value of the 'From' field of the email message")
    Optional<String> getSenderRef();


    @JsonProperty("to_refs")  @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the mailboxes that are 'To:' recipients of the email message")
    Set<String> getToRefs();


    @JsonProperty("cc_refs")  @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the mailboxes that are 'CC:' recipients of the email message")
    Set<String> getCcRefs();


    @JsonProperty("bcc_refs")  @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the mailboxes that are 'BCC:' recipients of the email message.")
    Set<String> getBccRefs();

    @JsonProperty("subject") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the subject of the email message.")
    Optional<String> getSubject();

    @JsonProperty("received_lines") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies one or more Received header fields that may be included in the email headers.")
    Set<String> getReceivedLines();

    @JsonProperty("additional_header_fields") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies any other header fields (except for date, received_lines, content_type, from_ref, sender_ref, to_refs, cc_refs, bcc_refs, and subject) found in the email message, as a dictionary.")
    Map<String, Set<String>> getAdditionalHeaderFields();

    @JsonProperty("raw_email_ref") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies the raw binary contents of the email message, including both the headers and body, as a reference to an Artifact Object.")
    Optional<String> getRawEmailRef();

    @JsonProperty("body_multipart")  @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies a list of the MIME parts that make up the email body.")
    Set<MimePartTypeObj> getBodyMultipart();

    @JsonProperty("body") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("Specifies a string containing the email body.")
    Optional<String> getBody();


}
