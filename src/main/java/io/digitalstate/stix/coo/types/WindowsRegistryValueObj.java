package io.digitalstate.stix.coo.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.WindowsRegistryValueDataTypes;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


/**
 * The Windows Registry Value type captures the properties of a Windows Registry Key Value.
 */
@Value.Immutable @Serial.Version(1L)
//@DefaultTypeValue(value = "windows-registry-value-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = WindowsRegistryValue.class) @JsonDeserialize(builder = WindowsRegistryValue.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "name", "data", "data_type" })
//@JsonTypeName("windows-registry-value-type")
public interface WindowsRegistryValueObj extends GenericValidation {

    @JsonProperty("name")
    @JsonPropertyDescription("Specifies the name of the registry value. For specifying the default value in a registry key, an empty string MUST be used.")
    @NotNull
    String getName();

    @JsonProperty("data")
    @JsonPropertyDescription("Specifies the data contained in the registry value.")
    Optional<String> getData();

    @JsonProperty("data_type")
    @JsonPropertyDescription("Specifies the registry (REG_*) data type used in the registry value.")
    @Vocab(WindowsRegistryValueDataTypes.class)
    Optional<String> getDataType();

}
