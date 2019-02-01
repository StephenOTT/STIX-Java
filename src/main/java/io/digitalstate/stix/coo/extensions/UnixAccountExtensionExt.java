package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Optional;
import java.util.Set;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.CyberExtension;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
 * unix-account-ext
 * <p>
 * The UNIX account extension specifies a default extension for capturing the additional information
 * for an account on a UNIX system.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "unix-account-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = UnixAccountExtension.class) @JsonDeserialize(builder = UnixAccountExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "gid", "groups", "home_dir", "shell" })
@JsonTypeName("unix-account-ext")
public interface UnixAccountExtensionExt extends CyberExtension {

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