package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
        "object_marking_refs", "granular_markings","name", "description",
        "action"})
public abstract class CourseOfActionProperties extends CommonProperties{
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> action = null;

    //
    // Relationships
    //

    private LinkedHashSet<StixRelationshipObject> mitigates = new LinkedHashSet<>();


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

    public LinkedHashSet<String> getAction() {
        return action;
    }
    public void setAction(LinkedHashSet<String> action) {
        this.action = action;
    }

    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<StixRelationshipObject> getMitigates() {
        return mitigates;
    }

    public void setMitigates(LinkedHashSet<StixRelationshipObject> mitigates) {
        RelationshipValidators.validateRelationshipAcceptableClasses("mitigates",
                mitigates, AttackPattern.class, Malware.class, Tool.class, Vulnerability.class);

        this.mitigates = mitigates;
    }

    public void addMitigates(StixRelationshipObject... relationships){
        if (this.getMitigates() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("mitigates",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class, Vulnerability.class);

            this.setMitigates(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class, Vulnerability.class);

            this.getMitigates().addAll(Arrays.asList(relationships));
        }
    }

    public void addMitigates(StixDomainObject mitigates, String description){
        Objects.requireNonNull(mitigates, "mitigates cannot be null");

        Relationship relationship = new Relationship(
                "mitigates",
                (StixDomainObject)this,
                mitigates);
        addMitigates(relationship);
    }

    public void addMitigates(StixDomainObject mitigates){
        addMitigates(mitigates, null);
    }


    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

//        bundleObjects.addAll(getTargets());
//        bundleObjects.addAll(getUses());

        return bundleObjects;
    }
}
