package io.digitalstate.stix.coo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.digitalstate.stix.coo.extensions.ArchiveFileExtension;
import io.digitalstate.stix.coo.extensions.HttpRequestExtension;
import io.digitalstate.stix.coo.extensions.IcmpExtension;
import io.digitalstate.stix.coo.extensions.NetworkSocketExtension;
import io.digitalstate.stix.coo.extensions.NtfsFileExtenstion;
import io.digitalstate.stix.coo.extensions.PdfFileExtension;
import io.digitalstate.stix.coo.extensions.RasterImageFileExtension;
import io.digitalstate.stix.coo.extensions.TcpExtension;
import io.digitalstate.stix.coo.extensions.WindowsPeBinaryFileExtension;


/**
 * Interface to tag Cyber Extension classes
 * 
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NONE,
		include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
	@Type(value = ArchiveFileExtension.class, name="archive-ext"),
	@Type(value = NtfsFileExtenstion.class, name="ntfs-ext"),
	@Type(value = PdfFileExtension.class, name="pdf-ext"),
	@Type(value = RasterImageFileExtension.class, name="raster-image-ext"),
	@Type(value = WindowsPeBinaryFileExtension.class, name="windows-pebinary-ext"),
	@Type(value = HttpRequestExtension.class, name="http-request-ext"),
	@Type(value = TcpExtension.class, name="tcp-ext"),
	@Type(value = IcmpExtension.class, name="icmp-ext"),
	@Type(value = NetworkSocketExtension.class, name="socket-ext"),
//	@Type(value = WindowsProcessExtension.class, name="windows-process-ext"),
//	@Type(value = WindowsServiceExtension.class, name="windows-service-ext"),
//	@Type(value = UnixAccountExtension.class, name="unix-account-ext")

})
public interface CyberExtension {

}
