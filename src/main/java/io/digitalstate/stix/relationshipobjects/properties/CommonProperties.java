package io.digitalstate.stix.relationshipobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.relationshipobjects.Relation;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * RelationshipObjects Common Properties
 */
public abstract class CommonProperties {

    @JsonIgnore
    private final String specVersion = StixSpecVersion.SPECVERSION;

    private String type;
    private String id;

    private Relation<Identity> createdByRef = null;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime created = ZonedDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime modified = this.created;

    private boolean revoked = false;

    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<String> labels = new LinkedHashSet<>();

    @JsonProperty("external_references")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<ExternalReference> externalReferences = new LinkedHashSet<>();

    private LinkedHashSet<Relation<MarkingDefinition>> objectMarkingRefs = new LinkedHashSet<>();

    @JsonProperty("granular_markings")
    @JsonInclude(NON_EMPTY)
    private LinkedHashSet<GranularMarking> granularMarkings = new LinkedHashSet<>();

    private HashMap<String, Object> customProperties = new HashMap<>();


    //
    // Getters and Setters
    //

    public String getType() {
        return type;
    }

    public void setType(String type) {
        Objects.requireNonNull(type, "Type cannot be null");
        if (StringUtils.isNotBlank(type)){
            this.type = type;
        } else {
            throw new IllegalArgumentException("type cannot be null or blank");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Objects.requireNonNull(id, "Id cannot be null");

        if (StringUtils.isBlank(getType())){
            throw new IllegalArgumentException("Cannot set id without Type property being defined");

        }else if (StringUtils.isNotBlank(id)){
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
     * @return
     */
    @JsonProperty("created_by_ref")
    @JsonInclude(NON_NULL)
    public String getCreatedByRefId() {
        Relation<Identity> identity = this.getCreatedByRef();
        if (identity != null){
            if (identity.hasObject()){
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
        Objects.requireNonNull(type, "created date cannot be null");
        this.created = created;
    }

    public ZonedDateTime getModified() {
        return modified;
    }

    public void setModified(ZonedDateTime modified) {
        Objects.requireNonNull(type, "modified date cannot be null");
        this.modified = modified;
    }

    public boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public LinkedHashSet<String> getLabels() {
        return labels;
    }

    public void setLabels(LinkedHashSet<String> labels) {
        this.labels = labels;
    }

    public LinkedHashSet<ExternalReference> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(LinkedHashSet<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    @JsonIgnore
    public LinkedHashSet<Relation<MarkingDefinition>> getObjectMarkingRefs() {
        return this.objectMarkingRefs;
    }

    @JsonProperty("object_marking_refs")
    @JsonInclude(NON_EMPTY)
    public LinkedHashSet<String> getObjectMarkingRefsIds() {
        LinkedHashSet<Relation<MarkingDefinition>> objectRefs = this.getObjectMarkingRefs();
        LinkedHashSet<String> ids = new LinkedHashSet<>();
        if (objectRefs != null) {

            objectRefs.forEach(ref->{
                if (ref.hasObject()){
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


    public void setObjectMarkingRefs(LinkedHashSet<Relation<MarkingDefinition>> markingDefinitions) {
        this.objectMarkingRefs = markingDefinitions;
    }

    public LinkedHashSet<GranularMarking> getGranularMarkings() {
        return granularMarkings;
    }

    public void setGranularMarkings(LinkedHashSet<GranularMarking> granularMarkings) {
        // Check for Circular References: Where a granul
        granularMarkings.forEach(gm ->{
            // @TODO rebuild new methods for .equals() to have a special equals method that compares the combined ID and Modified fields (as per STIX spec)
            if (gm.getMarkingRef().equals(this)){
                throw new IllegalArgumentException("Circular Reference detected in Granular Marking: " + this.toString());
            }});
        this.granularMarkings = granularMarkings;
    }

    @JsonIgnore
    public HashMap<String, Object> getCustomProperties() {
        return this.customProperties;
    }

    public void setCustomProperties(HashMap<String, Object> customProperties) {
        this.customProperties = customProperties;
    }

    /**
     * Returns a Map with keys that start with "x_".
     * @return
     */
    @JsonAnyGetter
    @JsonInclude(NON_NULL)
    public HashMap<String, Object> getCustomPropertiesForJson() {
        String prefix = "x_";

        HashMap<String, Object> customProperties = this.getCustomProperties();

        // @TODO Add support for proper casing of Keys

        if (customProperties == null){
            return null;
        } else {
            HashMap<String, Object> customPropertiesWithPrefixUpdate = new HashMap<>();
            this.getCustomProperties().forEach((k,v)->{
                if (!k.startsWith(prefix)){
                    customPropertiesWithPrefixUpdate.put(String.join("", prefix, k), v);
                } else {
                    customPropertiesWithPrefixUpdate.put(k, v);
                }
            });
            return customPropertiesWithPrefixUpdate;
        }
    }


    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllCommonPropertiesBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        if (getCreatedByRef() != null){
            if (getCreatedByRef().hasObject()){
                bundleObjects.add(getCreatedByRef().getObject());
            }
        }

        if (getObjectMarkingRefs() != null) {
            getObjectMarkingRefs().forEach(om -> {
                if (om.hasObject()){
                    bundleObjects.add(om.getObject());

                    // getCreatedByRef
                    if (om.getObject().getCreatedByRef() != null){
                        if (om.getObject().getCreatedByRef().hasObject()){
                            bundleObjects.add(om.getObject().getCreatedByRef().getObject());
                        }
                    }

                    // getObjectMarkingRefs
                    if (om.getObject().getObjectMarkingRefs() != null) {
                        om.getObject().getObjectMarkingRefs().forEach(omr->{
                            if (omr.hasObject()){
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
                if (gm.getMarkingRef().hasObject()){
                    bundleObjects.add(gm.getMarkingRef().getObject());
                }
            });
        }

        return bundleObjects;
    }

    public String toJsonString() throws JsonProcessingException {
        return StixDataFormats.getJsonMapper().writeValueAsString(this);
    }

}