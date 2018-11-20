package io.digitalstate.stix.domainobjects.properties;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public abstract class ObservedDataProperties extends CommonProperties{

    @JsonProperty("first_observed")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime firstObserved = null;

    @JsonProperty("last_observed")
    @JsonInclude(NON_NULL)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN)
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    protected ZonedDateTime lastObserved = null;

    @JsonProperty("number_observed")
    @JsonInclude(NON_NULL)
    protected Integer numberObserved = null;

    protected LinkedHashSet<CyberObservableObject> objects;

    @JsonInclude(NON_NULL)
    protected String objective = null;

    //
    // Getters and Setters
    //

    public ZonedDateTime getFirstObserved() {
        return firstObserved;
    }
    public void setFirstObserved(ZonedDateTime firstObserved) {
        this.firstObserved = firstObserved;
    }

    public ZonedDateTime getLastObserved() {
        return lastObserved;
    }
    public void setLastObserved(ZonedDateTime lastObserved) {
        this.lastObserved = lastObserved;
    }

    public Integer getNumberObserved() {
        return numberObserved;
    }
    public void setNumberObserved(Integer numberObserved) {
        this.numberObserved = numberObserved;
    }

    public LinkedHashSet<CyberObservableObject> getObjects() {
        return objects;
    }
    public void setObjects(LinkedHashSet<CyberObservableObject> objects) {
        this.objects = objects;
    }

    public String getObjective() {
        return objective;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
}
