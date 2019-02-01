package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;
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

import io.digitalstate.stix.coo.CyberExtension;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
* The Windows Process extension specifies a default extension for capturing properties specific to Windows processes.
* 
*/
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-process-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = WindowsProcessExtension.class) @JsonDeserialize(builder = WindowsProcessExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "aslr_enabled", "dep_enabled", "priority", "owner_sid", "window_title", "startup_info" })
@JsonTypeName("windows-process-ext")
public interface WindowsProcessExtensionExt extends CyberExtension {

	@JsonProperty("aslr_enabled")
	@JsonPropertyDescription("Specifies whether Address Space Layout Randomization (ASLR) is enabled for the process.")
	Optional<Boolean> getAslrEnabled();

	@JsonProperty("dep_enabled")
	@JsonPropertyDescription("Specifies whether Data Execution Prevention (DEP) is enabled for the process.")
	Optional<Boolean> getDepEnabled();

	@JsonProperty("priority")
	@JsonPropertyDescription("Specifies the current priority class of the process in Windows.")
	Optional<String> getPriority();

	@JsonProperty("owner_sid")
	@JsonPropertyDescription("Specifies the Security ID (SID) value of the owner of the process.")
	Optional<String> getOwnerSid();

	@JsonProperty("window_title")
	@JsonPropertyDescription("Specifies the title of the main window of the process.")
	Optional<String> getWindowTitle();

	@JsonProperty("startup_info")
	@JsonPropertyDescription("Specifies the STARTUP_INFO struct used by the process, as a dictionary.")
	Map<String,String> getStartupInfo();

}
