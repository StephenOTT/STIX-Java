package io.digitalstate.stix.validation.contraints.allowedparents;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * Used to Validation Cyber Observable Extensions
 * Should only be placed on Cyber Observable Object classes.
 */
@Documented
@Constraint(validatedBy = {StixValidateParentValidator.class})
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateExtensions {
    String message() default "{io.digitalstate.stix.validation.contraints.allowedparents.ValidateExtensions}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
