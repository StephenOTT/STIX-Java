package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.OptionalPattern;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.EncryptionAlgorithms;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.Positive;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * file
 * <p>
 * The File Object represents the properties of a file.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "file", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonTypeName("file")
@JsonSerialize(as = File.class) @JsonDeserialize(builder = File.Builder.class)
@JsonPropertyOrder({ "type", "extensions", "hashes", "size", "name", "name_enc", "magic_number_hex", "mime_type", "created", "modified",
        "accessed", "parent_directory_ref", "is_encrypted", "encryption_algorithm", "decryption_key" , "contains_refs", "content_ref", })
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@BusinessRule(ifExp = "isEncrypted().orElse(false) == false", thenExp = "getEncryptionAlgorithm().isPresent() == false && getDecryptionKey().isPresent() == false")
public interface FileCoo extends CyberObservableObject {

    @JsonProperty("hashes")
    @JsonPropertyDescription("Specifies a dictionary of hashes for the contents of the url or the payload_bin.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

    @JsonProperty("size")
    @JsonPropertyDescription("Specifies the size of the file, in bytes, as a non-negative integer.")
    @Positive
    Optional<Long> getSize();

    @JsonProperty("name")
    @JsonPropertyDescription("Specifies the name of the file.")
    Optional<String> getName();

    @JsonProperty("name_enc")
    @JsonPropertyDescription("Specifies the observed encoding for the name of the file.")
    @OptionalPattern(regexp = "^[a-zA-Z0-9/\\.+_:-]{2,250}$")
    Optional<String> getNameEnc();

    @JsonProperty("magic_number_hex")
    @JsonPropertyDescription("Specifies the hexadecimal constant ('magic number') associated with a specific file format that corresponds to the file, if applicable.")
    @OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
    Optional<String> getMagicNumberHex();

    @JsonProperty("mime_type")
    @JsonPropertyDescription("Specifies the MIME type name specified for the file, e.g., 'application/msword'.")
    @OptionalPattern(regexp = "^(application|audio|font|image|message|model|multipart|text|video)/[a-zA-Z0-9.+_-]+")
    Optional<String> getMimeType();

    @JsonProperty("created")
    @JsonPropertyDescription("Specifies the date/time the file was created.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getCreated();

    @JsonProperty("modified")
    @JsonPropertyDescription("Specifies the date/time the file was last written to/modified.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getModified();

    @JsonProperty("accessed")
    @JsonPropertyDescription("Specifies the date/time the file was last accessed.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getAccessed();

    @JsonProperty("parent_directory_ref")
    @JsonPropertyDescription("Specifies the parent directory of the file, as a reference to a Directory Object.")
    Optional<String> getParentDirectoryRef();

    @JsonProperty("contains_refs")
    @JsonPropertyDescription("Specifies a list of references to other Observable Objects contained within the file.")
    Set<String> getContainsRefs();

    @JsonProperty("content_ref")
    @JsonPropertyDescription("Specifies the content of the file, represented as an Artifact Object.")
    Optional<String> getContentRef();

    @JsonProperty("is_encrypted")
    @JsonPropertyDescription("Specifies whether the file is encrypted.")
    Optional<Boolean> isEncrypted();

    @JsonProperty("encryption_algorithm")
    @JsonPropertyDescription("Specifies the name of the encryption algorithm used to encrypt the file. Open Vocabulary - encryption-algorithm-ov")
    @Vocab(EncryptionAlgorithms.class)
    Optional<String> getEncryptionAlgorithm();

    @JsonProperty("decryption_key")
    @JsonPropertyDescription("Specifies the decryption key used to decrypt the archive file.")
    Optional<String> getDecryptionKey();

}
