package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.domainobjects.AttackPattern;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.relationshipobjects.CustomStixRelationshipObject;
import io.digitalstate.stix.relationshipobjects.Relationship;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;
import static io.digitalstate.stix.helpers.RelationshipValidators.validateRelationshipClassEquality;
import static io.digitalstate.stix.helpers.RelationshipValidators.validateRelationshipType;
import static io.digitalstate.stix.relationshipobjects.RelationshipTypes.*;

/**
 * Abstract class to define base common properties found in Stix Objects
 */
public abstract class CommonProperties implements DataMarkingsAppliable {

    // Property Order is set at the super level of the individual STIX SDO classes.
    // This was due to JsonPropertyOrder limitation of extended classes

    @JsonIgnore
    private final String specVersion = StixSpecVersion.SPECVERSION;

    /**
     * <p>The type property identifies the type of STIX Object.</p>
     * <p>The value of the type property MUST be the name of one of the types of STIX Object defined in sections 2 and 3 of STIX™ Version 2.0. Part 2: STIX Objects.</p>
     * <p>(e.g., indicator) or the name of a custom object as defined by section 7.2.</p>
     */
    private String type;

    /**
     * <p>The id property universally and uniquely identifies this object.</p>
     * <p>All objects with the same id are considered different versions of the same object.</p>
     * <p>Because the object type is part of the identifier, it is invalid for objects of different types to share the same id.</p>
     */
    private String id;

    /**
     * <p>The created_by_ref property specifies the ID of the Identity object that describes the entity that created this object.</p>
     * <p>If this attribute is omitted, the source of this information is undefined.</p>
     * <p>This may be used by object creators who wish to remain anonymous.</p>
     */
    private Identity createdByRef = null;

    /**
     * <p>The created property represents the time at which the first version of this object was created.
     * The object creator can use the time it deems most appropriate as the time the object was created.</p>
     *
     * <p>The created property MUST NOT be changed when creating a new version of the object.</p>
     *
     * <p>The created timestamp MUST be precise to the nearest millisecond (exactly three digits after the decimal place in seconds).</p>
     *
     * <p>See section 3.4 for further definition of versioning.</p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime created = ZonedDateTime.now();

    /**
     * <p>The modified property represents the time that this particular version of the object was created.
     * The object creator can use the time it deems most appropriate as the time this version of the object was modified.
     * The value of the modified property for a given object version MUST be later than or equal to the value of the created property.</p>
     *
     * <p Object creators MUST set the modified property when creating a new version of an object.</p>
     *
     * <p>The modified timestamp MUST be precise to the nearest millisecond (exactly three digits after the decimal place in seconds).</p>
     *
     * <p>See section 3.4 for further definition of versioning.</p>
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime modified = this.created;

    /**
     * <p>The revoked property indicates whether the object has been revoked.
     * Revoked objects are no longer considered valid by the object creator.
     * Revoking an object is permanent; future versions of the object with this id MUST NOT be created.</p>
     *
     * <p>The default value of this property is false.</p>
     *
     * <p>See section 3.4 for further definition of versioning.</p>
     */
    private boolean revoked = false;

    /**
     * <p>The labels property specifies a set of classifications.</p>
     *
     * <p>Each STIX Object can define a suggested vocabulary for the labels property.
     * For example, the Indicator object, as defined in section 2.5 of STIX™ Version 2.0. Part 2: STIX Objects,
     * uses the Indicator Label vocabulary as defined in section 6.5.</p>
     *
     * <p>In some cases (generally, when a suggested vocabulary is defined) the labels property is then required for that specific SDO.</p>
     *
     * <p>If a vocabulary is defined, items in this list SHOULD come from the vocabulary.
     * Additional labels MAY be added beyond what is in the suggested vocabulary.</p>
     */
    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> labels = null;

    /**
     * <p>The external_references property specifies a list of external references which refers to non-STIX information.
     * This property is used to provide one or more URLs, descriptions, or IDs to records in other systems.</p>
     */
    @JsonProperty("external_references")
    @JsonInclude(NON_NULL)
    private LinkedHashSet<ExternalReference> externalReferences = null;

    /**
     * <p>The object_marking_refs property specifies a list of IDs of marking-definition objects that apply to this object.</p>
     *
     * <p>See section 4 for further definition of data markings.</p>
     */
    private LinkedHashSet<MarkingDefinition> objectMarkingRefs = null;

    /**
     * <p>The granular_markings property specifies a list of granular markings applied to this object.</p>
     *
     * <p>See section 4 for further definition of data markings.</p>
     */
    @JsonProperty("granular_markings")
    @JsonInclude(NON_NULL)
    private LinkedHashSet<GranularMarking> granularMarkings = null;

    /**
     * <p>There will be cases where certain information exchanges can be improved by adding properties
     * that are neither specified nor reserved in this document; these properties are called Custom Properties.
     * This section provides guidance and requirements for how producers can use Custom Properties and
     * how consumers should interpret them in order to extend STIX in an interoperable manner.</p>
     *
     * <p>A STIX Object MAY have any number of Custom Properties.</p>
     *
     * <p>Custom Property names MUST be in ASCII and MUST only contain the characters a–z (lowercase ASCII), 0–9, and underscore (_).</p>
     *
     * <p>Custom Property names SHOULD start with “x_” followed by a source unique identifier (such as a domain name with dots replaced by underscores),
     * an underscore and then the name. For example, x_example_com_customfield.</p>
     *
     * <p>Custom Property names MUST have a minimum length of 3 ASCII characters.</p>
     *
     * <p>Custom Property names MUST be no longer than 250 ASCII characters in length.</p>
     *
     * <p>Custom Property names that do not start with “x_” may be used in a future version of the specification for a different meaning.
     * If compatibility with future versions of this specification is required, the “x_” prefix MUST be used.</p>
     *
     * <p>Custom Properties SHOULD only be used when there is no existing properties defined by the STIX specification that fulfils that need.</p>
     *
     * <p>Example:</p>
     * <pre>
     * {
     *   ...,
     *   "x_acme_org_confidence": 10,
     *   "x_acme_org_scoring": {
     *     "impact": "high",
     *     "probability": "low"
     *   },
     *   ...
     * }
     * </pre>
     *
     * <p>Custom properties do not require you add the "x_"; it will be added for you when the JSON is generated.</p>
     */
    private HashMap<String, Object> customProperties = null;

    // Common Relationship Properties

    /**
     * <p>The referenced source and target objects are semantically duplicates of each other.</p>
     *
     * <p>This specification does not address whether the source or the target object is the duplicate object or
     * what action, if any, a consumer should take when receiving an instance of this relationship.</p>
     *
     * <p>As an example, a Campaign object from one organization could be marked as a duplicate-of a Campaign object
     * from another organization if they both described the same campaign.</p>
     */
    private LinkedHashSet<Relationship> duplicateOf = null;

    /**
     * <p>The information in the target object is based on information from the source object.</p>
     *
     * <p>derived-from is an explicit relationship between two separate objects and MUST NOT be used as a substitute
     * for the versioning process defined in section 3.4.</p>
     */
    private LinkedHashSet<Relationship> derivedFrom = null;

    /**
     * <p>The Relationship of Related-To asserts a non-specific relationship between two SDOs. This relationship can be used when none of the other
     * predefined relationships are appropriate.</p>
     *
     * <p>As an example, a Malware object describing a piece of malware could be marked as a related-to a Tool
     * if they are commonly used together. That relationship is not common enough to standardize
     * on, but may be useful to some analysts.</p>
     */
    private LinkedHashSet<Relationship> relatedTo = null;

    /**
     * <p>Custom Relationships: Relationships that are defined using the CustomStixRelationshipObject interface.</p>
     */
    private LinkedHashSet<CustomStixRelationshipObject> customRelationships = null;

    //
    // Getters and Setters
    //

    /**
     * Get the SDO Type
     * <br>
     * See {@link CommonProperties#type}
     * @return String type value.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the SDO Type
     * <br>
     * See {@link CommonProperties#type}
     */
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

    @JsonIgnore
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

    @JsonIgnore
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
    public LinkedHashSet<Relationship> getDuplicateOf() {
        return duplicateOf;
    }

    public void setDuplicateOf(LinkedHashSet<Relationship> duplicateOf) {
        validateRelationshipClassEquality(
                DUPLICATE_OF.toString(), duplicateOf);

        this.duplicateOf = duplicateOf;
    }

    public void addDuplicateOf(Relationship... relationships){
        Objects.requireNonNull(relationships, "relationships cannot be null");

        if (this.getDuplicateOf() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipClassEquality(DUPLICATE_OF.toString(), relationshipObjects);

            this.setDuplicateOf(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
        Objects.requireNonNull(target, "target cannot be null");
        addDuplicateOf(target, null);
    }



    @JsonIgnore
    public LinkedHashSet<Relationship> getDerivedFrom() {
        return derivedFrom;
    }

    public void setDerivedFrom(LinkedHashSet<Relationship> derivedFrom) {
        validateRelationshipClassEquality(DERIVED_FROM.toString(), derivedFrom);

        this.derivedFrom = derivedFrom;
    }

    public void addDerivedFrom(Relationship... relationships){
        Objects.requireNonNull(relationships, "relationships cannot be null");

        if (this.getDerivedFrom() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipClassEquality(DERIVED_FROM.toString(), relationshipObjects);

            this.setDerivedFrom(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
        Objects.requireNonNull(target, "target cannot be null");
        addDerivedFrom(target, null);
    }


    @JsonIgnore
    public LinkedHashSet<Relationship> getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(LinkedHashSet<Relationship> relatedTo) {
        validateRelationshipType(RELATED_TO.toString(), relatedTo);

        this.relatedTo = relatedTo;
    }

    public void addRelatedTo(Relationship... relationships){
        Objects.requireNonNull(relationships, "relationships cannot be null");

        if (this.getRelatedTo() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            validateRelationshipType(RELATED_TO.toString(), relationshipObjects);

            this.setRelatedTo(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

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
        Objects.requireNonNull(target, "target cannot be null");
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

    /**
     * Returns all common properties' bundle objects.
     * This method is typically used when creating a STIX bundle and you want to detect nested objects
     * that should be added into the Bundle as first class objects (non-nested)
     * @return
     */
    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllCommonPropertiesBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        bundleObjects.add(getCreatedByRef());
        if (getCustomRelationships() != null) {
            getCustomRelationships().forEach(cr -> {
                Objects.requireNonNull(cr.getBundleObjects(), "Bundle Objects cannot be null");
                bundleObjects.addAll(cr.getBundleObjects());
            });
        }

        if (getRelatedTo() != null) {
            getRelatedTo().forEach(rt -> {
                if (rt.getSource().hasObject()){
                    bundleObjects.add(rt.getSource().getObject());
                }

                if (rt.getTarget().hasObject()){
                    bundleObjects.add(rt.getTarget().getObject());
                }
            });
        }

        if (getDuplicateOf() != null) {
            getDuplicateOf().forEach(dupOf -> {
                if (dupOf.getSource().hasObject()){
                    bundleObjects.add(dupOf.getSource().getObject());
                }

                if (dupOf.getTarget().hasObject()){
                    bundleObjects.add(dupOf.getTarget().getObject());
                }
            });
        }

        if (getDerivedFrom() != null) {
            getDerivedFrom().forEach(df -> {
                if (df.getSource().hasObject()){
                    bundleObjects.add(df.getSource().getObject());
                }

                if (df.getTarget().hasObject()){
                    bundleObjects.add(df.getTarget().getObject());
                }
            });
        }

        if (getObjectMarkingRefs() != null) {
            getObjectMarkingRefs().forEach(om -> {
                bundleObjects.add(om);

                if (om.getCreatedByRef() != null){
                    bundleObjects.add(om.getCreatedByRef());
                }

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

    /**
     * Overridden: StixDomainObjects have hashcode() method overridden in the CommonProperties class.
     * hashcode() now uses: ID and Modified property as hashcode values.
     * Modified Date will be converted to UTC for brevity of comparison
     * @return hashcode of object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModified().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE)));
    }

    /**
     * Overridden: StixDomainObjects have equals() method overridden in the CommonProperties class.
     * equals() now uses: Must be Instance of StixDomainObject, classes must be equal,
     * and the ID property and Modified Data Property must be equal.
     * Modified Date will be converted to UTC for brevity of comparison
     * @param obj
     * @return boolean representing the result of the comparison
     */
    @Override
    public boolean equals(Object obj) {
        // Ensure the obj is a instance of StixDomainObject
        // We can assume this because anything using CommonProperties should be implementing
        // StixDomainObject (or the CustomStixDomainObject) interface.
        if (!(obj instanceof StixDomainObject)) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }

        StixDomainObject object = (StixDomainObject)obj;
        // Compare the ID and Modified Date fields to be equal.
        // Modified Date field is converted into UTC time for brevity
        return object.getId().equals(this.getId()) &&
                object.getModified().equals(this.getModified().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE)));
    }
}
