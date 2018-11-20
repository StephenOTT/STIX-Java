package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.AttackPatternProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class AttackPattern extends AttackPatternProperties implements StixDomainObject {

    private static final String TYPE = "attack-pattern";

    public AttackPattern(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }
}
