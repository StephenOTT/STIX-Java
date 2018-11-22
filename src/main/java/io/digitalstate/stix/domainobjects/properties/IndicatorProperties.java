package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.*;
import io.digitalstate.stix.helpers.RelationshipValidators;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.domainobjects.types.KillChainPhase;
import io.digitalstate.stix.relationshipobjects.Relationship;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "pattern", "valid_from", "valid_until", "kill_chain_phases"})
public abstract class IndicatorProperties extends CommonProperties{

    @JsonInclude(NON_NULL)
    protected String name = null;

    @JsonInclude(NON_NULL)
    protected String description = null;

    protected String pattern;

    @JsonProperty("valid_from")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime validFrom;

    @JsonProperty("valid_until")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime validUntil;

    @JsonProperty("kill_chain_phases")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<KillChainPhase> killChainPhases = null;

    //
    // Relationships
    //

    private LinkedHashSet<StixRelationshipObject> indicates = new LinkedHashSet<>();

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

    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public ZonedDateTime getValidFrom() {
        return validFrom;
    }
    public void setValidFrom(ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public ZonedDateTime getValidUntil() {
        return validUntil;
    }
    public void setValidUntil(ZonedDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public LinkedHashSet<KillChainPhase> getKillChainPhases() {
        return killChainPhases;
    }
    public void setKillChainPhases(LinkedHashSet<KillChainPhase> killChainPhases) {
        this.killChainPhases = killChainPhases;
    }


    //
    // Relationships
    //

    @JsonIgnore
    public LinkedHashSet<StixRelationshipObject> getIndicates() {
        return indicates;
    }

    public void setIndicates(LinkedHashSet<StixRelationshipObject> indicates) {
        RelationshipValidators.validateRelationshipAcceptableClasses("indicates",
                indicates, AttackPattern.class, Campaign.class, IntrusionSet.class,
                Malware.class, ThreatActor.class, Tool.class);

        this.indicates = indicates;
    }

    public void addIndicates(StixRelationshipObject... relationships){
        if (this.getIndicates() == null){
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("indicates",
                    relationshipObjects, AttackPattern.class, Campaign.class, IntrusionSet.class,
                    Malware.class, ThreatActor.class, Tool.class);

            this.setIndicates(new LinkedHashSet<>(Arrays.asList(relationships)));

        } else {
            LinkedHashSet<StixRelationshipObject> relationshipObjects = new LinkedHashSet<>(Arrays.asList(relationships));

            RelationshipValidators.validateRelationshipAcceptableClasses("indicates",
                    relationshipObjects, AttackPattern.class, Campaign.class, IntrusionSet.class,
                    Malware.class, ThreatActor.class, Tool.class);

            this.getIndicates().addAll(Arrays.asList(relationships));
        }
    }

    public void addIndicates(StixDomainObject indicates, String description){
        Objects.requireNonNull(indicates, "indicates cannot be null");

        Relationship relationship = new Relationship(
                "indicates",
                (StixDomainObject)this,
                indicates);
        addIndicates(relationship);
    }

    public void addIndicates(StixDomainObject indicates){
        addIndicates(indicates, null);
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
