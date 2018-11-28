package io.digitalstate.stix.helpers;

import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.relationshipobjects.Relation;
import io.digitalstate.stix.relationshipobjects.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

public class RelationshipValidators {

    /**
     * Validate that Source and Target are equal classes in a Stix Relationship Object (SRO)
     * @param relationshipType
     * @param relationships
     * @return
     */
    public static void validateRelationshipClassEquality(String relationshipType, LinkedHashSet<Relation<Relationship>> relationships){
        Objects.requireNonNull(relationshipType, "relationshipType cannot be null");
        Objects.requireNonNull(relationships, "relationship cannot be null");

        relationships.forEach(relationship -> {
            if (relationship.hasObject()){
                if (!relationship.getObject().getRelationshipType().equals(relationshipType)){
                    throw new IllegalArgumentException("Invalid relationship type.  Relationship should have type " + relationshipType + " but found type: " + relationship.getObject().getRelationshipType());
                } else if (!relationship.getObject().getSource().getObject().getClass().equals(relationship.getObject().getTarget().getObject().getClass())){
                    throw new IllegalArgumentException(relationshipType + " relationships must be with the same SDO Object Type.");
                }
            } else if (!relationship.hasObject()){
                //@TODO Check ID Style
            }

        });
    }

    public static void validateRelationshipType(String relationshipType, LinkedHashSet<Relation<Relationship>> relationships){
        Objects.requireNonNull(relationshipType, "relationshipType cannot be null");
        Objects.requireNonNull(relationships, "relationship cannot be null");

        relationships.forEach(relationship -> {
            if (relationship.hasObject()){
                if (!relationship.getObject().getRelationshipType().equals(relationshipType)){
                    throw new IllegalArgumentException("Invalid relationship type.  Relationship should have type " + relationshipType + " but found type: " + relationship.getObject().getRelationshipType());
                }
            } else if (!relationship.hasObject()){
                //@TODO Check ID Style
            }

        });
    }

    @SafeVarargs
    public static void validateRelationshipAcceptableClasses(String relationshipType, LinkedHashSet<Relation<Relationship>> relationships, Class<? extends StixDomainObject>... acceptableClasses){
        //@TODO Review varargs usage and the workaround for the SafeVarargs Annotation.  It feels hacky atm.

        Objects.requireNonNull(relationships, "relationship cannot be null");
        Objects.requireNonNull(acceptableClasses, "acceptableClasses cannot be null");

        LinkedHashSet<Class<? extends StixDomainObject>> classes = new LinkedHashSet<>(Arrays.asList(acceptableClasses));

        relationships.forEach(relationship -> {
            if (relationship.hasObject()){
                if (!relationship.getObject().getRelationshipType().equals(relationshipType)){
                    throw new IllegalArgumentException("Invalid relationship type.  Relationship should have type " + relationshipType + " but found type: " + relationship.getObject().getRelationshipType());

                } else if (relationship.getObject().getTarget().hasObject()){
                    if (!classes.contains(relationship.getObject().getTarget().getObject().getClass())) {
                        // @TODO build in more robust error message
                        throw new IllegalArgumentException("Not a acceptable class for relationship target");
                    }
                } else if (!relationship.getObject().getTarget().hasObject()){
                    // @TODO Add error handling for when the target does not have a object
                }
            } else if (!relationship.hasObject()){
                //@TODO Check ID Style
            }
        });
    }

}
