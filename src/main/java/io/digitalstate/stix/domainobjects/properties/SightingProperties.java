package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.ObservedData;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "first_seen", "last_seen",
        "count", "sighting_of_ref", "observed_data_refs", "where_sighted_refs",
        "summary"})
public abstract class SightingProperties extends CommonProperties{

    @JsonProperty("first_seen")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime firstSeen = null;

    @JsonProperty("last_seen")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime lastSeen = null;

    @JsonProperty("count")
    @JsonInclude(NON_NULL)
    protected Integer count = null;

    private StixDomainObject sightingOfRef;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<ObservedData> observedDataRefs = null;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<Identity> whereSightedRefs = null;

    /**
     * The summary property indicates whether the Sighting should be considered summary data.
     * Summary data is an aggregation of previous Sightings reports and should not be considered primary source data.
     * Default value is false.
     */
    private boolean summary = false;


    //
    // Getters and Setters
    //

    public ZonedDateTime getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(ZonedDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }

    public ZonedDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(ZonedDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        Objects.requireNonNull(count, "count cannot be null");
        this.count = count;
    }

    @JsonIgnore
    public StixDomainObject getSightingOfRef() {
        return sightingOfRef;
    }

    public void setSightingOfRef(StixDomainObject sightingOfRef) {
        Objects.requireNonNull(sightingOfRef, "sightingOfRed cannot be null");
        this.sightingOfRef = sightingOfRef;
    }

    @JsonProperty("sighting_of_ref")
    public String getSightingOfRefAsString(){
        return getSightingOfRef().getId();
    }

    @JsonIgnore
    public LinkedHashSet<ObservedData> getObservedDataRefs() {
        return observedDataRefs;
    }

    public void setObservedDataRefs(LinkedHashSet<ObservedData> observedDataRefs) {
        Objects.requireNonNull(observedDataRefs, "observedDataRefs cannot be null");
        this.observedDataRefs = observedDataRefs;
    }

    @JsonProperty("observed_data_refs")
    @JsonInclude(NON_NULL)
    public LinkedHashSet<String> getObservedDataRefsAsStrings() {
        if (getObservedDataRefs() != null) {
            return this.getObservedDataRefs().stream()
                    .map(ObservedData::getId)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else {
            return null;
        }
    }

    @JsonIgnore
    public LinkedHashSet<Identity> getWhereSightedRefs() {
        return whereSightedRefs;
    }

    public void setWhereSightedRefs(LinkedHashSet<Identity> whereSightedRefs) {
        Objects.requireNonNull(whereSightedRefs, "whereSightedRefs cannot be null");
        this.whereSightedRefs = whereSightedRefs;
    }

    @JsonProperty("where_sighted_refs")
    @JsonInclude(NON_NULL)
    public LinkedHashSet<String> getWhereSightedRefsAsStrings() {
        if (getWhereSightedRefs() != null) {
            return this.getWhereSightedRefs().stream()
                    .map(Identity::getId)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else {
            return null;
        }
    }

    public boolean isSummary() {
        return summary;
    }

    public void setSummary(boolean summary) {
        this.summary = summary;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        if (getSightingOfRef() != null){
            bundleObjects.add(getSightingOfRef());
        }

        if (getObservedDataRefs() != null){
            bundleObjects.addAll(getObservedDataRefs());
        }

        if (getWhereSightedRefs() != null){
            bundleObjects.addAll(getWhereSightedRefs());
        }

        return bundleObjects;
    }

    // Disabled Properties:


}
