package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.Relationship;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name",
        "description", "action"})
public abstract class CourseOfActionProperties extends CommonProperties{
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<String> action = new LinkedHashSet<>();

    //
    // Relationships
    //

    private LinkedHashSet<Relation<Relationship>> mitigates = new LinkedHashSet<>();


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
            throw new IllegalArgumentException("Name can't be null or blank");
        }
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
    public LinkedHashSet<Relation<Relationship>> getMitigates() {
        return mitigates;
    }

    public void setMitigates(LinkedHashSet<Relation<Relationship>> mitigates) {
        RelationshipValidators.validateRelationshipAcceptableClasses("mitigates",
                mitigates, AttackPattern.class, Malware.class, Tool.class, Vulnerability.class);

        this.mitigates = mitigates;
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        getMitigates().forEach(relation->{
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
