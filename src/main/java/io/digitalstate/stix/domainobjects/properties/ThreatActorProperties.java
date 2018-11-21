package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "aliases", "roles", "goals", "sophistication",
        "resource_level", "primary_motivation", "secondary_motivation", "personal_motivations"})
public abstract class ThreatActorProperties extends CommonProperties{
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> aliases = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> roles = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> goals = null;

    @JsonInclude(NON_NULL)
    protected String sophistication = null;

    @JsonProperty("resource_level")
    @JsonInclude(NON_NULL)
    protected String resourceLevel = null;

    @JsonProperty("primary_motivation")
    @JsonInclude(NON_NULL)
    protected String primaryMotivation = null;

    @JsonProperty("secondary_motivation")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> secondaryMotivations = null;

    @JsonProperty("personal_motivations")
    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> personalMotivations = null;

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

    public LinkedHashSet<String> getRoles() {
        return roles;
    }

    public void setRoles(LinkedHashSet<String> roles) {
        this.roles = roles;
    }

    public LinkedHashSet<String> getGoals() {
        return goals;
    }

    public void setGoals(LinkedHashSet<String> goals) {
        this.goals = goals;
    }

    public String getSophistication() {
        return sophistication;
    }

    public void setSophistication(String sophistication) {
        this.sophistication = sophistication;
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

    public LinkedHashSet<String> getPersonalMotivations() {
        return personalMotivations;
    }

    public void setPersonalMotivations(LinkedHashSet<String> personalMotivations) {
        this.personalMotivations = personalMotivations;
    }
}
