package io.digitalstate.stix.sro.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.json.converters.dehydrated.DomainObjectConverter;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.objects.IdentitySdo;
import io.digitalstate.stix.sdo.objects.ObservedDataSdo;
import io.digitalstate.stix.sro.RelationshipObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;

/**
 * sighting
 * <p>
 * A Sighting denotes the belief that something in CTI (e.g., an indicator, malware, tool, threat actor, etc.) was seen.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@Value.Style(typeAbstract="*Sro", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@DefaultTypeValue(value = "sighting", groups = {DefaultValuesProcessor.class})
@JsonSerialize(as = Sighting.class) @JsonDeserialize(builder = Sighting.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "first_seen", "last_seen",
        "count", "sighting_of_ref", "observed_data_refs", "where_sighted_refs",
        "summary"})
@Redactable
public interface SightingSro extends RelationshipObject {

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("first_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The beginning of the time window during which the SDO referenced by the sighting_of_ref property was sighted.")
    @Redactable
    Optional<Instant> getFirstSeen();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("last_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The end of the time window during which the SDO referenced by the sighting_of_ref property was sighted.")
    @Redactable
    Optional<Instant> getLastSeen();

    @JsonProperty("count") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("This is an integer between 0 and 999,999,999 inclusive and represents the number of times the object was sighted.")
    @DecimalMin("0")
    @DecimalMax("999999999")
    @Redactable
    Optional<Integer> getCount();

    @JsonProperty("sighting_of_ref")
    @JsonPropertyDescription("An ID reference to the object that has been sighted.")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectConverter.class)
    @Redactable(useMask = true)
    DomainObject getSightingOfRef();

    @JsonProperty("observed_data_refs") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("A list of ID references to the Observed Data objects that contain the raw cyber data for this Sighting.")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectConverter.class)
    @Redactable
    Set<ObservedDataSdo> getObservedDataRefs();

    @JsonProperty("where_sighted_refs") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("The ID of the Victim Target objects of the entities that saw the sighting.")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectConverter.class)
    @Redactable
    Set<IdentitySdo> getWhereSightedRefs();

    @NotNull
    @JsonProperty("summary") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The summary property indicates whether the Sighting should be considered summary data.")
    @Redactable
    default boolean getSummary(){
        return false;
    }

}
