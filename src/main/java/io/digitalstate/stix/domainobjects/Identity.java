package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.IdentityProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Identity extends IdentityProperties implements StixDomainObject {

    private static final String TYPE = "identity";

    public Identity(String name, String identityClass){
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
        setIdentityClass(identityClass);
    }
}
