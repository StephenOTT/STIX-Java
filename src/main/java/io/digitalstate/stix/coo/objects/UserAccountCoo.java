package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixInstant;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.json.StixOptionalInstantDeserializer;
import io.digitalstate.stix.json.StixOptionalInstantSerializer;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabulary.vocabularies.AccountTypes;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * user-account
 * <p>
 * The User Account Object represents an instance of any type of user account,
 * including but not limited to operating system, device, messaging service, and
 * social media platform accounts.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "user-account", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = UserAccount.class) @JsonDeserialize(builder = UserAccount.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("user-account")
@JsonPropertyOrder({ "type", "extensions","user_id", "account_login", "account_type", "display_name",
        "is_service_account", "is_privileged", "can_escalate_privs", "is_disabled", "account_created",
        "account_expires", "password_last_changed", "account_first_login", "account_last_login" })
public interface UserAccountCoo extends CyberObservableObject {

    @JsonProperty("user_id")
    @JsonPropertyDescription("Specifies the identifier of the account.")
    @NotNull
    String getUserId();

    @JsonProperty("account_login")
    @JsonPropertyDescription("Specifies the account login string, used in cases where the user_id property specifies something other than what a user would type when they login.")
    Optional<String> getAccountLogin();

    @JsonProperty("account_type")
    @JsonPropertyDescription("Specifies the type of the account. This is an open vocabulary and values SHOULD come from the account-type-ov vocabulary.")
    Optional<@Vocab(AccountTypes.class) String> getAccountType();

    @JsonProperty("display_name")
    @JsonPropertyDescription("Specifies the display name of the account, to be shown in user interfaces, if applicable.")
    Optional<String> getDisplayName();

    @JsonProperty("is_service_account")
    @JsonPropertyDescription("Indicates that the account is associated with a network service or system process (daemon), not a specific individual.")
    @NotNull
    Optional<Boolean> isServiceAccount();

    @JsonProperty("is_privileged")
    @JsonPropertyDescription("Specifies that the account has elevated privileges (i.e., in the case of root on Unix or the Windows Administrator account).")
    @NotNull
    Optional<Boolean> isPrivileged();

    @JsonProperty("can_escalate_privs")
    @JsonPropertyDescription("Specifies that the account has the ability to escalate privileges (i.e., in the case of sudo on Unix or a Windows Domain Admin account).")
    @NotNull
    Optional<Boolean> isCanEscalatePrivs();

    @JsonProperty("is_disabled")
    @JsonPropertyDescription("Specifies if the account is disabled.")
    @NotNull
    Optional<Boolean> isDisabled();

    @JsonProperty("account_created")
    @JsonPropertyDescription("Specifies when the account was created.")
    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    Optional<StixInstant> getAccountCreated();

    @JsonProperty("account_expires")
    @JsonPropertyDescription("Specifies the expiration date of the account.")
    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    Optional<StixInstant> getAccountExpires();

    @JsonProperty("password_last_changed")
    @JsonPropertyDescription("Specifies when the account password was last changed.")
    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    Optional<StixInstant> getPasswordLastChanged();

    @JsonProperty("account_first_login")
    @JsonPropertyDescription("Specifies when the account was first accessed.")
    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    Optional<StixInstant> getAccountFirstLogin();

    @JsonProperty("account_last_login")
    @JsonPropertyDescription("Specifies when the account was last accessed.")
    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    Optional<StixInstant> getAccountLastLogin();

}
