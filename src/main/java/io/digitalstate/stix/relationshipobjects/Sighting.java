package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.relationshipobjects.properties.SightingProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Sighting extends SightingProperties implements StixRelationshipObject {

    private static final String TYPE = "sighting";

    public Sighting(StixDomainObject sightingOfRef){
        setType(TYPE);
        setId(generateUuidAsString());
        setSightingOfRef(sightingOfRef);
    }
}
