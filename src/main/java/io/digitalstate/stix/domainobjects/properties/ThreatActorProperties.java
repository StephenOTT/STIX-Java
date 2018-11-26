package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.vocabularies.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "attributed_to", "impersonates",
        "targets", "uses", "name", "description",
        "aliases", "roles", "goals", "sophistication",
        "resource_level", "primary_motivation", "secondary_motivation", "personal_motivations"})
public abstract class ThreatActorProperties extends CommonProperties{
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> aliases = null;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> roles = null;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> goals = null;

    @JsonInclude(NON_NULL)
    private String sophistication = null;

    @JsonProperty("resource_level")
    @JsonInclude(NON_NULL)
    private String resourceLevel = null;

    @JsonProperty("primary_motivation")
    @JsonInclude(NON_NULL)
    private String primaryMotivation = null;

    @JsonProperty("secondary_motivation")
    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> secondaryMotivations = null;

    @JsonProperty("personal_motivations")
    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> personalMotivations = null;

    // Vocabulary Instances
    private StixVocabulary threatActorLabelsVocab = new ThreatActorLabels();
    private StixVocabulary threatActorRolesVocab = new ThreatActorRoles();
    private StixVocabulary threatActorSophisticationVocab = new ThreatActorSophistication();
    private StixVocabulary attackResourceLevelsVocab = new AttackResourceLevels();
    private StixVocabulary attackMotivationVocab = new AttackMotivations();

    //
    // Relationships
    //
    private LinkedHashSet<Relationship> attributedTo = new LinkedHashSet<>();
    private LinkedHashSet<Relationship> impersonates = new LinkedHashSet<>();
    private LinkedHashSet<Relationship> targets = new LinkedHashSet<>();
    private LinkedHashSet<Relationship> uses = new LinkedHashSet<>();

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

    public LinkedHashSet<String> getAliases() {
        return aliases;
    }

    public void setAliases(LinkedHashSet<String> aliases) {
        this.aliases = aliases;
    }

    public LinkedHashSet<String> getRoles() {
        return roles;
    }

    public void setRoles(LinkedHashSet<String> roles) {
        if (getThreatActorRolesVocab().vocabularyContains(roles)){
            this.roles = roles;

        } else {
            throw new IllegalArgumentException("One or more roles are not valid Threat Actor Roles");
        }
    }

    public LinkedHashSet<String> getGoals() {
        return goals;
    }

    public void setGoals(LinkedHashSet<String> goals) {
        this.goals = goals;
    }

    public String getSophistication() {
        return sophistication;
    }

    public void setSophistication(String sophistication) {
        if (!sophistication.isEmpty() && getThreatActorSophisticationVocab().vocabularyContains(sophistication)){
            this.sophistication = sophistication;
        } else {
            throw new IllegalArgumentException("sophistication is not a valid Threat Actor Sophistication");
        }
    }

    public String getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(String resourceLevel) {
        if (!resourceLevel.isEmpty() && getAttackResourceLevelsVocab().vocabularyContains(resourceLevel)){
            this.resourceLevel = resourceLevel;
        } else {
            throw new IllegalArgumentException("resourceLevel is not a valid Attack Resource Level");
        }
    }

    public String getPrimaryMotivation() {
        return primaryMotivation;
    }

    public void setPrimaryMotivation(String primaryMotivation) {
        if (!primaryMotivation.isEmpty() && getAttackMotivationVocab().vocabularyContains(primaryMotivation)){
            this.primaryMotivation = primaryMotivation;
        } else {
            throw new IllegalArgumentException("primaryMotivation is not a valid Attack Motivation");
        }
    }

    public LinkedHashSet<String> getSecondaryMotivations() {
        return secondaryMotivations;
    }

    public void setSecondaryMotivations(LinkedHashSet<String> secondaryMotivations) {
        if (!secondaryMotivations.isEmpty() && getAttackMotivationVocab().vocabularyContains(secondaryMotivations)){
            this.secondaryMotivations = secondaryMotivations;
        } else {
            throw new IllegalArgumentException("One or more values of secondaryMotivations is not a valid Attack Motivation");
        }
    }

    public LinkedHashSet<String> getPersonalMotivations() {
        return personalMotivations;
    }

    public void setPersonalMotivations(LinkedHashSet<String> personalMotivations) {
        if (!personalMotivations.isEmpty() && getAttackMotivationVocab().vocabularyContains(personalMotivations)){
            this.personalMotivations = personalMotivations;
        } else {
            throw new IllegalArgumentException("One or more values of personalMotivations is not a valid Attack Motivation");
        }
    }


    @JsonIgnore
    public StixVocabulary getThreatActorLabelsVocab() {
        return threatActorLabelsVocab;
    }

    @JsonIgnore
    public void setThreatActorLabelsVocab(StixVocabulary threatActorLabelsVocab) {
        Objects.requireNonNull(threatActorLabelsVocab, "threatActorLabelsVocab cannot be null");
        this.threatActorLabelsVocab = threatActorLabelsVocab;
    }

    @JsonIgnore
    public StixVocabulary getThreatActorRolesVocab() {
        return threatActorRolesVocab;
    }

    @JsonIgnore
    public void setThreatActorRolesVocab(StixVocabulary threatActorRolesVocab) {
        Objects.requireNonNull(threatActorRolesVocab, "threatActorRolesVocab cannot be null");
        this.threatActorRolesVocab = threatActorRolesVocab;
    }

    @JsonIgnore
    public StixVocabulary getThreatActorSophisticationVocab() {
        return threatActorSophisticationVocab;
    }

    @JsonIgnore
    public void setThreatActorSophisticationVocab(StixVocabulary threatActorSophisticationVocab) {
        Objects.requireNonNull(threatActorSophisticationVocab, "threatActorSophisticationVocab cannot be null");
        this.threatActorSophisticationVocab = threatActorSophisticationVocab;
    }

    @JsonIgnore
    public StixVocabulary getAttackResourceLevelsVocab() {
        return attackResourceLevelsVocab;
    }

    @JsonIgnore
    public void setAttackResourceLevelsVocab(StixVocabulary attackResourceLevelsVocab) {
        Objects.requireNonNull(attackResourceLevelsVocab, "attackResourceLevelsVocab cannot be null");
        this.attackResourceLevelsVocab = attackResourceLevelsVocab;
    }

    @JsonIgnore
    public StixVocabulary getAttackMotivationVocab() {
        return attackMotivationVocab;
    }

    @JsonIgnore
    public void setAttackMotivationVocab(StixVocabulary attackMotivationVocab) {
        Objects.requireNonNull(attackMotivationVocab, "attackMotivationVocab cannot be null");
        this.attackMotivationVocab = attackMotivationVocab;
    }

    /**
     * Labels for the type of Threat Actor (threat-actor-label-ov).
     * @param labels
     */
    @Override
    public void setLabels(LinkedHashSet<String> labels) {
        Objects.requireNonNull(labels, "labels cannot be null");

        if (!labels.isEmpty()) {
            throw new NullPointerException("At least one label must be provided");

        } else if (getThreatActorLabelsVocab().vocabularyContains(labels)){
            super.setLabels(labels);

        } else {
            throw new IllegalArgumentException("One or more labels are not valid Malware labels");
        }
    }


    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<Relationship> getAttributedTo() {
        return attributedTo;
    }

    public void setAttributedTo(LinkedHashSet<Relationship> attributedTo) {
        RelationshipValidators.validateRelationshipAcceptableClasses("attributed-to",
                attributedTo, Identity.class);

        this.attributedTo = attributedTo;
    }

    public void addAttributedTo(Relationship... relationships){
        if (this.getAttributedTo() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("attributed-to",
                    relationshipObjects, Identity.class);

            this.setAttributedTo(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("attributed-to",
                    relationshipObjects, Identity.class);

            this.getAttributedTo().addAll(Arrays.asList(relationships));
        }
    }

    public void addAttributedTo(StixDomainObject attributedTo, String description){
        Objects.requireNonNull(attributedTo, "attributedTo cannot be null");
        Relationship relationship = new Relationship(
                "attributed-to",
                (StixDomainObject)this,
                attributedTo);
        addAttributedTo(relationship);
    }

    public void addAttributedTo(StixDomainObject attributedTo){
        addAttributedTo(attributedTo, null);
    }

    @JsonIgnore
    public LinkedHashSet<Relationship> getImpersonates() {
        return impersonates;
    }

    public void setImpersonates(LinkedHashSet<Relationship> impersonates) {
        RelationshipValidators.validateRelationshipAcceptableClasses("impersonates",
                impersonates, Identity.class);

        this.impersonates = impersonates;
    }

    public void addImpersonates(Relationship... relationships){
        if (this.getImpersonates() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("impersonates",
                    relationshipObjects, Identity.class);

            this.setImpersonates(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("impersonates",
                    relationshipObjects, Identity.class);

            this.getImpersonates().addAll(Arrays.asList(relationships));
        }
    }

    public void addImpersonates(StixDomainObject impersonates, String description){
        Objects.requireNonNull(impersonates, "impersonates cannot be null");
        Relationship relationship = new Relationship(
                "uses",
                (StixDomainObject)this,
                impersonates);
        addImpersonates(relationship);
    }

    public void addImpersonates(StixDomainObject impersonates){
        addImpersonates(impersonates, null);
    }

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


    @JsonIgnore
    public LinkedHashSet<Relationship> getUses() {
        return uses;
    }

    public void setUses(LinkedHashSet<Relationship> uses) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                uses, AttackPattern.class, Malware.class, Tool.class);

        this.uses = uses;
    }

    public void addUses(Relationship... relationships){
        if (this.getUses() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class);

            this.setUses(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class);

            this.getUses().addAll(Arrays.asList(relationships));
        }
    }

    public void addUse(StixDomainObject use, String description){
        Objects.requireNonNull(use, "use cannot be null");
        Relationship relationship = new Relationship(
                "uses",
                (StixDomainObject)this,
                use);
        addUses(relationship);
    }

    public void addUse(StixDomainObject use){
        addUse(use, null);
    }



    //
    // helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        bundleObjects.addAll(getAttributedTo());
        bundleObjects.addAll(getImpersonates());
        bundleObjects.addAll(getTargets());
        bundleObjects.addAll(getUses());

        return bundleObjects;
    }

}
