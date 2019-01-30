package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Specifies a component of a multi-part email body.
 *
 */
public interface MimePartType {
	// one of either body or body_raw_ref is needed
	
	/**
	 * Contents of body MUST be decoded to Unicode.
	 */
	@JsonProperty("body")
	@JsonPropertyDescription("Specifies the contents of the MIME part if the content_type is not provided OR starts with text/")
	String getBody();

	/**
	 * The object referenced in this property MUST be of type artifact or file. 
	 * For use cases where conveying the actual data contained in the MIME part is of primary importance, artifact SHOULD be used. 
	 * Otherwise, for use cases where conveying metadata about the file-like properties of the MIME part is of primary importance, file SHOULD be used.
	 */
	@JsonProperty("body_raw_ref")
	@JsonPropertyDescription("Specifies the contents of non-textual MIME parts, that is those whose content_type does not start with text/")
	String getBodyRawRef();


	/**
	 * Any additional “Content-Type” header field parameters such as charset SHOULD be included in this property.
	 */
	@JsonProperty("content_type")
	@JsonPropertyDescription("Specifies the value of the 'Content-Type' header field of the MIME part.")
	String getContentType();


	@JsonProperty("content_disposition")
	@JsonPropertyDescription("Specifies the value of the 'Content-Disposition' header field of the MIME part.")
	String getContentDisposition();
}
