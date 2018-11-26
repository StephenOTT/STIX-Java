package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.domainobjects.properties.ObservedDataProperties;

import java.time.ZonedDateTime;
import java.util.HashMap;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class ObservedData extends ObservedDataProperties implements StixDomainObject {

    private static final String TYPE = "observed-data";

    public ObservedData(ZonedDateTime firstObserved,
                        ZonedDateTime lastObserved,
                        Integer numberObserved,
                        HashMap<String, CyberObservableObject> objects){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setFirstObserved(firstObserved);
        setLastObserved(lastObserved);
        setNumberObserved(numberObserved);
        setObjects(objects);
    }
}
