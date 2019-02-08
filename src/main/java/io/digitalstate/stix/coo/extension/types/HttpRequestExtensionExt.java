package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.coo.objects.NetworkTrafficCoo;
import io.digitalstate.stix.validation.contraints.allowedparents.AllowedParents;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * http-request-ext
 * <p>
 * The HTTP request extension specifies a default extension for capturing
 * network traffic properties specific to HTTP requests.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "http-request-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class}, depluralize = true)
@JsonSerialize(as = HttpRequestExtension.class) @JsonDeserialize(builder = HttpRequestExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "request_method", "request_value", "request_version", "request_header", "message_body_length",
        "message_body_data_ref" })
@JsonTypeName("http-request-ext")
@AllowedParents({NetworkTrafficCoo.class})
public interface HttpRequestExtensionExt extends CyberObservableExtension {

    @JsonProperty("request_method")
    @JsonPropertyDescription("Specifies the HTTP method portion of the HTTP request line, as a lowercase string.")
    @NotNull
    String getRequestMethod();

    @JsonProperty("request_value")
    @JsonPropertyDescription("Specifies the value (typically a resource path) portion of the HTTP request line.")
    @NotNull
    String getRequestValue();

    @JsonProperty("request_version")
    @JsonPropertyDescription("Specifies the HTTP version portion of the HTTP request line, as a lowercase string.")
    Optional<String> getRequestVersion();

    /**
     * Currently only supports non-duplicate keys: https://github.com/oasis-tcs/cti-stix2/issues/137
     * @return
     */
    @JsonProperty("request_header")
    @JsonPropertyDescription("Specifies all of the HTTP header fields that may be found in the HTTP client request, as a dictionary.")
    Map<String,String> getRequestHeader();

    //@TODO Review if this should be a long
    @JsonProperty("message_body_length")
    @JsonPropertyDescription("Specifies the length of the HTTP message body, if included, in bytes.")
    Optional<Long> getMessageBodyLength();

    /*
     * Must be of type artifact
     */
    @JsonProperty("message_body_data_ref")
    @JsonPropertyDescription("Specifies the data contained in the HTTP message body, if included.")
    Optional<String> getMessageBodyDataRef();

}
