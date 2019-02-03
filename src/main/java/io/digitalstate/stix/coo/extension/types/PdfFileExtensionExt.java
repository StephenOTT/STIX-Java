package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
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
public interface PdfFileExtensionExt extends CyberObservableExtension {

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