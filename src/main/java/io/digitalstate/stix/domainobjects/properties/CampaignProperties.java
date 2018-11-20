package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class CampaignProperties extends CommonProperties{
    protected String name;

    @JsonInclude(NON_NULL)
    protected String description = null;

    @JsonInclude(NON_NULL)
    protected LinkedHashSet<String> aliases = null;

    @JsonProperty("first_seen")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime firstSeen = null;

    @JsonProperty("last_seen")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime lastSeen = null;

    @JsonInclude(NON_NULL)
    protected String objective = null;

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

    public String getObjective() {
        return objective;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
}
