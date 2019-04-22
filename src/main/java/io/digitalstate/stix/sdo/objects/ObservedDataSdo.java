package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.coo.json.observables.CyberObservableSetFieldDeserializer;
import io.digitalstate.stix.coo.json.observables.CyberObservableSetFieldSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.json.StixDateDeserializer;
import io.digitalstate.stix.json.StixDateSerializer;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.hibernate.validator.constraints.Range;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;
/**
 * observed-data
 * <p>
 * Observed data conveys information that was observed on systems and networks, such as log data or network traffic, using the Cyber Observable specification.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("observed-data")
@DefaultTypeValue(value = "observed-data", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = ObservedData.class) @JsonDeserialize(builder = ObservedData.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "first_observed", "last_observed",
        "number_observed", "objects"})
@Redactable
public interface ObservedDataSdo extends DomainObject {

    @NotNull
    @JsonProperty("first_observed")
    @JsonPropertyDescription("The beginning of the time window that the data was observed during.")
    @JsonSerialize(using = StixDateSerializer.class) @JsonDeserialize(using = StixDateDeserializer.class)
    @Redactable(useMask = true)
    Instant getFirstObserved();

    @NotNull
    @JsonProperty("last_observed")
    @JsonPropertyDescription("The end of the time window that the data was observed during.")
    @JsonSerialize(using = StixDateSerializer.class) @JsonDeserialize(using = StixDateDeserializer.class)
    @Redactable(useMask = true)
    Instant getLastObserved();

    @NotNull @Positive
    @JsonProperty("number_observed")
    @JsonPropertyDescription("The number of times the data represented in the objects property was observed. This MUST be an integer between 1 and 999,999,999 inclusive.")
    @Redactable(useMask = true)
    @Range(min = 1, max = 999999999)
    Integer getNumberObserved();

    @NotNull @Size(min = 1, message = "At least one Cyber Observable Reference must be provided")
    @JsonProperty("objects")
    @JsonPropertyDescription("A dictionary of Cyber Observable Objects that describes the single 'fact' that was observed.")
    @Redactable(useMask = true)
    @JsonSerialize(using = CyberObservableSetFieldSerializer.class)
    @JsonDeserialize(using = CyberObservableSetFieldDeserializer.class)
    Set<CyberObservableObject> getObjects();

}
