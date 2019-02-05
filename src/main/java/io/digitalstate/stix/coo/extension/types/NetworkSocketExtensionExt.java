package io.digitalstate.stix.coo.extension.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;
import io.digitalstate.stix.coo.objects.NetworkTrafficCoo;
import io.digitalstate.stix.validation.contraints.allowedparents.AllowedParents;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * socket-ext
 * <p>
 * The Network Socket extension specifies a default extension for capturing
 * network traffic properties associated with network sockets.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "socket-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class})
@JsonSerialize(as = NetworkSocketExtension.class) @JsonDeserialize(builder = NetworkSocketExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({"address_family", "is_blocking", "is_listening", "protocol_family", "options", "socket_type",
        "socket_descriptor", "socket_handle" })
@JsonTypeName("socket-ext")
@AllowedParents({NetworkTrafficCoo.class})
public interface NetworkSocketExtensionExt extends CyberObservableExtension {

    @JsonProperty("address_family")
    @JsonPropertyDescription("Specifies the address family (AF_*) that the socket is configured for.")
    @NotNull
    AddressFamily getAddressFamily();

    @JsonProperty("is_blocking")
    @JsonPropertyDescription("Specifies whether the socket is in blocking mode.")
    Optional<Boolean> getBlocking();

    @JsonProperty("is_listening")
    @JsonPropertyDescription("Specifies whether the socket is in listening mode.")
    Optional<Boolean> getListening();

    @JsonProperty("protocol_family")
    @JsonPropertyDescription("Specifies the protocol family (PF_*) that the socket is configured for.")
    Optional<ProtocolFamily> getProtocolFamily();

    @JsonProperty("options")
    @JsonPropertyDescription("Specifies any options (SO_*) that may be used by the socket, as a dictionary.")
    Map<String,String> getOptions();

    @JsonProperty("socket_type")
    @JsonPropertyDescription("Specifies the type of the socket.")
    Optional<SocketType> getSocketType();

    @JsonProperty("socket_descriptor")
    @JsonPropertyDescription("Specifies the socket file descriptor value associated with the socket, as a non-negative integer.")
    Optional<Integer> getSocketDescriptor();

    @JsonProperty("socket_handle")
    @JsonPropertyDescription("Specifies the handle or inode value associated with the socket.")
    Optional<Integer> getSocketHandle();

    @JsonTypeName("network-socket-address-family-enum")
    public enum AddressFamily {
        AF_UNSPEC,
        AF_INET,
        AF_IPX,
        AF_APPLETALK,
        AF_NETBIOS,
        AF_INET_6,
        AF_IRDA,
        AF_BTH;
    }

    @JsonTypeName(" network-socket-protocol-family-enum")
    public enum ProtocolFamily {
        PF_INET,
        PF_AX25,
        PF_IPX,
        PF_INET_6,
        PF_APPLETALK,
        PF_NETROM,
        PF_BRIDGE,
        PF_ATMPVC,
        PF_X25,
        PF_ROSE,
        PF_DECNET,
        PF_NETBEUI,
        PF_SECURITY,
        PF_KEY,
        PF_NETLINK,
        PF_ROUTE,
        PF_PACKET,
        PF_ASH,
        PF_ECONET,
        PF_ATMSVC,
        PF_SNA,
        PF_IRDA,
        PF_PPPOX,
        PF_WANPIPE,
        PF_BLUETOOTH;
    }

    @JsonTypeName("network-socket-type-enum")
    public enum SocketType {
        SOCK_STREAM,
        SOCK_DGRAM,
        SOCK_RAW,
        SOCK_RDM,
        SOCK_SEQPACKET;
    }
}
