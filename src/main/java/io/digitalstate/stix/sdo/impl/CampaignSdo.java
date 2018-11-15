package io.digitalstate.stix.sdo.impl;

import io.digitalstate.stix.sdo.Campaign;
import io.digitalstate.stix.sdo.impl.properties.CampaignProperties;
import io.digitalstate.stix.sdo.impl.types.ExternalReference;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CampaignSdo extends CampaignProperties implements Campaign {

    private static final String TYPE = "campaign";

    public CampaignSdo(String name){
        setId(generateId());
        setName(name);
        setType(TYPE);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    @Override
    public LocalDateTime getFirstSeen() {
        return this.firstSeen;
    }

    @Override
    public void setFirstSeen(LocalDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }

    @Override
    public LocalDateTime getLastSeen() {
        return this.lastSeen;
    }

    @Override
    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public String getObjective() {
        return this.objective;
    }

    @Override
    public void setObjective(String objective) {
        this.objective = objective;
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

    private String generateId(){
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
}
