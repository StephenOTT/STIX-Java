package io.digitalstate.stix.coo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;


/**
 * network-traffic
 * <p>
 * The Network Traffic Object represents arbitrary network traffic that
 * originates from a source and is addressed to a destination.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "network-traffic", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = NetworkTraffic.class) @JsonDeserialize(builder = NetworkTraffic.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonTypeName("network-traffic")
@JsonPropertyOrder({ "type", "extensions", "start", "end", "src_ref", "dst_ref", "src_port", "dst_port", "protocols", "src_byte_count",
		"dst_byte_count", "src_packets", "dst_packets", "ipfix", "src_payload_ref", "dst_payload_ref",
		"encapsulates_refs", "encapsulated_by_ref" })
public interface NetworkTrafficCoo extends CyberObservableObject {

	@JsonProperty("start")
	@JsonPropertyDescription("Specifies the date/time the network traffic was initiated, if known.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Instant getStart();
	
	@JsonProperty("end")
	@JsonPropertyDescription("Specifies the date/time the network traffic ended, if known.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
	Instant getEnd();
	
	@JsonProperty("is_active")
	@JsonPropertyDescription("Indicates whether the network traffic is still ongoing.")
	Optional<Boolean> isActive();

	/*
	 * Must be of type ipv4-addr, ipv6-addr, mac-addr, or domain-name
	 */
	@JsonProperty("src_ref")
	@JsonPropertyDescription("Specifies the source of the network traffic, as a reference to one or more Observable Objects.")
	Optional<String> srcRef();

	/*
	 * Must be of type ipv4-addr, ipv6-addr, mac-addr, or domain-name
	 */
	@JsonProperty("dst_ref")
	@JsonPropertyDescription("Specifies the destination of the network traffic, as a reference to one or more Observable Objects.")
	Optional<String> dstRef();

	@JsonProperty("src_port")
	@JsonPropertyDescription("Specifies the source port used in the network traffic, as an integer. The port value MUST be in the range of 0 - 65535.")
	@DecimalMin("0")
	@DecimalMax("65535")
	Optional<Integer> srcPort();
	
	@JsonProperty("dst_port")
	@JsonPropertyDescription("Specifies the destination port used in the network traffic, as an integer. The port value MUST be in the range of 0 - 65535.")
	@DecimalMin("0")
	@DecimalMax("65535")
	Optional<Integer> dstPort();
	
	/*
	 * Specifies the protocols observed in the network traffic, along with their
	 * corresponding state.  Protocols MUST be listed in low to high order, from outer to inner in terms of packet encapsulation. 
	 * That is, the protocols in the outer level of the packet, such as IP, MUST be listed first.
	 * The protocol names SHOULD come from the service names defined in the Service Name column of th
	 * IANA Service Name and Port Number Registry
	 * 
	 */
	@JsonProperty("protocols")
	@JsonPropertyDescription("Specifies the protocols observed in the network traffic, along with their corresponding state.")
	@NotNull
	Set<String> protocols();
	
	@JsonProperty("src_byte_count")
	@JsonPropertyDescription("Specifies the number of bytes sent from the source to the destination.")
	Optional<Integer> srcByteCount();

	@JsonProperty("dst_byte_count")
	@JsonPropertyDescription("Specifies the number of bytes sent from the destination to the source.")
	Optional<Integer> dstByteCount();

	@JsonProperty("src_packets")
	@JsonPropertyDescription("Specifies the number of packets sent from the source to the destination.")
	Optional<Integer> srcPackets();

	@JsonProperty("dst_packets")
	@JsonPropertyDescription("Specifies the number of packets sent destination to the source.")
	Optional<Integer> dstPackets();

	/*
	 * Objects much be Integers or Strings
	 */
	@JsonProperty("ipfix")
	@JsonPropertyDescription("Specifies any IP Flow Information Export [IPFIX] data for the traffic, as a dictionary.")
	@Valid
	Map<String,Object> ipfix();
	
	/*
	 * Must be of type artifact
	 */
	@JsonProperty("src_payload_ref")
	@JsonPropertyDescription("Specifies the bytes sent from the source to the destination.")
	Optional<String> srcPayloadRef();

	/*
	 * Must be of type artifact
	 */
	@JsonProperty("dst_payload_ref")
	@JsonPropertyDescription("Specifies the bytes sent from the source to the destination.")
	Optional<String> dstPayloadRef();

	/*
	 * Must be of type network-traffic
	 */
	@JsonProperty("encapsulates_refs")
	@JsonPropertyDescription("Links to other network-traffic objects encapsulated by a network-traffic.")
	@Valid
	List<String> encapsulatesRefs();

	/*
	 * Must be of type network-traffic
	 */
	@JsonProperty("encapsulated_by_ref")
	@JsonPropertyDescription("Links to another network-traffic object which encapsulates this object.")
	Optional<String> encapsulatedByRef();

}
