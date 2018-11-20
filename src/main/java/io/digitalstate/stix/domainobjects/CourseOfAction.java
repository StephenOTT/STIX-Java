package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.CourseOfActionProperties;
import org.apache.commons.lang3.StringUtils;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class CourseOfAction extends CourseOfActionProperties implements StixDomainObject {

    private static final String TYPE = "course-of-action";

    public CourseOfAction(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }
}
