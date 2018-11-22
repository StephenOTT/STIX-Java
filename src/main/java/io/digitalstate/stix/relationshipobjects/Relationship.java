package io.digitalstate.stix.relationshipobjects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.relationshipobjects.properties.RelationshipObjectCommonProperties;

import static io.digitalstate.stix.helpers.IdGeneration.*;

/**
 * Defines a Stix Relationship Object (SRO)
 * Has no business logic.  All logic is handled within the specific SDOs
 */
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "relationship_type", "description",
        "source", "target"})
public class Relationship extends RelationshipObjectCommonProperties implements StixRelationshipObject{

    private static final String TYPE = "relationship";

    public Relationship(String relationshipType,
                 StixDomainObject source,
                 StixDomainObject target,
                 String description){

        setType(TYPE);
        setId(generateUuidAsString());
        setRelationshipType(relationshipType);
        setSource(source);
        setTarget(target);
        setDescription(description);
    }

    public Relationship(String relationshipType,
                 StixDomainObject source,
                 StixDomainObject target){

        this(relationshipType, source, target, null);
    }
 }
