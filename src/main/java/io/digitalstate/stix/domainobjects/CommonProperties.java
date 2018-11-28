package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.domainobjects.types.ExternalReference;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.Relationship;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;

interface CommonProperties extends DataMarkingsAppliable {
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

    LinkedHashSet<Relation<Relationship>> getDuplicateOf();
    void setDuplicateOf(LinkedHashSet<Relation<Relationship>> duplicateOf);

    LinkedHashSet<Relation<Relationship>> getDerivedFrom();
    void setDerivedFrom(LinkedHashSet<Relation<Relationship>> duplicateOf);

    LinkedHashSet<Relation<Relationship>> getRelatedTo();
    void setRelatedTo(LinkedHashSet<Relation<Relationship>> duplicateOf);

    String toJsonString() throws JsonProcessingException;

    LinkedHashSet<BundleObject> getAllCommonPropertiesBundleObjects();

    void hydrateCommonRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects);
}
