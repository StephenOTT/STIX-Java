package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "aliases", "roles", "goals", "sophistication",
        "resource_level", "primary_motivation", "secondary_motivation", "personal_motivations"})
public abstract class ThreatActorProperties extends CommonProperties{
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> aliases = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> roles = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> goals = null;

    @JsonInclude(NON_NULL)
    protected String sophistication = null;

    @JsonProperty("resource_level")
    @JsonInclude(NON_NULL)
    protected String resourceLevel = null;

    @JsonProperty("primary_motivation")
    @JsonInclude(NON_NULL)
    protected String primaryMotivation = null;

    @JsonProperty("secondary_motivation")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> secondaryMotivations = null;

    @JsonProperty("personal_motivations")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> personalMotivations = null;

    //
    // Relationships
    //

    private LinkedHashSet<StixRelationshipObject> attributedTo = new LinkedHashSet<>();
    private LinkedHashSet<StixRelationshipObject> impersonates = new LinkedHashSet<>();
    private LinkedHashSet<StixRelationshipObject> targets = new LinkedHashSet<>();
    private LinkedHashSet<StixRelationshipObject> uses = new LinkedHashSet<>();

    //
    // Getters and Setters
    //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.roles = roles;
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
        this.sophistication = sophistication;
    }

    public String getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(String resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public String getPrimaryMotivation() {
        return primaryMotivation;
    }

    public void setPrimaryMotivation(String primaryMotivation) {
        this.primaryMotivation = primaryMotivation;
    }

    public LinkedHashSet<String> getSecondaryMotivations() {
        return secondaryMotivations;
    }

    public void setSecondaryMotivations(LinkedHashSet<String> secondaryMotivations) {
        this.secondaryMotivations = secondaryMotivations;
    }

    public LinkedHashSet<String> getPersonalMotivations() {
        return personalMotivations;
    }

    public void setPersonalMotivations(LinkedHashSet<String> personalMotivations) {
        this.personalMotivations = personalMotivations;
    }

    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<StixRelationshipObject> getAttributedTo() {
        return attributedTo;
    }

    public void setAttributedTo(LinkedHashSet<StixRelationshipObject> attributedTo) {
        RelationshipValidators.validateRelationshipAcceptableClasses("attributed-to",
                attributedTo, Identity.class);

        this.attributedTo = attributedTo;
    }

    public void addAttributedTo(StixRelationshipObject... relationships){
        if (this.getAttributedTo() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("attributed-to",
                    relationshipObjects, Identity.class);

            this.setAttributedTo(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
    public LinkedHashSet<StixRelationshipObject> getImpersonates() {
        return impersonates;
    }

    public void setImpersonates(LinkedHashSet<StixRelationshipObject> impersonates) {
        RelationshipValidators.validateRelationshipAcceptableClasses("impersonates",
                impersonates, Identity.class);

        this.impersonates = impersonates;
    }

    public void addImpersonates(StixRelationshipObject... relationships){
        if (this.getImpersonates() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("impersonates",
                    relationshipObjects, Identity.class);

            this.setImpersonates(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
    public LinkedHashSet<StixRelationshipObject> getTargets() {
        return targets;
    }

    public void setTargets(LinkedHashSet<StixRelationshipObject> targets) {
        RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                targets, Identity.class, Vulnerability.class);

        this.targets = targets;
    }

    public void addTargets(StixRelationshipObject... relationships){
        if (this.getTargets() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                    relationshipObjects, Identity.class, Vulnerability.class);

            this.setTargets(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
    public LinkedHashSet<StixRelationshipObject> getUses() {
        return uses;
    }

    public void setUses(LinkedHashSet<StixRelationshipObject> uses) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                uses, AttackPattern.class, Malware.class, Tool.class);

        this.uses = uses;
    }

    public void addUses(StixRelationshipObject... relationships){
        if (this.getUses() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class);

            this.setUses(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
