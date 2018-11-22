package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.domainobjects.types.ExternalReference;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;

public interface StixRelationshipObject extends BundleObject {

    String getType();
    void setType(String type);

    String getId();
    void setId(String id);

    Identity getCreatedByRef();
    void setCreatedByRef(Identity createdByRef);

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



    String getRelationshipType();
    void setRelationshipType(String relationshipType);

    String getDescription();
    void setDescription(String description);

    StixDomainObject getSource();
    void setSource(StixDomainObject source);

    StixDomainObject getTarget();
    void setTarget(StixDomainObject target);

}
