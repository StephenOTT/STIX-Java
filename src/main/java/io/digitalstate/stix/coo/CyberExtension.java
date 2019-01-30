package io.digitalstate.stix.coo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.digitalstate.stix.coo.objects.ArchiveFileExtension;


/**
 * Interface to tag Cyber Extension classes
 * 
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NONE,
		include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
	@Type(value = ArchiveFileExtension.class, name="archive-ext"),
//	@Type(value = NTFSFileExtensions.class, name="ntfxs-ext"),
//	@Type(value = PDFFileExtension.class, name="pdf-ext"),
//	@Type(value = RasterImageFileExtension.class, name="raster-image-ext"),
//	@Type(value = WindowsPEBinaryFileExtension.class, name="windows=pebinary-ext"),
//	@Type(value = HTTPRequestExtension.class, name="http-request-ext"),
//	@Type(value = TCPExtension.class, name="tcp-ext"),
//	@Type(value = ICMPExtension.class, name="icmp-ext"),
//	@Type(value = NetworkSocketExtensions.class, name="socket-ext"),
//	@Type(value = WindowsProcessExtension.class, name="windows-process-ext"),
//	@Type(value = WindowsServiceExtension.class, name="windows-service-ext"),
//	@Type(value = UnixAccountExtension.class, name="unix-account-ext")

})
public interface CyberExtension {

}
