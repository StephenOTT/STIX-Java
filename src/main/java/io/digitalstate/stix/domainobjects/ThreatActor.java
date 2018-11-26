package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.ThreatActorProperties;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class ThreatActor extends ThreatActorProperties implements StixDomainObject {

    private static final String TYPE = "threat-actor";

    public ThreatActor(String name,
                       LinkedHashSet<String> threatActorLabels){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setName(name);
        setLabels(threatActorLabels);
    }
    public ThreatActor(String name, String... threatActorLabels){
        this(name, new LinkedHashSet<>(Arrays.asList(threatActorLabels)));
    }
}
