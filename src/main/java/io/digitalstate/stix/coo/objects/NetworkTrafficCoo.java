package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.hibernate.validator.constraints.Range;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;


/**
 * network-traffic
 * <p>
 * The Network Traffic Object represents arbitrary network traffic that
 * originates from a source and is addressed to a destination.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "network-traffic", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
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
    Optional<Instant> getStart();

    @JsonProperty("end")
    @JsonPropertyDescription("Specifies the date/time the network traffic ended, if known.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    Optional<Instant> getEnd();

    @JsonProperty("is_active")
    @JsonPropertyDescription("Indicates whether the network traffic is still ongoing.")
    @NotNull
    Optional<Boolean> isActive();

    /*
     * Must be of type ipv4-addr, ipv6-addr, mac-addr, or domain-name
     */
    @JsonProperty("src_ref")
    @JsonPropertyDescription("Specifies the source of the network traffic, as a reference to one or more Observable Objects.")
    Optional<String> getSrcRef();

    /*
     * Must be of type ipv4-addr, ipv6-addr, mac-addr, or domain-name
     */
    @JsonProperty("dst_ref")
    @JsonPropertyDescription("Specifies the destination of the network traffic, as a reference to one or more Observable Objects.")
    Optional<String> getDstRef();

    @JsonProperty("src_port")
    @JsonPropertyDescription("Specifies the source port used in the network traffic, as an integer. The port value MUST be in the range of 0 - 65535.")
    Optional<@Range(min = 0, max = 65535 ) Integer> getSrcPort();

    @JsonProperty("dst_port")
    @JsonPropertyDescription("Specifies the destination port used in the network traffic, as an integer. The port value MUST be in the range of 0 - 65535.")
    Optional<@Range(min = 0, max = 65535) Integer> getDstPort();

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
    Set<String> getProtocols();

    @JsonProperty("src_byte_count")
    @JsonPropertyDescription("Specifies the number of bytes sent from the source to the destination.")
    Optional<Long> getSrcByteCount();

    @JsonProperty("dst_byte_count")
    @JsonPropertyDescription("Specifies the number of bytes sent from the destination to the source.")
    Optional<Long> getDstByteCount();

    @JsonProperty("src_packets")
    @JsonPropertyDescription("Specifies the number of packets sent from the source to the destination.")
    Optional<Long> getSrcPackets();

    @JsonProperty("dst_packets")
    @JsonPropertyDescription("Specifies the number of packets sent destination to the source.")
    Optional<Long> getDstPackets();

    /*
     * Objects much be Integers or Strings
     */
    @JsonProperty("ipfix")
    @JsonPropertyDescription("Specifies any IP Flow Information Export [IPFIX] data for the traffic, as a dictionary.")
    Map<String,Object> getIpFix();

    /*
     * Must be of type artifact
     */
    @JsonProperty("src_payload_ref")
    @JsonPropertyDescription("Specifies the bytes sent from the source to the destination.")
    Optional<String> getSrcPayloadRef();

    /*
     * Must be of type artifact
     */
    @JsonProperty("dst_payload_ref")
    @JsonPropertyDescription("Specifies the bytes sent from the source to the destination.")
    Optional<String> getDstPayloadRef();

    /*
     * Must be of type network-traffic
     */
    @JsonProperty("encapsulates_refs")
    @JsonPropertyDescription("Links to other network-traffic objects encapsulated by a network-traffic.")
    Set<String> getEncapsulatesRefs();

    /*
     * Must be of type network-traffic
     */
    @JsonProperty("encapsulated_by_ref")
    @JsonPropertyDescription("Links to another network-traffic object which encapsulates this object.")
    Optional<String> getEncapsulatedByRef();

}
