package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.domainobjects.properties.ObservedDataProperties;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class ObservedData extends ObservedDataProperties implements StixDomainObject {

    private static final String TYPE = "observed-data";

    public ObservedData(ZonedDateTime firstObserved,
                        ZonedDateTime lastObserved,
                        Integer numberObserved,
                        HashMap<String, CyberObservableObject> objects){

        setType(TYPE);
        setId(generateUuidAsString());
        setFirstObserved(firstObserved);
        setLastObserved(lastObserved);
        setNumberObserved(numberObserved);
        setObjects(objects);
    }

    @Override
    public void setFirstObserved(ZonedDateTime firstObserved) {
        if (firstObserved != null){
            this.firstObserved = firstObserved;
        } else {
            throw new IllegalArgumentException("firstObserved can't be null");
        }
    }

    @Override
    public void setLastObserved(ZonedDateTime lastObserved) {
        if (lastObserved != null){
            this.lastObserved = lastObserved;
        } else {
            throw new IllegalArgumentException("lastObserved can't be null");
        }
    }

    @Override
    public void setNumberObserved(Integer numberObserved) {
        if (numberObserved != null && numberObserved >= 1){
            this.numberObserved = numberObserved;
        } else {
            throw new IllegalArgumentException("numberObserved can't be null and must be greater or equal to 1");
        }
    }

    @Override
    public void setObjects(HashMap<String, CyberObservableObject> objects) {
        if (!objects.isEmpty()){
            this.objects = objects;
        } else {
            throw new IllegalArgumentException("Cannot be null and at least one CyberObservableObjectCommonProperties must be provided");
        }
    }
}
