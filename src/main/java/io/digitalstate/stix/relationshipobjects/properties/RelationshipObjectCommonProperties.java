package io.digitalstate.stix.relationshipobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;
import org.apache.commons.lang3.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Defines the common properties for a Stix Relationship Object (SRO)
 */
public abstract class RelationshipObjectCommonProperties {

    @JsonIgnore
    private final String specVersion = StixSpecVersion.SPECVERSION;

    protected String type;
    protected String id;

    protected Identity createdByRef = null;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime created = ZonedDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime modified = this.created;

    protected boolean revoked = false;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> labels = null;

    @JsonProperty("external_references")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<ExternalReference> externalReferences = null;

    protected LinkedHashSet<MarkingDefinition> objectMarkingRefs = null;

    @JsonProperty("granular_markings")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<GranularMarking> granularMarkings = null;

    protected HashMap<String, Object> customProperties = null;

    @JsonProperty("relationship_type")
    private String relationshipType;

    @JsonInclude(NON_NULL)
    private String description = null;

    private StixDomainObject source;

    private StixDomainObject target;

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
            this.id = String.join("--", getType(), id);

        } else {
            throw new IllegalArgumentException("Id can't be null or blank");
        }
    }

    @JsonIgnore
    public Identity getCreatedByRef() {
        return createdByRef;
    }

    public void setCreatedByRef(Identity createdByRef) {
        this.createdByRef = createdByRef;
    }

    /**
     * Used by JSON serlizer to return proper spec value for created_by_ref property
     * @return
     */
    @JsonProperty("created_by_ref")
    @JsonInclude(NON_NULL)
    public String getCreatedByRefId() {
        Identity identity = this.getCreatedByRef();
        if (identity != null){
            return this.getCreatedByRef().getId();
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
    public LinkedHashSet<MarkingDefinition> getObjectMarkingRefs() {
        return this.objectMarkingRefs;
    }

    @JsonProperty("object_marking_refs")
    @JsonInclude(NON_NULL)
    public LinkedHashSet<String> getObjectMarkingRefsAsStrings() {
        if (getObjectMarkingRefs() != null) {
            return this.getObjectMarkingRefs().stream()
                    .map(MarkingDefinition::getId)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        } else {
            return null;
        }
    }

    public void setObjectMarkingRefs(LinkedHashSet<MarkingDefinition> markingDefinitions) {
        this.objectMarkingRefs = markingDefinitions;
    }

    public void setObjectMarkingRefs(MarkingDefinition... objectMarkingRefs) {
        this.setObjectMarkingRefs(new LinkedHashSet<>(Arrays.asList(objectMarkingRefs)));
    }

    public void addObjectMarkingRefs(MarkingDefinition... objectMarkingRefs) {
        if (this.getObjectMarkingRefs() == null){
            this.setObjectMarkingRefs(new LinkedHashSet<>(Arrays.asList(objectMarkingRefs)));
        } else {
            this.getObjectMarkingRefs().addAll(Arrays.asList(objectMarkingRefs));
        }
    }

    public LinkedHashSet<GranularMarking> getGranularMarkings() {
        return this.granularMarkings;
    }

    public void setGranularMarkings(LinkedHashSet<GranularMarking> granularMarkings) {
        this.granularMarkings = granularMarkings;
    }

    public void setGranularMarkings(GranularMarking... objectMarkingRefs) {
        this.setGranularMarkings(new LinkedHashSet<>(Arrays.asList(objectMarkingRefs)));
    }

    public void addGranularMarkings(GranularMarking... granularMarkings) {
        if (this.getGranularMarkings() == null){
            this.setGranularMarkings(new LinkedHashSet<>(Arrays.asList(granularMarkings)));
        } else {
            this.getGranularMarkings().addAll(Arrays.asList(granularMarkings));
        }
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
