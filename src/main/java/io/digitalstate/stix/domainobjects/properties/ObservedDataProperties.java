package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "first_observed", "last_observed",
        "number_observed", "objects", "objective"})
public abstract class ObservedDataProperties extends CommonProperties{

    @JsonProperty("first_observed")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime firstObserved = null;

    @JsonProperty("last_observed")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime lastObserved = null;

    @JsonProperty("number_observed")
    @JsonInclude(NON_NULL)
    private Integer numberObserved = null;

    private HashMap<String,CyberObservableObject> objects;

    @JsonInclude(NON_NULL)
    private String objective = null;

    //
    // Getters and Setters
    //

    public ZonedDateTime getFirstObserved() {
        return firstObserved;
    }

    public void setFirstObserved(ZonedDateTime firstObserved) {
        if (firstObserved != null){
            this.firstObserved = firstObserved;
        } else {
            throw new IllegalArgumentException("firstObserved can't be null");
        }
    }

    public ZonedDateTime getLastObserved() {
        return lastObserved;
    }

    public void setLastObserved(ZonedDateTime lastObserved) {
        if (lastObserved != null){
            this.lastObserved = lastObserved;
        } else {
            throw new IllegalArgumentException("lastObserved can't be null");
        }
    }

    public Integer getNumberObserved() {
        return numberObserved;
    }

    public void setNumberObserved(Integer numberObserved) {
        if (numberObserved != null && numberObserved >= 1){
            this.numberObserved = numberObserved;
        } else {
            throw new IllegalArgumentException("numberObserved can't be null and must be greater or equal to 1");
        }
    }

    public HashMap<String,CyberObservableObject> getObjects() {
        return objects;
    }

    public void setObjects(HashMap<String,CyberObservableObject> objects) {
        if (!objects.isEmpty()){
            this.objects = objects;
        } else {
            throw new IllegalArgumentException("Cannot be null and at least one CyberObservableObjectCommonProperties must be provided");
        }
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();
        return bundleObjects;
    }
}
