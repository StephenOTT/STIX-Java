package io.digitalstate.stix.sdo;

import java.util.List;

public interface CourseOfAction extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public List<String> getAction();
    public void setAction(List<String> action);
}
