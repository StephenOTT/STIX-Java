package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.relationshipobjects.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "targets", "uses",
        "name", "description", "kill_chain_phases"})
public abstract class AttackPatternProperties extends CommonProperties {
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonProperty("kill_chain_phases")
    @JsonInclude(NON_NULL)
    private LinkedHashSet<KillChainPhase> killChainPhases = null;

    //
    // Relationships
    private LinkedHashSet<Relationship> targets = new LinkedHashSet<>();
    private LinkedHashSet<Relationship> uses = new LinkedHashSet<>();

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

    public LinkedHashSet<KillChainPhase> getKillChainPhases() {
        return killChainPhases;
    }

    public void setKillChainPhases(LinkedHashSet<KillChainPhase> killChainPhases) {
        this.killChainPhases = killChainPhases;
    }

    @JsonIgnore
    public void setKillChainPhases(KillChainPhase... killChainPhases) {
        setKillChainPhases(new LinkedHashSet<>(Arrays.asList(killChainPhases)));
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

    @JsonIgnore
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

    @JsonIgnore
    public void addTarget(StixDomainObject target, String description){
        Objects.requireNonNull(target, "target cannot be null");
        Relationship relationship = new Relationship(
                "targets",
                (StixDomainObject)this,
                target);
        addTargets(relationship);
    }

    @JsonIgnore
    public void addTarget(StixDomainObject target){
        addTarget(target, null);
    }

    @JsonIgnore
    public LinkedHashSet<Relationship> getUses() {
        return uses;
    }

    public void setUses(LinkedHashSet<Relationship> uses) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                uses, Malware.class, Tool.class);

        this.uses = uses;
    }

    @JsonIgnore
    public void addUses(Relationship... relationships){
        if (this.getUses() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, Malware.class, Tool.class);

            this.setUses(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, Malware.class, Tool.class);

            this.getUses().addAll(Arrays.asList(relationships));
        }
    }

    @JsonIgnore
    public void addUse(StixDomainObject use, String description){
        Objects.requireNonNull(use, "use cannot be null");
        Relationship relationship = new Relationship(
                "uses",
                (StixDomainObject)this,
                use);
        addUses(relationship);
    }

    @JsonIgnore
    public void addUse(StixDomainObject use){
        addUse(use, null);
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        bundleObjects.addAll(getTargets());
        bundleObjects.addAll(getUses());

        return bundleObjects;
    }

}
