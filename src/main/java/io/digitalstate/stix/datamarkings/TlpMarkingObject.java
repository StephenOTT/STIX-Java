package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;

@Value.Immutable
@Value.Style(typeImmutable = "Tlp", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = Tlp.class) @JsonDeserialize(builder = Tlp.Builder.class)
public interface TlpMarkingObject extends SdoDefaultValidator, StixMarkingObject {

    @NotNull
    @JsonProperty("tlp")
    String getTlp();

    @JsonIgnore
    @Value.Check
    default void checkTlpValue() {
//        Preconditions.checkState(getKillChainPhases().size() >= 1,
//                "Must have at least 1 kill chain phase defined");
    }

}
