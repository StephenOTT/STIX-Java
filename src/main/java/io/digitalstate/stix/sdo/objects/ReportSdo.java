package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.json.converters.dehydrated.BundleableObjectSetConverter;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.ReportLabels;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable @Serial.Version(1L)
@JsonTypeName("report")
@DefaultTypeValue(value = "report", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Report.class) @JsonDeserialize(builder = Report.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "published", "object_refs"})
@Redactable
public interface ReportSdo extends DomainObject {

    @Override
    @NotNull
    @Vocab(ReportLabels.class)
    @Redactable(useMask = true)
    Set<@Length(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("published")
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable(useMask = true)
    Instant getPublished();

    @NotNull @Size(min = 1, message = "Must have at least one Report object reference")
    @JsonProperty("object_refs")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize( converter = BundleableObjectSetConverter.class)
    @Redactable(useMask = true)
    Set<BundleableObject> getObjectRefs();

}
