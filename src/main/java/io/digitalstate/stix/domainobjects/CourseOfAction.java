package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.CourseOfActionProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class CourseOfAction extends CourseOfActionProperties implements StixDomainObject {

    private static final String TYPE = "course-of-action";

    public CourseOfAction(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }
}
