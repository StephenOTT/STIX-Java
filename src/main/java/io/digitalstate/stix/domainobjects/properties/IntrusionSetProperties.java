package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "aliases", "first_seen", "last_seen", "goals",
        "resource_level", "primary_motivation", "secondary_motivation"})
public abstract class IntrusionSetProperties extends CommonProperties{

    @JsonInclude(NON_NULL)
    protected String name = null;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> aliases = null;

    @JsonProperty("first_seen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime firstSeen;

    @JsonProperty("last_seen")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = StixDataFormats.DATETIMEZONE)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime lastSeen;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> goals = null;

    @JsonProperty("resource_level")
    @JsonInclude(NON_NULL)
    protected String resourceLevel = null;

    @JsonProperty("primary_motivation")
    @JsonInclude(NON_NULL)
    protected String primaryMotivation = null;

    @JsonProperty("secondary_motivation")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> secondaryMotivations = null;

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

    public LinkedHashSet<String> getGoals() {
        return goals;
    }
    public void setGoals(LinkedHashSet<String> goals) {
        this.goals = goals;
    }

    public String getResourceLevel() {
        return resourceLevel;
    }
    public void setResourceLevel(String resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public String getPrimaryMotivation() {
        return primaryMotivation;
    }
    public void setPrimaryMotivation(String primaryMotivation) {
        this.primaryMotivation = primaryMotivation;
    }

    public LinkedHashSet<String> getSecondaryMotivations() {
        return secondaryMotivations;
    }
    public void setSecondaryMotivations(LinkedHashSet<String> secondaryMotivations) {
        this.secondaryMotivations = secondaryMotivations;
    }
}
