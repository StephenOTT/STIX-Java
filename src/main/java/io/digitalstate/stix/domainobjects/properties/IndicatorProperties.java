package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.vocabularies.IndicatorLabels;
import io.digitalstate.stix.vocabularies.StixVocabulary;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name",
        "description", "pattern", "valid_from", "valid_until",
        "kill_chain_phases"})
public abstract class IndicatorProperties extends CommonProperties{

    @JsonInclude(NON_NULL)
    private String name = null;

    @JsonInclude(NON_NULL)
    private String description = null;

    private String pattern;

    @JsonProperty("valid_from")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime validFrom;

    @JsonProperty("valid_until")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime validUntil;

    @JsonProperty("kill_chain_phases")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<KillChainPhase> killChainPhases = new LinkedHashSet<>();

    @JsonIgnore
    private StixVocabulary indicatorLabelsVocab = new IndicatorLabels();

    //
    // Relationships
    //
    private LinkedHashSet<Relation<Relationship>> indicates = new LinkedHashSet<>();

    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotEmpty(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be blank (but it can be null)");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        if (StringUtils.isNotBlank(pattern)){
            this.pattern = pattern;
        } else {
            throw new IllegalArgumentException("Pattern cannot be null or blank");
        }
    }

    public ZonedDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(ZonedDateTime validFrom) {
        if (validFrom != null){
            this.validFrom = validFrom;
        } else {
            throw new IllegalArgumentException("ValidFrom cannot be null");
        }
    }

    public ZonedDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(ZonedDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public LinkedHashSet<KillChainPhase> getKillChainPhases() {
        return killChainPhases;
    }

    public void setKillChainPhases(LinkedHashSet<KillChainPhase> killChainPhases) {
        this.killChainPhases = killChainPhases;
    }


    @JsonIgnore
    public StixVocabulary getIndicatorLabelsVocab() {
        return indicatorLabelsVocab;
    }

    @JsonIgnore
    public void setIndicatorLabelsVocab(StixVocabulary indicatorLabelsVocab) {
        Objects.requireNonNull(indicatorLabelsVocab, "indicatorLabelsVocab cannot be null");
        this.indicatorLabelsVocab = indicatorLabelsVocab;
    }

    /**
     * Labels for Indicator are specific to Indicator Labels (indicator-label-ov)
     * It specifies the Type of Indicator.
     * @param labels
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Objects.requireNonNull(labels, "labels cannot be null");

        if (!labels.isEmpty() && getIndicatorLabelsVocab().vocabularyContains(labels)){
            super.setLabels(labels);
        } else {
            throw new IllegalArgumentException("One or more labels are not valid Indicator labels");
        }
    }

    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<Relation<Relationship>> getIndicates() {
        return indicates;
    }

    public void setIndicates(LinkedHashSet<Relation<Relationship>> indicates) {
        RelationshipValidators.validateRelationshipAcceptableClasses("indicates",
                indicates, AttackPattern.class, Campaign.class, IntrusionSet.class,
                Malware.class, ThreatActor.class, Tool.class);

        this.indicates = indicates;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        getIndicates().forEach(relation->{
            if (relation.hasObject()){
                bundleObjects.add(relation.getObject());
            }
        });

        return bundleObjects;
    }

    @JsonIgnore
    public void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects){

    }
}
