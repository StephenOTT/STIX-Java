package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.impl.types.StixObject;

import java.time.LocalDateTime;
import java.util.List;

public interface Report extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public LocalDateTime getPublishedDateTime();
    public void setPublishedDateTime(LocalDateTime publishedDateTime);

    public List<StixObject> getObjectRefs();
    public void setObjectRefs(List<StixObject> objectRefs);
}
