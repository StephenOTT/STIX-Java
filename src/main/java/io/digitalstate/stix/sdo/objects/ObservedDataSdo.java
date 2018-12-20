package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Value.Immutable
@JsonTypeName("observed-data")
@DefaultTypeValue(value = "observed-data", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = ObservedData.class) @JsonDeserialize(builder = ObservedData.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "first_observed", "last_observed",
        "number_observed", "objects"})
@Redactable
public interface ObservedDataSdo extends DomainObject {

    @NotNull
    @JsonProperty("first_observed")
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable(useMask = true)
    Instant getFirstObserved();

    @NotNull
    @JsonProperty("last_observed")
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable(useMask = true)
    Instant getLastObserved();

    @NotNull @Positive
    @JsonProperty("number_observed")
    @Redactable(useMask = true)
    int getNumberObserved();

    @NotNull @Size(min = 1, message = "At least one Cyber Observable Reference must be provided")
    @JsonProperty("objects")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @Redactable(useMask = true)
    Set<String> getObjects();
    //@TODO Refactor to use Cyber Observables

}
