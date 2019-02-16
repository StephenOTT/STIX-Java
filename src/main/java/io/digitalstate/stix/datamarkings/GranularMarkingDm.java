package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.json.converters.dehydrated.MarkingDefinitionConverter;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Value.Immutable @Serial.Version(1L)
@Value.Style(typeAbstract="*Dm", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, depluralize = true)
@JsonSerialize(as = GranularMarking.class) @JsonDeserialize(builder = GranularMarking.Builder.class)
@Redactable
public interface GranularMarkingDm extends StixCustomProperties, SdoDefaultValidator, Serializable {

    @NotNull
    @JsonProperty("marking_ref")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = MarkingDefinitionConverter.class)
    MarkingDefinitionDm getMarkingRef();

    @Size(min = 1, message = "Must have as least 1 selector")
    @JsonProperty("selectors")
    Set<String> getSelectors();

}
