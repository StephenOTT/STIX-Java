package io.digitalstate.stix.validation.contraints.relationship;

import io.digitalstate.stix.sdo.DomainObject;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

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
