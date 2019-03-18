package io.digitalstate.stix.coo.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.vocabulary.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.Pattern;
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
//@DefaultTypeValue(value = "windows-pe-optional-header-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true, depluralizeDictionary = {"hash:hashes"})
@JsonSerialize(as = WindowsPeOptionalHeader.class) @JsonDeserialize(builder = WindowsPeOptionalHeader.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "magic_hex", "major_linker_version", "minor_linker_version", "size_of_code",
        "size_of_initialized_data", "size_of_uninitialized_data", "address_of_entry_point", "base_of_code",
        "base_of_data", "image_base", "section_alignment", "file_alignment", "major_os_version", "minor_os_version",
        "major_image_version", "minor_image_version", "major_subsystem_version", "minor_subsystem_version",
        "win32_version_value_hex", "size_of_image", "size_of_headers", "checksum_hex", "subsystem_hex",
        "dll_characteristics_hex", "size_of_stack_reserve", "size_of_stack_commit", "size_of_heap_reserve",
        "size_of_heap_commit", "loader_flags_hex", "number_of_rva_and_sizes", "hashes" })
//@JsonTypeName("windows-pe-optional-header-type")
@BusinessRule(ifExp = "true", thenExp = "getMagicHex().isPresent() == true || getMajorLinkerVersion().isPresent() == true || getMinorLinkerVersion().isPresent() == true || getSizeOfCode().isPresent() == true || getSizeOfInitializedData().isPresent() == true || getSizeOfUninitializedData().isPresent() == true || getAddressOfEntryPoint().isPresent() == true || getBaseOfCode().isPresent() == true || getBaseOfData().isPresent() == true || getImageBase().isPresent() == true || getSectionAlignment().isPresent() == true || getFileAlignment().isPresent() == true || getMajorOsVersion().isPresent() == true || getMinorOsVersion().isPresent() == true || getMajorImageVersion().isPresent() == true || getMinorImageVersion().isPresent() == true || getMajorSubsystemVersion().isPresent() == true || getMinorSubsystemVersion().isPresent() == true || getWin32VersionValueHex().isPresent() == true || getSizeOfImage().isPresent() == true || getSizeOfHeaders().isPresent() == true || getChecksumHex().isPresent() == true || getSubsystemHex().isPresent() == true || getDllCharacteristicsHex().isPresent() == true || getSizeOfStackReserve().isPresent() == true || getSizeOfStackCommit().isPresent() == true || getSizeOfHeapReserve().isPresent() == true || getSizeOfHeapCommit().isPresent() == true || getLoaderFlagsHex().isPresent() == true || getNumberOfRvaAndSizes().isPresent() == true || getHashes().isEmpty() == true", errorMessage = "At least 1 field must be used in Windows Pe Extension Optional Header Object.")
public interface WindowsPeOptionalHeaderObj extends GenericValidation, StixCustomProperties {
//@TODO Add GITHUB issue that says the requirement for atleast 1 field to be present

    @JsonProperty("magic_hex")
    @JsonPropertyDescription("Specifies the unsigned Optional<Integer> that indicates the type of the PE binary.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getMagicHex();

    @JsonProperty("major_linker_version")
    @JsonPropertyDescription("Specifies the linker major version number.")
    Optional<Long> getMajorLinkerVersion();

    @JsonProperty("minor_linker_version")
    @JsonPropertyDescription("Specifies the linker minor version number.")
    Optional<Long> getMinorLinkerVersion();

    @JsonProperty("size_of_code")
    @JsonPropertyDescription("Specifies the size of the code (text) section. If there are multiple such sections, this refers to the sum of the sizes of each section.")
    Optional<@PositiveOrZero Long> getSizeOfCode();

    @JsonProperty("size_of_initialized_data")
    @JsonPropertyDescription("Specifies the size of the initialized data section. If there are multiple such sections, this refers to the sum of the sizes of each section.")
    Optional<@PositiveOrZero Long> getSizeOfInitializedData();

    @JsonProperty("size_of_uninitialized_data")
    @JsonPropertyDescription("Specifies the size of the uninitialized data section. If there are multiple such sections, this refers to the sum of the sizes of each section.")
    Optional<@PositiveOrZero Long> getSizeOfUninitializedData();

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
    Optional<Long> getMajorSubsystemVersion();

    @JsonProperty("minor_subsystem_version")
    @JsonPropertyDescription("Specifies the minor version number of the subsystem.")
    Optional<Long> getMinorSubsystemVersion();

    @JsonProperty("win32_version_value_hex")
    @JsonPropertyDescription("Specifies the reserved win32 version value.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getWin32VersionValueHex();

    @JsonProperty("size_of_image")
    @JsonPropertyDescription("Specifies the size, in bytes, of the image, including all headers, as the image is loaded in memory.")
    Optional<@PositiveOrZero Long> getSizeOfImage();

    @JsonProperty("size_of_headers")
    @JsonPropertyDescription("Specifies the combined size of the MS-DOS, PE header, and section headers, rounded up a multiple of the value specified in the file_alignment header.")
    Optional<@PositiveOrZero Long> getSizeOfHeaders();

    @JsonProperty("checksum_hex")
    @JsonPropertyDescription("Specifies the checksum of the PE binary.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getChecksumHex();

    @JsonProperty("subsystem_hex")
    @JsonPropertyDescription("Specifies the subsystem (e.g., GUI, device driver, etc.) that is required to run this image.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getSubsystemHex();

    @JsonProperty("dll_characteristics_hex")
    @JsonPropertyDescription("Specifies the flags that characterize the PE binary.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getDllCharacteristicsHex();

    @JsonProperty("size_of_stack_reserve")
    @JsonPropertyDescription("Specifies the size of the stack to reserve")
    Optional<@PositiveOrZero Long> getSizeOfStackReserve();

    @JsonProperty("size_of_stack_commit")
    @JsonPropertyDescription("Specifies the size of the stack to commit.")
    Optional<@PositiveOrZero Long> getSizeOfStackCommit();

    @JsonProperty("size_of_heap_reserve")
    @JsonPropertyDescription("Specifies the size of the local heap space to reserve.")
    Optional<@PositiveOrZero Long> getSizeOfHeapReserve();

    @JsonProperty("size_of_heap_commit")
    @JsonPropertyDescription("Specifies the size of the local heap space to commit.")
    Optional<@PositiveOrZero Long> getSizeOfHeapCommit();

    @JsonProperty("loader_flags_hex")
    @JsonPropertyDescription("Specifies the reserved loader flags.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getLoaderFlagsHex();

    @JsonProperty("number_of_rva_and_sizes")
    @JsonPropertyDescription("Specifies the number of data-directory entries in the remainder of the optional header.")
    Optional<Long> getNumberOfRvaAndSizes();

    @JsonProperty("hashes")
    @JsonPropertyDescription("Specifies any hashes that were computed for the optional header.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

}