package io.digitalstate.stix.sdo.impl;

import io.digitalstate.stix.sdo.Indicator;
import io.digitalstate.stix.sdo.impl.properties.IndicatorProperties;
import io.digitalstate.stix.sdo.impl.types.ExternalReference;
import io.digitalstate.stix.sdo.impl.types.KillChainPhase;
import io.digitalstate.stix.sdo.vocab.IndicatorLabel;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class IndicatorSdo extends IndicatorProperties implements Indicator {

    private static final String TYPE = "indicator";

    public IndicatorSdo(List<Enum<IndicatorLabel>> indicatorLabels,
                        String pattern,
                        LocalDateTime validFrom){

        setId(generateId());
        setType(TYPE);
        setLabels(indicatorLabels.stream()
                .map(Enum::toString)
                .collect(Collectors.toList()));
        setPattern(pattern);
        setValidFrom(validFrom);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotEmpty(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be blank (but it can be null)");
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
    public String getPattern() {

        return this.pattern;
    }

    @Override
    public void setPattern(String pattern) {
        if (StringUtils.isNotBlank(pattern)){
            this.pattern = pattern;
        } else {
            throw new IllegalArgumentException("Pattern cannot be null or blank");
        }
    }

    @Override
    public LocalDateTime getValidFrom() {
        return this.validFrom;
    }

    @Override
    public void setValidFrom(LocalDateTime validFrom) {
        if (validFrom != null){
            this.validFrom = validFrom;
        } else {
            throw new IllegalArgumentException("ValidFrom cannot be null");
        }
    }

    @Override
    public LocalDateTime getValidUntil() {
        return this.validUntil;
    }

    @Override
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public List<KillChainPhase> getKillChainPhases() {
        return this.killChainPhases;
    }

    @Override
    public void setKillChainPhases(List<KillChainPhase> killChainPhases) {
        this.killChainPhases = killChainPhases;
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
        if (!labels.isEmpty()){
            this.labels = labels;
        } else {
            throw new IllegalArgumentException("At least one label is required");
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
