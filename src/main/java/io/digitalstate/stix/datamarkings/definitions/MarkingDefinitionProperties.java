package io.digitalstate.stix.datamarkings.definitions;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.datamarkings.markingtypes.MarkingObjectType;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "external_references", "object_marking_refs", "granular_markings", "definition_type",
        "definition"})
public abstract class MarkingDefinitionProperties {

    @JsonIgnore
    private final String specVersion = StixSpecVersion.SPECVERSION;

    private String type;
    private String id;

    private Relation<Identity> createdByRef = null;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime created = ZonedDateTime.now();

    @JsonProperty("external_references")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<ExternalReference> externalReferences = new LinkedHashSet<>();

    private LinkedHashSet<Relation<MarkingDefinition>> objectMarkingRefs = new LinkedHashSet<>();

    @JsonProperty("granular_markings")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<GranularMarking> granularMarkings = new LinkedHashSet<>();

    @JsonProperty("definition_type")
    private String definitionType;

    private MarkingObjectType definition;


    //
    // Getters and Setters
    //

    public String getSpecVersion() {
        return specVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Objects.requireNonNull(id, "Id cannot be null");

        if (StringUtils.isBlank(getType())) {
            throw new IllegalArgumentException("Cannot set id without Type property being defined");

        } else if (StringUtils.isNotBlank(id)) {
            this.id = id;

        } else {
            throw new IllegalArgumentException("Id can't be null or blank");
        }
    }

    public void setId(String prefix, String uuid) {
        Objects.requireNonNull(prefix, "prefix cannot be null");
        Objects.requireNonNull(uuid, "Id cannot be null");

        this.setId(String.join("--", prefix, uuid));
    }

    @JsonIgnore
    public Relation<Identity> getCreatedByRef() {
        return createdByRef;
    }

    public void setCreatedByRef(Relation<Identity> createdByRef) {
        this.createdByRef = createdByRef;
    }

    /**
     * Used by JSON serlizer to return proper spec value for created_by_ref property
     *
     * @return
     */
    @JsonProperty("created_by_ref")
    @JsonInclude(NON_NULL)
    public String getCreatedByRefId() {
        Relation<Identity> identity = this.getCreatedByRef();
        if (identity != null) {
            if (identity.hasObject()) {
                return identity.getObject().getId();
            } else {
                return identity.getId();
            }
        } else {
            return null;
        }
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public LinkedHashSet<ExternalReference> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(LinkedHashSet<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    @JsonIgnore
    public LinkedHashSet<Relation<MarkingDefinition>> getObjectMarkingRefs() {
        return objectMarkingRefs;
    }

    public void setObjectMarkingRefs(LinkedHashSet<Relation<MarkingDefinition>> objectMarkingRefs) {
        this.objectMarkingRefs = objectMarkingRefs;
    }

    @JsonProperty("object_marking_refs")
    @JsonInclude(NON_EMPTY)
    public LinkedHashSet<String> getObjectMarkingRefsIds() {
        LinkedHashSet<Relation<MarkingDefinition>> objectRefs = this.getObjectMarkingRefs();
        LinkedHashSet<String> ids = new LinkedHashSet<>();
        if (objectRefs != null) {

            objectRefs.forEach(ref -> {
                if (ref.hasObject()) {
                    ids.add(ref.getObject().getId());
                } else {
                    ids.add(ref.getId());
                }
            });
            return ids;

        } else {
            return null;
        }
    }

    public LinkedHashSet<GranularMarking> getGranularMarkings() {
        return granularMarkings;
    }

    public void setGranularMarkings(LinkedHashSet<GranularMarking> granularMarkings) {
        // Check for Circular References: Where a granul
        granularMarkings.forEach(gm -> {
            // @TODO rebuild new methods for .equals() to have a special equals method that compares the combined ID and Modified fields (as per STIX spec)
            if (gm.getMarkingRef().equals(this)) {
                throw new IllegalArgumentException("Circular Reference detected in Granular Marking: " + this.toString());
            }
        });
        this.granularMarkings = granularMarkings;
    }

    public String getDefinitionType() {
        return definitionType;
    }

    public void setDefinitionType(String definitionType) {
        this.definitionType = definitionType;
    }

    public MarkingObjectType getDefinition() {
        return definition;
    }

    public void setDefinition(MarkingObjectType definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllCommonPropertiesBundleObjects() {
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        if (getCreatedByRef() != null) {
            if (getCreatedByRef().hasObject()) {
                bundleObjects.add(getCreatedByRef().getObject());
            }
        }

        if (getObjectMarkingRefs() != null) {
            getObjectMarkingRefs().forEach(om -> {
                if (om.hasObject()) {
                    bundleObjects.add(om.getObject());

                    // getCreatedByRef
                    if (om.getObject().getCreatedByRef() != null) {
                        if (om.getObject().getCreatedByRef().hasObject()) {
                            bundleObjects.add(om.getObject().getCreatedByRef().getObject());
                        }
                    }

                    // getObjectMarkingRefs
                    if (om.getObject().getObjectMarkingRefs() != null) {
                        om.getObject().getObjectMarkingRefs().forEach(omr -> {
                            if (omr.hasObject()) {
                                bundleObjects.add(omr.getObject());
                            }
                        });
                    }

                    // getGranularMarkings and get the getMarkingRef for each (if any)
                    if (om.getObject().getGranularMarkings() != null) {
                        om.getObject().getGranularMarkings().forEach(gm -> {
                            if (gm.getMarkingRef().hasObject()) {
                                bundleObjects.add(gm.getMarkingRef().getObject());
                            }
                        });
                    }
                }

            });
        }

        if (getGranularMarkings() != null) {
            getGranularMarkings().forEach(gm -> {
                if (gm.getMarkingRef().hasObject()) {
                    bundleObjects.add(gm.getMarkingRef().getObject());
                }
            });
        }

        return bundleObjects;
    }

    @JsonIgnore
    public void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects){

    }



        //
    // Overrides for hashcode and equals to support comparison of objects as per the STIX Spec
    //

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreated());
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
                object.getModified().equals(this.getCreated().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE)));
    }

    public String toJsonString() throws JsonProcessingException {
        return StixDataFormats.getJsonMapper().writeValueAsString(this);
    }

}
