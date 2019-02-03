package io.digitalstate.stix.coo.extension;

import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.validation.GenericValidation;


/**
 * Interface to tag Cyber Observable Extension classes
 * 
 */
//@JsonTypeInfo(
//		use = JsonTypeInfo.Id.NONE,
//		include = JsonTypeInfo.As.EXISTING_PROPERTY)
//@JsonSubTypes({
//	@Type(value = ArchiveFileExtension.class, name="archive-ext"),
//	@Type(value = NtfsFileExtenstion.class, name="ntfs-ext"),
//	@Type(value = PdfFileExtension.class, name="pdf-ext"),
//	@Type(value = RasterImageFileExtension.class, name="raster-image-ext"),
//	@Type(value = WindowsPeBinaryFileExtension.class, name="windows-pebinary-ext"),
//	@Type(value = HttpRequestExtension.class, name="http-request-ext"),
//	@Type(value = TcpExtension.class, name="tcp-ext"),
//	@Type(value = IcmpExtension.class, name="icmp-ext"),
//	@Type(value = NetworkSocketExtension.class, name="socket-ext"),
//	@Type(value = WindowsProcessExtension.class, name="windows-process-ext"),
//	@Type(value = WindowsServiceExtension.class, name="windows-service-ext"),
//	@Type(value = UnixAccountExtension.class, name="unix-account-ext")
//
//})
public interface CyberObservableExtension extends
		CyberObservableExtensionCommonProperties,
		GenericValidation,
		StixCustomProperties {

}
