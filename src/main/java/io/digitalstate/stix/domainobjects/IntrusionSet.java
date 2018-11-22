package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.IntrusionSetProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class IntrusionSet extends IntrusionSetProperties implements StixDomainObject{

    private static final String TYPE = "intrusion-set";

    public IntrusionSet(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }
}
