package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixInstant;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.coo.objects.FileCoo;
import io.digitalstate.stix.coo.types.WindowsPeOptionalHeaderObj;
import io.digitalstate.stix.coo.types.WindowsPeSectionObj;
import io.digitalstate.stix.validation.contraints.coo.allowedparents.AllowedParents;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabulary.vocabularies.HashingAlgorithms;
import io.digitalstate.stix.vocabulary.vocabularies.WindowsPeBinaryTypes;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Windows PE Binary File extension specifies a default extension for
 * capturing properties specific to Windows portable executable (PE) files.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "windows-pebinary-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class}, depluralize = true, depluralizeDictionary = {"hash:hashes"})
@JsonSerialize(as = WindowsPeBinaryFileExtension.class) @JsonDeserialize(builder = WindowsPeBinaryFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "pe_type", "imphash", "machine_hex", "number_of_sections", "time_date_stamp",
        "pointer_to_symbol_table_hex", "number_of_symbols", "size_of_optional_header", "characteristics_hex",
        "file_header_hashes", "optional_header", "sections", "required" })
@JsonTypeName("windows-pebinary-ext")
@AllowedParents({FileCoo.class})
public interface WindowsPeBinaryFileExtensionExt extends CyberObservableExtension {

    @JsonProperty("pe_type")
    @JsonPropertyDescription("Specifies the type of the PE binary. Open Vocabulary - windows-pebinary-type-ov")
    @NotNull
    @Vocab(WindowsPeBinaryTypes.class)
    String getPeType();

    @JsonProperty("imphash")
    @JsonPropertyDescription("Specifies the special import hash, or 'imphash', calculated for the PE Binary based on its imported libraries and functions.")
    Optional<String> getImphash();

    @JsonProperty("machine_hex")
    @JsonPropertyDescription("Specifies the type of target machine.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getMachineHex();

    @JsonProperty("number_of_sections")
    @JsonPropertyDescription("Specifies the number of sections in the PE binary, as a non-negative integer.")
    Optional<Long> getNumberOfSections();

    @JsonProperty("time_date_stamp")
    @JsonPropertyDescription("Specifies the time when the PE binary was created. The timestamp value MUST BE precise to the second.")
    Optional<StixInstant> getTimeDateStamp();

    @JsonProperty("pointer_to_symbol_table_hex")
    @JsonPropertyDescription("Specifies the file offset of the COFF symbol table.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getPointerToSymbolTableHex();

    @JsonProperty("number_of_symbols")
    @JsonPropertyDescription("Specifies the number of entries in the symbol table of the PE binary, as a non-negative integer.")
    Optional<Long> getNumberOfSymbols();

    @JsonProperty("size_of_optional_header")
    @JsonPropertyDescription("Specifies the size of the optional header of the PE binary.")
    Optional<@PositiveOrZero Long> getSizeOfOptionalHeader();

    @JsonProperty("characteristics_hex")
    @JsonPropertyDescription("Specifies the flags that indicate the file\u2019s characteristics.")
    Optional<@Pattern(regexp = "^([a-fA-F0-9]{2})+$") String> getCharacteristicsHex();

    @JsonProperty("file_header_hashes")
    @JsonPropertyDescription("Specifies any hashes that were computed for the file header.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getFileHeaderHashes();

    @JsonProperty("optional_header")
    @JsonPropertyDescription("Specifies the PE optional header of the PE binary.")
    Optional<WindowsPeOptionalHeaderObj> getOptionalHeader();

    @JsonProperty("sections")
    @JsonPropertyDescription("Specifies metadata about the sections in the PE file.")
    Set<WindowsPeSectionObj> getSections();

}
