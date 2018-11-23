package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.SightingProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Sighting extends SightingProperties implements StixDomainObject {

    private static final String TYPE = "sighting";

    public Sighting(StixDomainObject sightingOfRef){
        setType(TYPE);
        setId(generateUuidAsString());
        setSightingOfRef(sightingOfRef);
    }
}
