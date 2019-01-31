package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;
import java.util.Optional;

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
 * The PDF file extension specifies a default extension for capturing properties
 * specific to PDF files.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "pdf-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = PdfFileExtension.class) @JsonDeserialize(builder = PdfFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "version", "is_optimized", "document_info_dict", "pdfid0", "pdfid1" })
@JsonTypeName("pdf-ext")
public interface PdfFileExtensionExt extends CyberExtension {

	@JsonProperty("version")
	@JsonPropertyDescription("Specifies the decimal version number of the Optional<String> from the PDF header that specifies the version of the PDF specification to which the PDF file conforms. E.g., '1.4'.")
	Optional<String> getVersion();
	
	@JsonProperty("is_optimized")
	@JsonPropertyDescription("Specifies whether the PDF file has been optimized.")
	Boolean isOptimized();
	
	@JsonProperty("document_info_dict")
	@JsonPropertyDescription("Specifies details of the PDF document information dictionary (DID), which includes properties like the document creation data and producer, as a dictionary.")
	Map<String,String> getDocumentInfoDict();
	
	@JsonProperty("pdfid0")
	@JsonPropertyDescription("Specifies the first file identifier found for the PDF file.")
	Optional<String> getPdfid0();
	
	@JsonProperty("pdfid1")
	@JsonPropertyDescription("Specifies the second file identifier found for the PDF file.")
	Optional<String> getPdfid1();

}
