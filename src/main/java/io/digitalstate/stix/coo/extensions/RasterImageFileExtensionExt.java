package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
 * The Raster Image file extension specifies a default extension for capturing
 * properties specific to image files.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "raster-image-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = RasterImageFileExtension.class) @JsonDeserialize(builder = RasterImageFileExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "image_height", "image_width", "bits_per_pixel", "image_compression_algorithm", "exif_tags" })
@JsonTypeName("raster-image-ext")
public interface RasterImageFileExtensionExt extends CyberExtension {
	
	@JsonProperty("image_height")
	@JsonPropertyDescription("Specifies the height of the image in the image file, in pixels.")
	Optional<Integer> getImageHeight();
	
	@JsonProperty("image_width")
	@JsonPropertyDescription("Specifies the width of the image in the image file, in pixels.")
	Optional<Integer> getImageWidth();
	
	@JsonProperty("bits_per_pixel")
	@JsonPropertyDescription("Specifies the sum of bits used for each color channel in the image in the image file, and thus the total number of pixels used for expressing the color depth of the image.")
	Optional<Integer> getBitsPerPixel();
	
	@JsonProperty("image_compression_algorithm")
	@JsonPropertyDescription("Specifies the name of the compression algorithm used to compress the image in the image file, if applicable.")
	Optional<String> getImageCompressionAlgorithm();
	
	@JsonProperty("exif_tags")
	@JsonPropertyDescription("Specifies the set of EXIF tags found in the image file, as a dictionary. Each key/value pair in the dictionary represents the name/value of a single EXIF tag.")
	@Valid
	Map<String,Object> getExifTags();

}
