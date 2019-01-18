package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "autonomous-system", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("autonomous-system")
@JsonSerialize(as = AutonomousSystem.class) @JsonDeserialize(builder = AutonomousSystem.Builder.class)
@JsonPropertyOrder({"type"})
@Redactable
public interface AutonomousSystemCoo extends CyberObservableObject {

    @NotBlank
    @JsonProperty("number") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<Long> getNumber();

    @JsonProperty("name") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getName();

    @JsonProperty("rir") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getRir();

}
