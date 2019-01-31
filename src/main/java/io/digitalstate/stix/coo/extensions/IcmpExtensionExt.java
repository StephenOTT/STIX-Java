package io.digitalstate.stix.coo.extensions;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
 * icmp-ext
 * <p>
 * The ICMP extension specifies a default extension for capturing network
 * traffic properties specific to ICMP.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "icmp-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = IcmpExtension.class) @JsonDeserialize(builder = IcmpExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "icmp_type_hex", "icmp_code_hex" })
@JsonTypeName("icmp-ext")
public interface IcmpExtensionExt extends CyberExtension {

	@JsonProperty("icmp_type_hex")
	@JsonPropertyDescription("Specifies the ICMP type byte.")
	@Pattern(regexp = "^([a-fA-F0-9]{2})+$")
	@NotNull
	String getOcmpTypeHex();

	@JsonProperty("icmp_code_hex")
	@JsonPropertyDescription("Specifies the ICMP code byte.")
	@Pattern(regexp = "^([a-fA-F0-9]{2})+$")
	@NotNull
	String getIcmpCodeHex();

}
