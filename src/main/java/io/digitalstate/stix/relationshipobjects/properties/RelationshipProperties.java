package io.digitalstate.stix.relationshipobjects.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.time.ZoneId;
import java.util.LinkedHashSet;
import java.util.Objects;

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

    private StixDomainObject source;

    private StixDomainObject target;

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
    public StixDomainObject getSource() {
        return source;
    }

    public void setSource(StixDomainObject source) {
        this.source = source;
    }

    @JsonProperty("source")
    @JsonInclude(NON_NULL)
    public String getSourceRefsAsString() {
        if (getSource() != null){
            return getSource().getId();
        } else {
            return null;
        }
    }

    @JsonIgnore
    public StixDomainObject getTarget() {
        return target;
    }

    public void setTarget(StixDomainObject target) {
        this.target = target;
    }

    @JsonProperty("target")
    @JsonInclude(NON_NULL)
    public String getTargetRefsAsString() {
        if (getTarget() != null){
            return getTarget().getId();
        } else {
            return null;
        }
    }

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        if (getSource() != null) {
            bundleObjects.add(getSource());
        }

        if (getTarget() != null) {
            bundleObjects.add(getTarget());
        }

        return bundleObjects;
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
