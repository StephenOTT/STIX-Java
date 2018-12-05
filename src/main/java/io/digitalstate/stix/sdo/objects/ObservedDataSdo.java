package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "ObservedData", validationMethod = Value.Style.ValidationMethod.NONE)
public interface ObservedDataSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "observed-data";
    }

    @NotNull
    @JsonProperty("first_observed")
    Instant getFirstObserved();

    @NotNull
    @JsonProperty("last_observed")
    Instant getLastObserved();

    @NotNull @Positive
    @JsonProperty("number_observed")
    int getNumberObserved();

    @NotNull @Size(min = 1, message = "At least one Cyber Observable Reference must be provided")
    @JsonProperty("objects")
    Set<String> getObjects();
    //@TODO Refactor to use Cyber Observables

}
