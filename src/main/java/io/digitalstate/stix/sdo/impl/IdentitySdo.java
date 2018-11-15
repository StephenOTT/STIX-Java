package io.digitalstate.stix.sdo.impl;

import io.digitalstate.stix.sdo.Identity;
import io.digitalstate.stix.sdo.impl.properties.IdentityProperties;
import io.digitalstate.stix.sdo.impl.types.ExternalReference;
import io.digitalstate.stix.sdo.vocab.IdentityClass;
import io.digitalstate.stix.sdo.vocab.Sectors;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class IdentitySdo extends IdentityProperties implements Identity {

    private static final String TYPE = "identity";

    public IdentitySdo(String name, Enum<IdentityClass> identityClassEnum){
        setId(generateId());
        setName(name);
        setType(TYPE);
        setIdentityClass(identityClassEnum);
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
    public Enum<IdentityClass> getIdentityClass() {
        return this.identityClass;
    }

    @Override
    public void setIdentityClass(Enum<IdentityClass> identityClassEnum) {
        if (identityClass != null){
            this.identityClass = identityClassEnum;
        } else {
            throw new IllegalArgumentException("Identity Class can't be null or blank");
        }
    }

    @Override
    public List<Enum<Sectors>> getSectors() {
        return this.sectors;
    }

    @Override
    public void setSectors(List<Enum<Sectors>> sectors) {
        this.sectors = sectors;
    }

    @Override
    public String getContactInformation() {
        return this.contactInformation;
    }

    @Override
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
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
