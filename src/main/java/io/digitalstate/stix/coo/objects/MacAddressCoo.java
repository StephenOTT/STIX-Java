package io.digitalstate.stix.coo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

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

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
/**
 * mac-addr
 * <p>
 * The MAC Address Object represents a single Media Access Control (MAC) address.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "mac-addr", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("mac-addr")
@JsonSerialize(as = MacAddress.class) @JsonDeserialize(builder = MacAddress.Builder.class)
@JsonPropertyOrder({"type", "extensions", "value"})
public interface MacAddressCoo extends CyberObservableObject {
	/**
	* The MAC address value MUST be represented as a single colon-delimited, lowercase MAC-48 address,
	* which MUST include leading zeros for each octet.
	* (Required)
	* 
	*/
	@JsonProperty("value") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies one or more mac addresses expressed using CIDR notation.")
	@Pattern(regexp="^([0-9a-f]{2}[:]){5}([0-9a-f]{2})$")
	String getValue();
}
