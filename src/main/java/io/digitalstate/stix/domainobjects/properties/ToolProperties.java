package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.domainobjects.Vulnerability;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.vocabularies.StixVocabulary;
import io.digitalstate.stix.vocabularies.ToolLabels;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "kill_chain_phases", "tool_version"})
public abstract class ToolProperties extends CommonProperties{
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonProperty("kill_chain_phases")
    @JsonInclude(NON_NULL)
    private LinkedHashSet<KillChainPhase> killChainPhases = null;

    @JsonProperty("tool_version")
    @JsonInclude(NON_NULL)
    private String toolVersion = null;

    // Vocabulary Instances
    private StixVocabulary toolLabelsVocab = new ToolLabels();

    //
    // Relationships
    //
    private LinkedHashSet<Relationship> targets = new LinkedHashSet<>();

    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedHashSet<KillChainPhase> getKillChainPhases() {
        return killChainPhases;
    }

    public void setKillChainPhases(LinkedHashSet<KillChainPhase> killChainPhases) {
        this.killChainPhases = killChainPhases;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    @JsonIgnore
    public StixVocabulary getToolLabelsVocab() {
        return toolLabelsVocab;
    }

    @JsonIgnore
    public void setToolLabelsVocab(StixVocabulary toolLabelsVocab) {
        Objects.requireNonNull(toolLabelsVocab, "toolLabelsVocab cannot be null");
        this.toolLabelsVocab = toolLabelsVocab;
    }

    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Objects.requireNonNull(labels, "labels cannot be null");

        if (!labels.isEmpty() && getToolLabelsVocab().vocabularyContains(labels)){
            super.setLabels(labels);
        } else {
            throw new IllegalArgumentException("One or more labels are not valid Tool labels");
        }
    }

    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<Relationship> getTargets() {
        return targets;
    }

    public void setTargets(LinkedHashSet<Relationship> targets) {
        RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                targets, Identity.class, Vulnerability.class);

        this.targets = targets;
    }

    public void addTargets(Relationship... relationships){
        if (this.getTargets() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                    relationshipObjects, Identity.class, Vulnerability.class);

            this.setTargets(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                    relationshipObjects, Identity.class, Vulnerability.class);

            this.getTargets().addAll(Arrays.asList(relationships));
        }
    }

    public void addTarget(StixDomainObject target, String description){
        Objects.requireNonNull(target, "target cannot be null");
        Relationship relationship = new Relationship(
                "targets",
                (StixDomainObject)this,
                target);
        addTargets(relationship);
    }

    public void addTarget(StixDomainObject target){
        addTarget(target, null);
    }


    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        bundleObjects.addAll(getTargets());

        return bundleObjects;
    }
}
