package io.digitalstate.stix.sro.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.objects.IdentitySdo;
import io.digitalstate.stix.sdo.objects.ObservedDataSdo;
import io.digitalstate.stix.sro.RelationshipObject;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "Sighting", validationMethod = Value.Style.ValidationMethod.NONE)
public interface SightingSro extends RelationshipObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "sighting";
    }

    @JsonProperty("first_seen")
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen")
    Optional<Instant> getLastSeen();

    @JsonProperty("count")
    Optional<Integer> getCount();

    @JsonProperty("sighting_of_ref")
    Optional<DomainObject> getSightingOfRef();

    @JsonProperty("observed_data_refs")
    Set<ObservedDataSdo> getObservedDataRefs();

    @JsonProperty("where_sighted_refs")
    Set<IdentitySdo> getWhereSightedRefs();

    @NotNull
    @JsonProperty("summary")
    default boolean getSummary(){
        return false;
    }

}
