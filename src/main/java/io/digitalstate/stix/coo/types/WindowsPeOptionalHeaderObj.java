package io.digitalstate.stix.coo.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.OptionalPattern;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.PositiveOrZero;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


/**
 * The Windows PE Optional Header type represents the properties of the PE
 * optional header.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-pe-optional-header-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = WindowsPeOptionalHeader.class) @JsonDeserialize(builder = WindowsPeOptionalHeader.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "magic_hex", "major_linker_version", "minor_linker_version", "size_of_code",
        "size_of_initialized_data", "size_of_uninitialized_data", "address_of_entry_point", "base_of_code",
        "base_of_data", "image_base", "section_alignment", "file_alignment", "major_os_version", "minor_os_version",
        "major_image_version", "minor_image_version", "major_subsystem_version", "minor_subsystem_version",
        "win32_version_value_hex", "size_of_image", "size_of_headers", "checksum_hex", "subsystem_hex",
        "dll_characteristics_hex", "size_of_stack_reserve", "size_of_stack_commit", "size_of_heap_reserve",
        "size_of_heap_commit", "loader_flags_hex", "number_of_rva_and_sizes", "hashes" })
@JsonTypeName("windows-pe-optional-header-type")
public interface WindowsPeOptionalHeaderObj {

    @JsonProperty("magic_hex")
    @JsonPropertyDescription("Specifies the unsigned Optional<Integer> that indicates the type of the PE binary.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getMagicHex();

    @JsonProperty("major_linker_version")
    @JsonPropertyDescription("Specifies the linker major version number.")
    Optional<Long> getMajorLinkerVersion();

    @JsonProperty("minor_linker_version")
    @JsonPropertyDescription("Specifies the linker minor version number.")
    Optional<Long> getMinorLinkerVersion();

    @JsonProperty("size_of_code")
    @JsonPropertyDescription("Specifies the size of the code (text) section. If there are multiple such sections, this refers to the sum of the sizes of each section.")
    @PositiveOrZero
    Optional<Long> getSizeOfCode();

    @JsonProperty("size_of_initialized_data")
    @JsonPropertyDescription("Specifies the size of the initialized data section. If there are multiple such sections, this refers to the sum of the sizes of each section.")
    @PositiveOrZero
    Optional<Long> getSizeOfInitializedData();

    @JsonProperty("size_of_uninitialized_data")
    @JsonPropertyDescription("Specifies the size of the uninitialized data section. If there are multiple such sections, this refers to the sum of the sizes of each section.")
    @PositiveOrZero
    Optional<Long> getSizeOfUninitializedData();

    @JsonProperty("address_of_entry_point")
    @JsonPropertyDescription("Specifies the address of the entry point relative to the image base when the executable is loaded into memory.")
    Optional<Long> getAddressOfEntryPoint();

    @JsonProperty("base_of_code")
    @JsonPropertyDescription("Specifies the address that is relative to the image base of the beginning-of-code section when it is loaded into memory.")
    Optional<Long> getBaseOfCode();

    @JsonProperty("base_of_data")
    @JsonPropertyDescription("Specifies the address that is relative to the image base of the beginning-of-data section when it is loaded into memory.")
    Optional<Long> getBaseOfData();

    @JsonProperty("image_base")
    @JsonPropertyDescription("Specifies the preferred address of the first byte of the image when loaded into memory.")
    Optional<Long> getImageBase();

    @JsonProperty("section_alignment")
    @JsonPropertyDescription("Specifies the alignment (in bytes) of PE sections when they are loaded into memory.")
    Optional<Long> getSectionAlignment();

    @JsonProperty("file_alignment")
    @JsonPropertyDescription("Specifies the factor (in bytes) that is used to align the raw data of sections in the image file.")
    Optional<Long> getFileAlignment();

    @JsonProperty("major_os_version")
    @JsonPropertyDescription("Specifies the major version number of the required operating system.")
    Optional<Long> getMajorOsVersion();

    @JsonProperty("minor_os_version")
    @JsonPropertyDescription("Specifies the minor version number of the required operating system.")
    Optional<Long> getMinorOsVersion();

    @JsonProperty("major_image_version")
    @JsonPropertyDescription("Specifies the major version number of the image.")
    Optional<Long> getMajorImageVersion();

    @JsonProperty("minor_image_version")
    @JsonPropertyDescription("Specifies the minor version number of the image.")
    Optional<Long> getMinorImageVersion();

    @JsonProperty("major_subsystem_version")
    @JsonPropertyDescription("Specifies the major version number of the subsystem.")
    Optional<Integer> mgetMjorSubsystemVersion();

    @JsonProperty("minor_subsystem_version")
    @JsonPropertyDescription("Specifies the minor version number of the subsystem.")
    Optional<Integer> getMinorSubsystemVersion();

    @JsonProperty("win32_version_value_hex")
    @JsonPropertyDescription("Specifies the reserved win32 version value.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getWin32VersionValueHex();

    @JsonProperty("size_of_image")
    @JsonPropertyDescription("Specifies the size, in bytes, of the image, including all headers, as the image is loaded in memory.")
    @PositiveOrZero
    Optional<Integer> getSizeOfImage();

    @JsonProperty("size_of_headers")
    @JsonPropertyDescription("Specifies the combined size of the MS-DOS, PE header, and section headers, rounded up a multiple of the value specified in the file_alignment header.")
    @PositiveOrZero
    Optional<Integer> getSizeOfHeaders();

    @JsonProperty("checksum_hex")
    @JsonPropertyDescription("Specifies the checksum of the PE binary.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getChecksumHex();

    @JsonProperty("subsystem_hex")
    @JsonPropertyDescription("Specifies the subsystem (e.g., GUI, device driver, etc.) that is required to run this image.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getSubsystemHex();

    @JsonProperty("dll_characteristics_hex")
    @JsonPropertyDescription("Specifies the flags that characterize the PE binary.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getDllCharacteristicsHex();

    @JsonProperty("size_of_stack_reserve")
    @JsonPropertyDescription("Specifies the size of the stack to reserve")
    @PositiveOrZero
    Optional<Integer> getSizeOfStackReserve();

    @JsonProperty("size_of_stack_commit")
    @JsonPropertyDescription("Specifies the size of the stack to commit.")
    @PositiveOrZero
    Optional<Integer> getSizeOfStackCommit();

    @JsonProperty("size_of_heap_reserve")
    @JsonPropertyDescription("Specifies the size of the local heap space to reserve.")
    @PositiveOrZero
    Optional<Integer> getSizeOfHeapReserve();

    @JsonProperty("size_of_heap_commit")
    @JsonPropertyDescription("Specifies the size of the local heap space to commit.")
    @PositiveOrZero
    Optional<Integer> getSizeOfHeapCommit();

    @JsonProperty("loader_flags_hex")
    @JsonPropertyDescription("Specifies the reserved loader flags.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getLoaderFlagsHex();

    @JsonProperty("number_of_rva_and_sizes")
    @JsonPropertyDescription("Specifies the number of data-directory entries in the remainder of the optional header.")
    Optional<Long> getNumberOfRvaAndSizes();

    @JsonProperty("hashes")
    @JsonPropertyDescription("Specifies any hashes that were computed for the optional header.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

}
