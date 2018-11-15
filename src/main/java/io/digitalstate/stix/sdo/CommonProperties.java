package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.impl.types.ExternalReference;

import java.time.LocalDateTime;
import java.util.List;

public interface CommonProperties {
    public String getType();
    public void setType(String type);

    public String getId();
    public void setId(String id);

    public String getCreatedByRef();
    public void setCreatedByRef(String createdByRef);

    public LocalDateTime getCreated();
    public void setCreated(LocalDateTime created);

    public LocalDateTime getModified();
    public void setModified(LocalDateTime modified);

    public Boolean revoked();
    public void revoked(Boolean revoked);

    public List<String> getLabels();
    public void setLabels(List<String> labels);

    public List<ExternalReference> getExternalReferences();
    public void setExternalReferences(List<ExternalReference> externalReferences);

    public List<String> getObjectMarkingRefs();
    public void setObjectMarkingRefs(List<String> objectMarkingRefs);

    public List<String> getGranularMarkings();
    public void setGranularMarkings(List<String> granularMarkings);
}
