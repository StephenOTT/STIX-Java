package io.digitalstate.stix.sdo.impl;

import io.digitalstate.stix.sdo.ObservedData;
import io.digitalstate.stix.sdo.impl.properties.ObservedDataProperties;
import io.digitalstate.stix.sdo.impl.types.CyberObservableObject;
import io.digitalstate.stix.sdo.impl.types.ExternalReference;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ObservedDataSdo extends ObservedDataProperties implements ObservedData {

    private static final String TYPE = "observed-data";

    public ObservedDataSdo(LocalDateTime firstObserved,
                           LocalDateTime lastObserved,
                           Integer numberObserved,
                           List<CyberObservableObject> objects){

        setId(generateId());
        setType(TYPE);
        setFirstObserved(firstObserved);
        setLastObserved(lastObserved);
        setNumberObserved(numberObserved);
        setObjects(objects);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        if (StringUtils.isNotBlank(type)){
            this.type = type;
        } else {
            throw new IllegalArgumentException("type cannot be null or blank");
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        if (StringUtils.isNotBlank(id)){
            this.id = String.join("--", TYPE, id);
        } else {
            throw new IllegalArgumentException("Id can't be null or blank");
        }
    }

    public String generateId(){
        UUID uuid = UUID.randomUUID();
        return String.join("--", TYPE, uuid.toString());
    }

    @Override
    public String getCreatedByRef() {
        return this.createdByRef;
    }

    @Override
    public void setCreatedByRef(String createdByRef) {
        this.createdByRef = createdByRef;
    }

    @Override
    public LocalDateTime getCreated() {
        return this.created;
    }

    @Override
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public LocalDateTime getModified() {
        return this.modified;
    }

    @Override
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public Boolean revoked() {
        return this.revoked;
    }

    @Override
    public void revoked(Boolean revoked) {
        this.revoked = revoked;
    }

    @Override
    public List<String> getLabels() {
        return this.labels;
    }

    @Override
    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public List<ExternalReference> getExternalReferences() {
        return this.externalReferences;
    }

    @Override
    public void setExternalReferences(List<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    @Override
    public List<String> getObjectMarkingRefs() {
        return this.objectMarkingRefs;
    }

    @Override
    public void setObjectMarkingRefs(List<String> objectMarkingRefs) {
        this.objectMarkingRefs = objectMarkingRefs;
    }

    @Override
    public List<String> getGranularMarkings() {
        return this.granularMarkings;
    }

    @Override
    public void setGranularMarkings(List<String> granularMarkings) {
        this.granularMarkings = granularMarkings;
    }

    @Override
    public LocalDateTime getFirstObserved() {
        return this.firstObserved;
    }

    @Override
    public void setFirstObserved(LocalDateTime firstObserved) {
        if (firstObserved != null){
            this.firstObserved = firstObserved;
        } else {
            throw new IllegalArgumentException("firstObserved can't be null");
        }
    }

    @Override
    public LocalDateTime getLastObserved() {
        return this.lastObserved;
    }

    @Override
    public void setLastObserved(LocalDateTime lastObserved) {
        if (lastObserved != null){
            this.lastObserved = lastObserved;
        } else {
            throw new IllegalArgumentException("lastObserved can't be null");
        }
    }

    @Override
    public Integer getNumberObserved() {
        return this.numberObserved;
    }

    @Override
    public void setNumberObserved(Integer numberObserved) {
        if (numberObserved != null && numberObserved >= 1){
            this.numberObserved = numberObserved;
        } else {
            throw new IllegalArgumentException("numberObserved can't be null and must be greater or equal to 1");
        }
    }

    @Override
    public List<CyberObservableObject> getObjects() {
        return this.objects;
    }

    @Override
    public void setObjects(List<CyberObservableObject> objects) {
        if (!objects.isEmpty()){
            this.objects = objects;
        } else {
            throw new IllegalArgumentException("Cannot be null and at least one CyberObservableObject must be provided");
        }
    }
}
