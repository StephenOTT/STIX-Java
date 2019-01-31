package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

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
import io.digitalstate.stix.validation.OptionalPattern;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
 * tcp-ext
 * <p>
 * The TCP extension specifies a default extension for capturing network traffic
 * properties specific to TCP.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "tcp-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = TcpExtension.class) @JsonDeserialize(builder = TcpExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "src_flags_hex", "dst_flags_hex" })
@JsonTypeName("tcp-ext")
public interface TcpExtensionExt extends CyberExtension {
	/**
	 * Specifies the source TCP flags, as the union of all TCP flags observed
	 * between the start of the traffic (as defined by the start property) and
	 * the end of the traffic (as defined by the end property).
	 * 
	 */
	@JsonProperty("src_flags_hex")
	@JsonPropertyDescription("Specifies the source TCP flags, as the union of all TCP flags observed between the start of the traffic (as defined by the start property) and the end of the traffic (as defined by the end property). ")
	@OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
	Optional<String> getSrcFlagsHex();

	/**
	 * Specifies the destination TCP flags, as the union of all TCP flags
	 * observed between the start of the traffic (as defined by the start
	 * property) and the end of the traffic (as defined by the end property).
	 * 
	 */
	@JsonProperty("dst_flags_hex")
	@JsonPropertyDescription("Specifies the destination TCP flags, as the union of all TCP flags observed between the start of the traffic (as defined by the start property) and the end of the traffic (as defined by the end property).")
	@OptionalPattern(regexp = "^([a-fA-F0-9]{2})+$")
	Optional<String> getDstFlagsHex();

}
