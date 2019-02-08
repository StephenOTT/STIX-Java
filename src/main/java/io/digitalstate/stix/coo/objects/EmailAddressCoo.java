package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * email-addr
 * <p>
 * The Email Address Object represents a single email address.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "email-addr", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonTypeName("email-addr")
@JsonSerialize(as = EmailAddress.class) @JsonDeserialize(builder = EmailAddress.Builder.class)
@JsonPropertyOrder({"type", "extensions", "value", "display_name", "belongs_to_ref"})
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
public interface EmailAddressCoo extends CyberObservableObject {

    @JsonProperty("value")
    @JsonPropertyDescription("Specifies a single email address. This MUST not include the display name.")
    @Pattern(regexp="(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)")
    @NotNull
    String getValue();

    @JsonProperty("display_name")
    @JsonPropertyDescription("Specifies a single email display name, i.e., the name that is displayed to the human user of a mail application.")
    Optional<String> getDisplayName();

    @JsonProperty("belongs_to_ref")
    @JsonPropertyDescription("Specifies the user account that the email address belongs to, as a reference to a User Account Object.")
    Optional<String> getBelongsToRef();

}
