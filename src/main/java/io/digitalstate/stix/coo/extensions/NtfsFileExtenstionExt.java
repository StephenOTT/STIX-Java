package io.digitalstate.stix.coo.extensions;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberExtension;
import io.digitalstate.stix.coo.types.NtfsAlternateDataStream;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
* The NTFS file extension specifies a default extension for capturing properties specific to the storage of the file on the NTFS file system.
* 
*/
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "ntfs-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = NtfsFileExtenstion.class) @JsonDeserialize(builder = NtfsFileExtenstion.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "sid", "alternate_data_streams" })
@JsonTypeName("ntfs-ext")
public interface NtfsFileExtenstionExt extends CyberExtension {

	// either SID or alternateDataStream is needed.
	
	@JsonProperty("sid")
	@JsonPropertyDescription("Specifies the security ID (SID) value assigned to the file.")
	Optional<String> getSid();


	@JsonProperty("alternate_data_streams")
	@JsonPropertyDescription("Specifies a list of NTFS alternate data streams that exist for the file.")
	Set<NtfsAlternateDataStream> getAlternateDataStreams();

}
