package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;

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
    protected ZonedDateTime firstObserved = null;

    @JsonProperty("last_observed")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime lastObserved = null;

    @JsonProperty("number_observed")
    @JsonInclude(NON_NULL)
    protected Integer numberObserved = null;

    protected HashMap<String,CyberObservableObject> objects;

    @JsonInclude(NON_NULL)
    protected String objective = null;

    //
    // Getters and Setters
    //

    public ZonedDateTime getFirstObserved() {
        return firstObserved;
    }

    public void setFirstObserved(ZonedDateTime firstObserved) {
        this.firstObserved = firstObserved;
    }

    public ZonedDateTime getLastObserved() {
        return lastObserved;
    }

    public void setLastObserved(ZonedDateTime lastObserved) {
        this.lastObserved = lastObserved;
    }

    public Integer getNumberObserved() {
        return numberObserved;
    }

    public void setNumberObserved(Integer numberObserved) {
        this.numberObserved = numberObserved;
    }

    public HashMap<String,CyberObservableObject> getObjects() {
        return objects;
    }

    public void setObjects(HashMap<String,CyberObservableObject> objects) {
        this.objects = objects;
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

//        bundleObjects.addAll(getTargets());
//        bundleObjects.addAll(getUses());

        return bundleObjects;
    }
}
