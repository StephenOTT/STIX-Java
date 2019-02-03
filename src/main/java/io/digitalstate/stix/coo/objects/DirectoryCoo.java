package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.OptionalPattern;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * directory
 * <p>
 * The Directory Object represents the properties common to a file system directory.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "directory", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("directory")
@JsonSerialize(as = Directory.class) @JsonDeserialize(builder = Directory.Builder.class)
@JsonPropertyOrder({"type", "extensions", "path", "path_enc", "created", "modified", "accessed", "contains_refs"})
public interface DirectoryCoo extends CyberObservableObject {

    @JsonProperty("path") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the path, as originally observed, to the directory on the file system.")
    String getPath();

	/**
	 * This value MUST be specified using the corresponding name from the 2013-12-20 revision of the IANA character set registry.
	 */
    @JsonProperty("path_enc") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the observed encoding for the path.")
	@OptionalPattern(regexp="^[a-zA-Z0-9/\\.+_:-]{2,250}$")
    Optional<String> getName();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("created") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the date/time the directory was created.")
    Optional<Instant> getCreated();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("modified") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the date/time the directory was last written to/modified.")
    Optional<Instant> getModified();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("accessed") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the date/time the directory was last accessed.")
    Optional<Instant> getAccessed();

    //@TODO add proper support for contains refs.  Must be Set of File or Directory types
    @JsonProperty("contains_refs") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies a list of references to other File and/or Directory Objects contained within the directory.")
    Set<String> getContainsRefs();


}
