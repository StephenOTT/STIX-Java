package io.digitalstate.stix.coo.types;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.objects.WindowsRegistryValue;
import io.digitalstate.stix.coo.objects.WindowsRegistryValue.Builder;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;


/**
 * The Windows Registry Value type captures the properties of a Windows Registry Key Value.
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-registry-value-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = WindowsRegistryValue.class) @JsonDeserialize(builder = WindowsRegistryValue.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "name", "data", "data_type" })
@JsonTypeName("windows-registry-value-type")
public interface WindowsRegistryValueObj {

	@JsonProperty("name")
	@JsonPropertyDescription("Specifies the name of the registry value. For specifying the default value in a registry key, an empty string MUST be used.")
	@NotNull
	String getName();

	@JsonProperty("data")
	@JsonPropertyDescription("Specifies the data contained in the registry value.")
	Optional<String> getData();

	@JsonProperty("data_type")
	@JsonPropertyDescription("Specifies the registry (REG_*) data type used in the registry value.")
	Optional<DataType> getDataType();
	


	public enum DataType {
		REG_NONE, 
		REG_SZ, 
		REG_EXPAND_SZ, 
		REG_BINARY, 
		REG_DWORD, 
		REG_DWORD_BIG_ENDIAN, 
		REG_LINK, 
		REG_MULTI_SZ, 
		REG_RESOURCE_LIST, 
		REG_FULL_RESOURCE_DESCRIPTION, 
		REG_RESOURCE_REQUIREMENTS_LIST, 
		REG_QWORD, 
		REG_INVALID_TYPE;
	}

}