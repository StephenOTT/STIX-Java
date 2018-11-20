package io.digitalstate.stix.datamarkings;

import io.digitalstate.stix.bundle.BundleObjects;
import io.digitalstate.stix.datamarkings.markingtypes.MarkingObjectType;
import io.digitalstate.stix.domainobjects.types.ExternalReference;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;

public interface StixDataMarking extends DataMarkingsAppliable, BundleObjects {

    String getType();
    void setType(String type);

    String getId();
    void setId(String id);

    String getCreatedByRef();
    void setCreatedByRef(String createdByRef);

    ZonedDateTime getCreated();
    void setCreated(ZonedDateTime created);

    LinkedHashSet<ExternalReference> getExternalReferences();
    void setExternalReferences(LinkedHashSet<ExternalReference> externalReferences);

    String getDefinitionType();
    void setDefinitionType(String definitionType);

    MarkingObjectType getDefinition();
    void setDefinition(MarkingObjectType definition);
}
