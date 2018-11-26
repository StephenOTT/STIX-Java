package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.relationshipobjects.properties.RelationshipProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

/**
 * Defines a Stix Relationship Object (SRO)
 * Has minimal or no business logic.  All logic is handled within the specific SDOs
 */
public class Relationship extends RelationshipProperties implements StixRelationshipObject{

    private static final String TYPE = "relationship";

    public Relationship(String relationshipType,
                 StixDomainObject source,
                 StixDomainObject target,
                 String description){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
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
