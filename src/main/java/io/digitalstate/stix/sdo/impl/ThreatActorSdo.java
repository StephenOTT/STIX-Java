package io.digitalstate.stix.sdo.impl;

import io.digitalstate.stix.sdo.ThreatActor;
import io.digitalstate.stix.sdo.impl.properties.ThreatActorProperties;
import io.digitalstate.stix.sdo.impl.types.ExternalReference;
import io.digitalstate.stix.sdo.vocab.*;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ThreatActorSdo extends ThreatActorProperties implements ThreatActor {

    private static final String TYPE = "threat-actor";

    public ThreatActorSdo(String name,
                          List<Enum<ThreatActorLabel>> threatActorLabels){
        setId(generateId());
        setType(TYPE);
        setName(name);
        setLabels(threatActorLabels.stream()
                .map(Enum::toString)
                .collect(Collectors.toList()));
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
            throw new IllegalArgumentException("Name cannot be null or blank");
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
    public List<Enum<ThreatActorRole>> getRoles() {
        return this.roles;
    }

    @Override
    public void setRoles(List<Enum<ThreatActorRole>> roles) {
        this.roles = roles;
    }

    @Override
    public List<String> getGoals() {
        return this.goals;
    }

    @Override
    public void setGoals(List<String> goals) {
        this.goals = goals;
    }

    @Override
    public Enum<ThreatActorSophistication> getSophistication() {
        return this.sophistication;
    }

    @Override
    public void setSophistication(Enum<ThreatActorSophistication> sophistication) {
        this.sophistication = sophistication;
    }

    @Override
    public Enum<AttackResourceLevel> getResourceLevel() {
        return this.resourceLevel;
    }

    @Override
    public void setResourceLevel(Enum<AttackResourceLevel> resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    @Override
    public Enum<AttackMotivation> getPrimaryMotivation() {
        return this.primaryMotivation;
    }

    @Override
    public void setPrimaryMotivation(Enum<AttackMotivation> primaryMotivation) {
        this.primaryMotivation = primaryMotivation;
    }

    @Override
    public List<Enum<AttackMotivation>> getSecondaryMotivations() {
        return this.secondaryMotivations;
    }

    @Override
    public void setSecondaryMotivations(List<Enum<AttackMotivation>> secondaryMotivations) {
        this.secondaryMotivations = secondaryMotivations;
    }

    @Override
    public List<Enum<AttackMotivation>> getPersonalMotivations() {
        return this.personalMotivations;
    }

    @Override
    public void setPersonalMotivations(List<Enum<AttackMotivation>> personalMotivations) {
        this.personalMotivations = personalMotivations;
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
        if (labels != null && !labels.isEmpty()){
            this.labels = labels;
        } else {
            throw new IllegalArgumentException("At least one label must be provided for a ThreatActor");
        }
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
