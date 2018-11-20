package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class ReportProperties extends CommonProperties {
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime published;

    @JsonProperty("object_refs")
    protected LinkedHashSet<String> objectRefs;

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

    public ZonedDateTime getPublished() {
        return published;
    }
    public void setPublished(ZonedDateTime published) {
        this.published = published;
    }

    public LinkedHashSet<String> getObjectRefs() {
        return objectRefs;
    }
    public void setObjectRefs(LinkedHashSet<String> objectRefs) {
        this.objectRefs = objectRefs;
    }
}
