package io.digitalstate.stix.relationshipobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "relationship_type", "description",
        "source", "target"})
public abstract class RelationshipProperties extends CommonProperties {

    @JsonProperty("relationship_type")
    private String relationshipType;

    @JsonInclude(NON_NULL)
    private String description = null;

    private Relation<StixDomainObject> source;

    private Relation<StixDomainObject> target;

    //
    // Getters and Setters
    //

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Relation<StixDomainObject> getSource() {
        return this.source;
    }

    public void setSource(Relation<StixDomainObject> source) {
        Objects.requireNonNull(source, "source cannot be null");
        this.source = source;
    }

    public void setSource(StixDomainObject source) {
        Objects.requireNonNull(source, "source cannot be null");
        this.source = new Relation<>(source);
    }

    @JsonProperty("source")
    @JsonInclude(NON_NULL)
    public String getSourceID() {
        if (getSource().hasObject()){
            return getSource().getObject().getId();
        } else {
            return getSource().getId();
        }
    }

    @JsonIgnore
    public Relation<StixDomainObject> getTarget() {
        return this.target;
    }

    public void setTarget(Relation<StixDomainObject> target) {
        Objects.requireNonNull(target, "target cannot be null");
        this.target = target;
    }

    public void setTarget(StixDomainObject target) {
        Objects.requireNonNull(target, "target cannot be null");
        this.target = new Relation<>(target);
    }

    @JsonProperty("target")
    @JsonInclude(NON_NULL)
    public String getTargetID() {
        if (getTarget().hasObject()){
            return getTarget().getObject().getId();
        } else {
            return getTarget().getId();
        }
    }

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        if (getSource() != null && getSource().hasObject()) {
            bundleObjects.add(getSource().getObject());
        }

        if (getTarget() != null && getTarget().hasObject()) {
            bundleObjects.add(getTarget().getObject());
        }

        return bundleObjects;
    }


    @JsonIgnore
    public void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects){

        // Hydrate Source
        String sourceId = getSource().getId();
        Optional<BundleObject> source = bundleObjects.stream()
                .filter(o-> o instanceof StixRelationshipObject)
                .filter(o-> ((StixRelationshipObject) o).getId().equals(sourceId))
                .findAny();

        source.ifPresent(o->{
            Relation<StixDomainObject> hydratedRelation = new Relation<StixDomainObject>((StixDomainObject)o);
            setSource(hydratedRelation);
        });

        // Hydrate Target
        String targetId = getSource().getId();
        Optional<BundleObject> target = bundleObjects.stream()
                .filter(o-> o instanceof StixRelationshipObject)
                .filter(o-> ((StixRelationshipObject) o).getId().equals(targetId))
                .findAny();

        target.ifPresent(o->{
            Relation<StixDomainObject> hydratedRelation = new Relation<StixDomainObject>((StixDomainObject)o);
            setTarget(hydratedRelation);
        });
    }


    //
    // Overrides for hashcode and equals to support comparison of objects as per the STIX Spec
    //

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModified());
    }

    @Override
    public boolean equals(Object obj) {
        // Ensure the obj is a instance of StixRelationshipObject
        // We can assume this because anything using CommonProperties should be implementing
        // StixRelationshipObject (or the CustomStixRelationshipObject) interface.
        if (!(obj instanceof StixRelationshipObject)) {
            return false;
        }

        StixRelationshipObject object = (StixRelationshipObject) obj;
        // Compare the ID and Modified Date fields to be equal.
        // Modified Date field is converted into UTC time for brevity
        return object.getId().equals(this.getId()) &&
                object.getModified().equals(this.getModified().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE)));
    }
}
