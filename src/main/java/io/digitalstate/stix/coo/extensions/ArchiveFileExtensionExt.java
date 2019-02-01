package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Optional;
import java.util.Set;

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

import io.digitalstate.stix.coo.CyberExtension;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
/**
 * The Archive File extension specifies a default extension for capturing
 * properties specific to archive files.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "archive-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = ArchiveFileExtension.class) @JsonDeserialize(builder = ArchiveFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "contains_refs", "version", "comment" })
@JsonTypeName("archive-ext")
public interface ArchiveFileExtensionExt extends CyberExtension {

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