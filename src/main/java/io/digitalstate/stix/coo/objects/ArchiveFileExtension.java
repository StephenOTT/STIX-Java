package io.digitalstate.stix.coo.objects;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.digitalstate.stix.coo.CyberExtension;
/**
 * The Archive File extension specifies a default extension for capturing
 * properties specific to archive files.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "contains_refs", "version", "comment" })
@JsonTypeName("archive-ext")
public class ArchiveFileExtension implements CyberExtension {

	@JsonProperty("contains_refs")
	@JsonPropertyDescription("Specifies the files contained in the archive, as a reference to one or more other File Objects. The objects referenced in this list MUST be of type file-object.")
	@Valid
	@NotNull
	private List<String> containsRefs;
	

	@JsonProperty("version")
	@JsonPropertyDescription("Specifies the version of the archive type used in the archive file.")
	private String version;
	

	@JsonProperty("comment")
	@JsonPropertyDescription("Specifies a comment included as part of the archive file.")
	private String comment;

}
