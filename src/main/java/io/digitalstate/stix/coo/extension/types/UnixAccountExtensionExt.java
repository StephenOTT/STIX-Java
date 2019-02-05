package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.coo.objects.UserAccountCoo;
import io.digitalstate.stix.validation.contraints.allowedparents.AllowedParents;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * unix-account-ext
 * <p>
 * The UNIX account extension specifies a default extension for capturing the additional information
 * for an account on a UNIX system.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "unix-account-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class})
@JsonSerialize(as = UnixAccountExtension.class) @JsonDeserialize(builder = UnixAccountExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "gid", "groups", "home_dir", "shell" })
@JsonTypeName("unix-account-ext")
@AllowedParents({UserAccountCoo.class})
public interface UnixAccountExtensionExt extends CyberObservableExtension {

    @JsonProperty("gid")
    @JsonPropertyDescription("Specifies the primary group ID of the account.")
    Optional<Integer> getGid();

    @JsonProperty("groups")
    @JsonPropertyDescription("Specifies a list of names of groups that the account is a member of.")
    Set<String> getGroups();

    @JsonProperty("home_dir")
    @JsonPropertyDescription("Specifies the home directory of the account.")
    Optional<String> getHomeDir();

    @JsonProperty("shell")
    @JsonPropertyDescription("Specifies the account\u2019s command shell.")
    Optional<String> getShell();
}
