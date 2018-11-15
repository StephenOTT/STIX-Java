package io.digitalstate.stix.sdo.impl.properties;

import java.util.List;

public abstract class CourseOfActionProperties extends CommonProperties{
    protected String name;
    protected String description = null;
    protected List<String> action = null;
}
