package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.impl.types.ExternalReference;
import io.digitalstate.stix.sdo.impl.types.StixObject;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Abstract class to define base common properties found in Stix Objects
 */
public abstract class CommonProperties extends StixObject {
    protected String type;
    protected String id;
    protected String createdByRef = null;
    protected LocalDateTime created = LocalDateTime.now();
    protected LocalDateTime modified = LocalDateTime.now();
    protected Boolean revoked = false;
    protected List<String> labels = null;
    protected List<ExternalReference> externalReferences = null;
    protected List<String> objectMarkingRefs = null;
    protected List<String> granularMarkings = null;
}
