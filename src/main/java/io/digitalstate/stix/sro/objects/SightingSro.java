package io.digitalstate.stix.sro.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.json.converters.dehydrated.DomainObjectConverter;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.objects.IdentitySdo;
import io.digitalstate.stix.sdo.objects.ObservedDataSdo;
import io.digitalstate.stix.sro.RelationshipObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@Value.Style(typeAbstract="*Sro", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class, JsonTypeInfo.class})
@DefaultTypeValue(value = "sighting", groups = {DefaultValuesProcessor.class})
@JsonSerialize(as = Sighting.class) @JsonDeserialize(builder = Sighting.Builder.class)
public interface SightingSro extends RelationshipObject {

    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("first_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<Instant> getFirstSeen();

    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("last_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<Instant> getLastSeen();

    @JsonProperty("count") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<Integer> getCount();

    @JsonProperty("sighting_of_ref")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectConverter.class)
    DomainObject getSightingOfRef();

    @JsonProperty("observed_data_refs") @JsonInclude(NON_EMPTY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectConverter.class)
    Set<ObservedDataSdo> getObservedDataRefs();

    @JsonProperty("where_sighted_refs") @JsonInclude(NON_EMPTY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectConverter.class)
    Set<IdentitySdo> getWhereSightedRefs();

    @NotNull
    @JsonProperty("summary") @JsonInclude(NON_EMPTY)
    default boolean getSummary(){
        return false;
    }

}
