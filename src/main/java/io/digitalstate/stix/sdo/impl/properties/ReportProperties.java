package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.impl.types.StixObject;

import java.time.LocalDateTime;
import java.util.List;

public abstract class ReportProperties extends CommonProperties {
    protected String name;
    protected String description = null;
    protected LocalDateTime published;
    protected List<StixObject> objectRefs;
}
