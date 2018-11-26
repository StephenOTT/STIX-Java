package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.Relationship;
import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "attributed_to",
        "targets", "uses", "name", "description",
        "aliases", "first_seen", "last_seen", "objective"})
public abstract class CampaignProperties extends CommonProperties{
    private String name;

    @JsonInclude(NON_NULL)
    private String description = null;

    @JsonInclude(NON_NULL)
    private LinkedHashSet<String> aliases = null;

    @JsonProperty("first_seen")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime firstSeen = null;

    @JsonProperty("last_seen")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    private ZonedDateTime lastSeen = null;

    @JsonInclude(NON_NULL)
    private String objective = null;

    // Relationships
    private LinkedHashSet<Relationship> attributedTo = new LinkedHashSet<>();
    private LinkedHashSet<Relationship> targets = new LinkedHashSet<>();
    private LinkedHashSet<Relationship> uses = new LinkedHashSet<>();

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

    public LinkedHashSet<String> getAliases() {
        return aliases;
    }

    public void setAliases(LinkedHashSet<String> aliases) {
        this.aliases = aliases;
    }

    public ZonedDateTime getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(ZonedDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }

    public ZonedDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(ZonedDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<Relationship> getAttributedTo() {
        return attributedTo;
    }

    public void setAttributedTo(LinkedHashSet<Relationship> attributedTo) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                attributedTo, IntrusionSet.class, ThreatActor.class);

        this.attributedTo = attributedTo;
    }

    public void addAttributedTo(Relationship... relationships){
        if (this.getAttributedTo() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, IntrusionSet.class, ThreatActor.class);

            this.setAttributedTo(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, IntrusionSet.class, ThreatActor.class);

            this.getAttributedTo().addAll(Arrays.asList(relationships));
        }
    }

    public void addAttributedTo(StixDomainObject attributedTo, String description){
        Objects.requireNonNull(attributedTo, "use cannot be null");
        Relationship relationship = new Relationship(
                "attributed-to",
                (StixDomainObject)this,
                attributedTo);
        addAttributedTo(relationship);
    }

    public void addAttributedTo(StixDomainObject attributedTo){
        addUse(attributedTo, null);
    }

    @JsonIgnore
    public LinkedHashSet<Relationship> getTargets() {
        return targets;
    }

    public void setTargets(LinkedHashSet<Relationship> targets) {
        RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                targets, Identity.class, Vulnerability.class);

        this.targets = targets;
    }

    public void addTargets(Relationship... relationships){
        if (this.getTargets() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                    relationshipObjects, Identity.class, Vulnerability.class);

            this.setTargets(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("targets",
                    relationshipObjects, Identity.class, Vulnerability.class);

            this.getTargets().addAll(Arrays.asList(relationships));
        }
    }

    public void addTarget(StixDomainObject target, String description){
        Objects.requireNonNull(target, "target cannot be null");
        Relationship relationship = new Relationship(
                "targets",
                (StixDomainObject)this,
                target);
        addTargets(relationship);
    }

    public void addTarget(StixDomainObject target){
        addTarget(target, null);
    }

    @JsonIgnore
    public LinkedHashSet<Relationship> getUses() {
        return uses;
    }

    public void setUses(LinkedHashSet<Relationship> uses) {
        RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                uses, AttackPattern.class, Malware.class, Tool.class);

        this.uses = uses;
    }

    public void addUses(Relationship... relationships){
        if (this.getUses() == null){
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class);

            this.setUses(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<Relationship> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("uses",
                    relationshipObjects, AttackPattern.class, Malware.class, Tool.class);

            this.getUses().addAll(Arrays.asList(relationships));
        }
    }

    public void addUse(StixDomainObject use, String description){
        Objects.requireNonNull(use, "use cannot be null");
        Relationship relationship = new Relationship(
                "uses",
                (StixDomainObject)this,
                use);
        addUses(relationship);
    }

    public void addUse(StixDomainObject use){
        addUse(use, null);
    }

    //
    // Helpers
    //

    @JsonIgnore
    public LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects(){
        LinkedHashSet<BundleObject> bundleObjects = new LinkedHashSet<>();

        bundleObjects.addAll(getAttributedTo());
        bundleObjects.addAll(getTargets());
        bundleObjects.addAll(getUses());

        return bundleObjects;
    }
}
