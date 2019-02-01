package io.digitalstate.stix.coo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
 * windows-registry-key
 * <p>
 * The Registry Key Object represents the properties of a Windows registry key.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-registry-key", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = WindowsRegistryKey.class) @JsonDeserialize(builder = WindowsRegistryKey.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "type", "key", "values", "modified", "creator_user_ref", "number_of_subkeys", "extensions" })
@JsonTypeName("windows-registry-key")
public interface WindowsRegistryKeyCoo extends CyberObservableObject {

	@JsonProperty("key")
	@JsonPropertyDescription("Specifies the full registry key including the hive.")
	@Pattern(regexp = "^HKEY_LOCAL_MACHINE|hkey_local_machine|HKEY_CURRENT_USER|hkey_current_user|HKEY_CLASSES_ROOT|hkey_classes_root|HKEY_CURRENT_CONFIG|hkey_current_config|HKEY_PERFORMANCE_DATA|hkey_performance_data|HKEY_USERS|hkey_users|HKEY_DYN_DATA")
	@NotNull
	String getKey();

	@JsonProperty("values")
	@JsonPropertyDescription("Specifies the values found under the registry key.")
	Set<WindowsRegistryValue> getValues();

	@JsonProperty("modified")
	@JsonPropertyDescription("Specifies the last date/time that the registry key was modified.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Optional<Instant> getModified();

	/*
	 * TODO Must be of type user-account
	 */
	@JsonProperty("creator_user_ref")
	@JsonPropertyDescription("Specifies a reference to a user account, represented as a User Account Object, that created the registry key.")
	Optional<String> getCreatorUserRef();

	@JsonProperty("number_of_subkeys")
	@JsonPropertyDescription("Specifies the number of subkeys contained under the registry key.")
	Optional<Integer> getNumberOfSubkeys();

}