package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixInstant;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.json.StixOptionalInstantDeserializer;
import io.digitalstate.stix.json.StixOptionalInstantSerializer;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.Pattern;
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
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonTypeName("directory")
@JsonSerialize(as = Directory.class) @JsonDeserialize(builder = Directory.Builder.class)
@JsonPropertyOrder({"type", "extensions", "path", "path_enc", "created", "modified", "accessed", "contains_refs"})
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
public interface DirectoryCoo extends CyberObservableObject {

    @JsonProperty("path")
    @JsonPropertyDescription("Specifies the path, as originally observed, to the directory on the file system.")
    String getPath();

    /**
     * This value MUST be specified using the corresponding name from the 2013-12-20 revision of the IANA character set registry.
     */
    @JsonProperty("path_enc")
    @JsonPropertyDescription("Specifies the observed encoding for the path.")
    Optional<@Pattern(regexp = "^[a-zA-Z0-9/\\.+_:-]{2,250}$")
            String> getPathEnc();

    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    @JsonProperty("created")
    @JsonPropertyDescription("Specifies the date/time the directory was created.")
    Optional<StixInstant> getCreated();

    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    @JsonProperty("modified")
    @JsonPropertyDescription("Specifies the date/time the directory was last written to/modified.")
    Optional<StixInstant> getModified();

    @JsonSerialize(using = StixOptionalInstantSerializer.class) @JsonDeserialize(using = StixOptionalInstantDeserializer.class)
    @JsonProperty("accessed")
    @JsonPropertyDescription("Specifies the date/time the directory was last accessed.")
    Optional<StixInstant> getAccessed();

    //@TODO add proper support for contains refs.  Must be Set of File or Directory types
    @JsonProperty("contains_refs")
    @JsonPropertyDescription("Specifies a list of references to other File and/or Directory Objects contained within the directory.")
    Set<String> getContainsRefs();

}
