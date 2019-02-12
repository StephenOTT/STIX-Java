package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.coo.objects.FileCoo;
import io.digitalstate.stix.validation.contraints.coo.allowedparents.AllowedParents;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The Archive File extension specifies a default extension for capturing
 * properties specific to archive files.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "archive-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class}, depluralize = true)
@JsonSerialize(as = ArchiveFileExtension.class) @JsonDeserialize(builder = ArchiveFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "contains_refs", "version", "comment" })
@JsonTypeName("archive-ext")
@AllowedParents({FileCoo.class})
public interface ArchiveFileExtensionExt extends CyberObservableExtension {

    @JsonProperty("contains_refs")
    @JsonPropertyDescription("Specifies the files contained in the archive, as a reference to one or more other File Objects. The objects referenced in this list MUST be of type file-object.")
    @NotNull
    Set<String> getContainsRefs();

    @JsonProperty("version")
    @JsonPropertyDescription("Specifies the version of the archive type used in the archive file.")
    Optional<String> getVersion();

    @JsonProperty("comment")
    @JsonPropertyDescription("Specifies a comment included as part of the archive file.")
    Optional<String> getComment();

}
