package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.domainobjects.Identity;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.helpers.JsonConvertable;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;

public interface StixRelationshipObject extends DataMarkingsAppliable, BundleObject, JsonConvertable {

    String getType();
    void setType(String type);

    String getId();
    void setId(String id);

    Relation<Identity> getCreatedByRef();
    void setCreatedByRef(Relation<Identity> createdByRef);

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

    LinkedHashSet<BundleObject> getAllCommonPropertiesBundleObjects();

    LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects();

    void hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects);

}
