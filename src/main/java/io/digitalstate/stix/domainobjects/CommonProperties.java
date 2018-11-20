package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.domainobjects.types.ExternalReference;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;

interface CommonProperties extends DataMarkingsAppliable {
    String getType();
    void setType(String type);

    String getId();
    void setId(String id);

    String getCreatedByRef();
    void setCreatedByRef(String createdByRef);

    ZonedDateTime getCreated();
    void setCreated(ZonedDateTime created);

    ZonedDateTime getModified();
    void setModified(ZonedDateTime modified);

    boolean getRevoked();
    void setRevoked(boolean revoked);

    LinkedHashSet<String> getLabels();
    void setLabels(LinkedHashSet<String> labels);

    LinkedHashSet<ExternalReference> getExternalReferences();
    void setExternalReferences(LinkedHashSet<ExternalReference> externalReferences);

    HashMap<String,Object> getCustomProperties();
    void setCustomProperties(HashMap<String,Object> customProperties);
}
