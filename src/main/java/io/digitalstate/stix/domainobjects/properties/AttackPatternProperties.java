package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "targets", "uses",
        "name", "description", "kill_chain_phases"})
public abstract class AttackPatternProperties extends CommonProperties {
    private String name;

    @JsonInclude(NON_EMPTY)
    private String description = null;

    @JsonProperty("kill_chain_phases")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<KillChainPhase> killChainPhases = new LinkedHashSet<>();

    //
    // Relationships
    private LinkedHashSet<Relation<Relationship>> targets = new LinkedHashSet<>();
    private LinkedHashSet<Relation<Relationship>> uses = new LinkedHashSet<>();

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
    public LinkedHashSet<Relation<Relationship>> getTargets() {
        return targets;
    }

    public void setTargets(LinkedHashSet<Relation<Relationship>> targets) {
        RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                targets, Identity.class, Vulnerability.class);

        this.targets = targets;
    }

    @JsonIgnore
    public LinkedHashSet<Relation<Relationship>> getUses() {
        return uses;
    }

    public void setUses(LinkedHashSet<Relation<Relationship>> uses) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                uses, Malware.class, Tool.class);

        this.uses = uses;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        getTargets().forEach(t->{
            if (t.hasObject()){
                bundleObjects.add(t.getObject());
            }
        });

        getUses().forEach(u->{
            if (u.hasObject()){
                bundleObjects.add(u.getObject());
            }
        });

        return bundleObjects;
    }

    @JsonIgnore
    public void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects){

    }

}
