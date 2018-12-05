package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "GranularMarking", validationMethod = Value.Style.ValidationMethod.NONE)
public interface GranularMarkingDm extends SdoDefaultValidator {

    @NotNull
    @JsonProperty("marking_ref")
    DomainObject getMarkingRef();

    @Size(min = 1, message = "Must have as least 1 selector")
    @JsonProperty("selectors")
    Set<String> getSelectors();

}
