package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.impl.types.CyberObservableObject;

import java.time.LocalDateTime;
import java.util.List;

public abstract class ObservedDataProperties extends CommonProperties{
    protected LocalDateTime firstObserved = null;
    protected LocalDateTime lastObserved = null;
    protected Integer numberObserved = null;
    protected List<CyberObservableObject> objects;
    protected String objective = null;
}
