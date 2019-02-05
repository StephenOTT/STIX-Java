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
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * icmp-ext
 * <p>
 * The ICMP extension specifies a default extension for capturing network
 * traffic properties specific to ICMP.
 *
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "icmp-ext", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Ext", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, passAnnotations = {AllowedParents.class})
@JsonSerialize(as = IcmpExtension.class) @JsonDeserialize(builder = IcmpExtension.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "icmp_type_hex", "icmp_code_hex" })
@JsonTypeName("icmp-ext")
@AllowedParents({NetworkTrafficCoo.class})
public interface IcmpExtensionExt extends CyberObservableExtension {

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
