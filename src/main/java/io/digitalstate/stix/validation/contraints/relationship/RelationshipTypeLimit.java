package io.digitalstate.stix.validation.contraints.relationship;

import io.digitalstate.stix.sdo.DomainObject;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * To only be used on STIX Relationship class.
 * The annotation provides a Javax Validation that looks at the RelationshipType being used.
 * This annotation enforces the STIX Relationship Type restrictions for each SDO.
 */
@Documented
@Constraint(validatedBy = {StixRelationshipTypeLimitValidator.class})
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RelationshipTypeLimit.List.class)
public @interface RelationshipTypeLimit {
    String message() default "{io.digitalstate.stix.validation.contraints.relationship.RelationshipTypeLimit}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean enforceCommonRelationshipTypes() default true;
    // @TODO look to use reflection to populate the Common Relationship Types default values.
    String[] commonRelationshipTypes() default {"duplicate-of", "derived-from", "related-to"};
    Class<? extends DomainObject> source();
    String[] relationshipTypes();

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target( { ANNOTATION_TYPE, TYPE })
    @interface List {
        RelationshipTypeLimit[] value();
    }
}
