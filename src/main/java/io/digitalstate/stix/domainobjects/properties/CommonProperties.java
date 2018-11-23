package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.relationshipobjects.CustomStixRelationshipObject;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import static io.digitalstate.stix.helpers.RelationshipValidators.validateRelationshipClassEquality;
import static io.digitalstate.stix.helpers.RelationshipValidators.validateRelationshipType;
import static io.digitalstate.stix.relationshipobjects.CommonRelationshipTypes.*;

/**
 * Abstract class to define base common properties found in Stix Objects
 */
public abstract class CommonProperties implements DataMarkingsAppliable {

    // Property Order is set at the super level of the individual STIX SDO classes.
    // This was due to JsonPropertyOrder limitation of extended classes

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

    // Common Relationship Properties

    // @TODO add autoAdders for Bundle: Detect relationships and add them inner objects to the Bundle.
    private LinkedHashSet<StixRelationshipObject> duplicateOf = null;
    private LinkedHashSet<StixRelationshipObject> derivedFrom = null;
    private LinkedHashSet<StixRelationshipObject> relatedTo = null;
    private LinkedHashSet<CustomStixRelationshipObject> customRelationships = null;

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


    @JsonIgnore
    public LinkedHashSet<StixRelationshipObject> getDuplicateOf() {
        return duplicateOf;
    }

    public void setDuplicateOf(LinkedHashSet<StixRelationshipObject> duplicateOf) {
        validateRelationshipClassEquality(
                DUPLICATE_OF.toString(), duplicateOf);

        this.duplicateOf = duplicateOf;
    }

    public void addDuplicateOf(StixRelationshipObject... relationships){
        if (this.getDuplicateOf() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipClassEquality(DUPLICATE_OF.toString(), relationshipObjects);

            this.setDuplicateOf(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipClassEquality(DUPLICATE_OF.toString(), relationshipObjects);

            this.getDuplicateOf().addAll(Arrays.asList(relationships));
        }
    }

    public void addDuplicateOf(StixDomainObject target, String description){
        Objects.requireNonNull(target, "target cannot be null");
        Relationship relationship = new Relationship(
                DUPLICATE_OF.toString(),
                (StixDomainObject)this,
                target);
        addDuplicateOf(relationship);
    }

    public void addDuplicateOf(StixDomainObject target){
        addDuplicateOf(target, null);
    }



    @JsonIgnore
    public LinkedHashSet<StixRelationshipObject> getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(LinkedHashSet<StixRelationshipObject> derivedFrom) {
        validateRelationshipClassEquality(DERIVED_FROM.toString(), derivedFrom);

        this.derivedFrom = derivedFrom;
    }

    public void addDerivedFrom(StixRelationshipObject... relationships){
        if (this.getDerivedFrom() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipClassEquality(DERIVED_FROM.toString(), relationshipObjects);

            this.setDerivedFrom(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipClassEquality(DERIVED_FROM.toString(), relationshipObjects);

            this.getDerivedFrom().addAll(Arrays.asList(relationships));
        }
    }

    public void addDerivedFrom(StixDomainObject target, String description){
        Objects.requireNonNull(target, "target cannot be null");
        Relationship relationship = new Relationship(
                DERIVED_FROM.toString(),
                (StixDomainObject)this,
                target);
        addDerivedFrom(relationship);
    }

    public void addDerivedFrom(StixDomainObject target){
        addDerivedFrom(target, null);
    }


    @JsonIgnore
    public LinkedHashSet<StixRelationshipObject> getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(LinkedHashSet<StixRelationshipObject> relatedTo) {
        validateRelationshipType(RELATED_TO.toString(), relatedTo);

        this.relatedTo = relatedTo;
    }

    public void addRelatedTo(StixRelationshipObject... relationships){
        if (this.getRelatedTo() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipType(RELATED_TO.toString(), relationshipObjects);

            this.setRelatedTo(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipType(RELATED_TO.toString(), relationshipObjects);

            this.getRelatedTo().addAll(Arrays.asList(relationships));
        }
    }

    public void addRelatedTo(StixDomainObject target, String description){
        Objects.requireNonNull(target, "target cannot be null");
        Relationship relationship = new Relationship(
                RELATED_TO.toString(),
                (StixDomainObject)this,
                target);
        addRelatedTo(relationship);
    }

    public void addRelatedTo(StixDomainObject target){
        addRelatedTo(target, null);
    }


    /**
     * Custom Properties Getter
     * @return
     */
    @JsonIgnore
    public LinkedHashSet<CustomStixRelationshipObject> getCustomRelationships() {
        return customRelationships;
    }

    /**
     * Custom Properties Setter
     * @param customRelationships
     */
    public void setCustomRelationships(LinkedHashSet<CustomStixRelationshipObject> customRelationships) {
        this.customRelationships = customRelationships;
    }


    /**
     * Returns the STIX-Spec-version of the specific STIX SDO.
     * @return
     */
    public String getSpecVersion() {
        return this.specVersion;
    }

    /**
     * Uses ReflectionToStringBuilder to convert object into String.
     * Primarily used for testing
     * @return
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Deep Converts the STIX SDO into a JSON string as per STIX JSON Spec.
     * @return String JSON string of STIX SDO
     * @throws JsonProcessingException
     */
    public String toJsonString() throws JsonProcessingException {
        return StixDataFormats.getJsonMapper().writeValueAsString(this);
    }

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllCommonPropertiesBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        bundleObjects.add(getCreatedByRef());
        if (getCustomRelationships() != null) {
            getCustomRelationships().forEach(cr -> {
                bundleObjects.add(cr.getSource());
                bundleObjects.add(cr.getTarget());
            });
        }

        if (getRelatedTo() != null) {
            getRelatedTo().forEach(rt -> {
                bundleObjects.add(rt.getSource());
                bundleObjects.add(rt.getTarget());
            });
        }

        if (getDuplicateOf() != null) {
            getDuplicateOf().forEach(dupOf -> {
                bundleObjects.add(dupOf.getSource());
                bundleObjects.add(dupOf.getTarget());
            });
        }

        if (getDerivedFrom() != null) {
            getDerivedFrom().forEach(df -> {
                bundleObjects.add(df.getSource());
                bundleObjects.add(df.getTarget());
            });
        }

        if (getObjectMarkingRefs() != null) {
            getObjectMarkingRefs().forEach(om -> {
                bundleObjects.add(om.getCreatedByRef());
                if (om.getObjectMarkingRefs() != null) {
                    bundleObjects.addAll(om.getObjectMarkingRefs());
                }

                if (om.getGranularMarkings() != null) {
                    if (om.getGranularMarkings() != null) {
                        om.getGranularMarkings().forEach(gm -> {
                            bundleObjects.add(gm.getMarkingRef());
                        });
                    }
                }
            });
        }

        if (getGranularMarkings() != null) {
            getGranularMarkings().forEach(gm -> {
                bundleObjects.add(gm.getMarkingRef());
            });
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
        // Ensure the obj is a instance of StixDomainObject
        // We can assume this because anything using CommonProperties should be implementing
        // StixDomainObject (or the CustomStixDomainObject) interface.
        if (!(obj instanceof StixDomainObject)) {
            return false;
        }

        StixDomainObject object = (StixDomainObject)obj;
        // Compare the ID and Modified Date fields to be equal.
        // Modified Date field is converted into UTC time for brevity
        return object.getId().equals(this.getId()) &&
                object.getModified().equals(this.getModified().withZoneSameInstant(ZoneId.of("Etc/UTC")));
    }
}
