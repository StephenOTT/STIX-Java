package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * windows-service-ext
 * <p>
 * The Windows Service extension specifies a default extension for capturing
 * properties specific to Windows services.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-service-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = WindowsServiceExtension.class) @JsonDeserialize(builder = WindowsServiceExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "service_name", "descriptions", "display_name", "group_name", "start_type", "service_dll_refs",
		"service_type", "service_status" })
@JsonTypeName("windows-service-ext")
public interface WindowsServiceExtensionExt extends CyberObservableExtension {

	@JsonProperty("service_name")
	@JsonPropertyDescription("Specifies the name of the service.")
	@NotNull
	String getServiceName();

	@JsonProperty("descriptions")
	@JsonPropertyDescription("Specifies the descriptions defined for the service.")
	Set<String> getDescriptions();

	@JsonProperty("display_name")
	@JsonPropertyDescription("Specifies the displayed name of the service in Windows GUI controls.")
	Optional<String> getDisplayName();

	@JsonProperty("group_name")
	@JsonPropertyDescription("Specifies the name of the load ordering group of which the service is a member.")
	Optional<String> getGroupName();

	@JsonProperty("start_type")
	@JsonPropertyDescription("Specifies the start options defined for the service. windows-service-start-enum")
	Optional<ServiceStartType> getServiceStartType();

	@JsonProperty("service_dll_refs")
	@JsonPropertyDescription("Specifies the DLLs loaded by the service, as a reference to one or more File Objects.")
	Set<String> getServiceDllRefs();

	@JsonProperty("service_type")
	@JsonPropertyDescription("Specifies the type of the service. windows-service-enum")
	Optional<ServiceType> getServiceType();

	@JsonProperty("service_status")
	@JsonPropertyDescription("Specifies the current status of the service. windows-service-status-enum")
	Optional<ServiceStatus> getServiceStatus();
	
	

	@JsonTypeName("windows-service-status-enum")
	public enum ServiceStatus {
		SERVICE_CONTINUE_PENDING, SERVICE_PAUSE_PENDING, SERVICE_PAUSED, SERVICE_RUNNING, SERVICE_START_PENDING, SERVICE_STOP_PENDING, SERVICE_STOPPED;
	}

	@JsonTypeName("windows-service-type-enum")
	public enum ServiceType {
		SERVICE_KERNEL_DRIVER, SERVICE_FILE_SYSTEM_DRIVER, SERVICE_WIN32_OWN_PROCESS, SERVICE_WIN32_SHARE_PROCESS;
	}

	@JsonTypeName("windows-service-start-type-enum")
	public enum ServiceStartType {
		SERVICE_AUTO_START, SERVICE_BOOT_START, SERVICE_DEMAND_START, SERVICE_DISABLED, SERVICE_SYSTEM_ALERT;
	}
}
