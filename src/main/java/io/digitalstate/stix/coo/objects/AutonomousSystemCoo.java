package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * autonomous-system
 * <p>
 * The AS object represents the properties of an Autonomous Systems (AS).
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "autonomous-system", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("autonomous-system")
@JsonSerialize(as = AutonomousSystem.class) @JsonDeserialize(builder = AutonomousSystem.Builder.class)
@JsonPropertyOrder({"type", "extensions", "number", "name", "rir"})
public interface AutonomousSystemCoo extends CyberObservableObject {

    @JsonProperty("number") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the number assigned to the AS. Such assignments are typically performed by a Regional Internet Registries (RIR)")
    @Redactable
    Optional<Long> getNumber();

    @JsonProperty("name") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the name of the AS.")
    @Redactable
    Optional<String> getName();

    @JsonProperty("rir") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Specifies the name of the Regional Internet Registry (RIR) that assigned the number to the AS.")
    @Redactable
    Optional<String> getRir();

}
