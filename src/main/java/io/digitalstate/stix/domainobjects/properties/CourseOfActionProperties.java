package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class CourseOfActionProperties extends CommonProperties{
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> action = null;

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

    public LinkedHashSet<String> getAction() {
        return action;
    }
    public void setAction(LinkedHashSet<String> action) {
        this.action = action;
    }
}
