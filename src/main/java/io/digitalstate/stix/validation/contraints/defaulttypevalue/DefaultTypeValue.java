package io.digitalstate.stix.validation.contraints.defaulttypevalue;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;


/**
 * <p>Sets the default value of the {@code type} field for a Bundleable object.
 * This annotation must only be applied on a Class level of a class that extends from Bundleable object
 * (Typically SDOs, SROs, COOs, COO-Extensions, and Marking Definitions).</p>
 * <br>
 * <p>This annotation will also validate a manually set {@code type} attribute.
 * If the {@code type} attribute does not equal the value set in this annotation, then validation will fail.</p>
 */
@Documented
@Constraint(validatedBy = {StixDefaultTypeValueBundleableValidator.class, StixDefaultTypeValueBundleObjectValidator.class, StixDefaultTypeValueCyberObservableValidator.class, StixDefaultTypeValueCyberObservableExtensionValidator.class})
@Target( { ANNOTATION_TYPE, TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultTypeValue {
    String message() default "{io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String value();

}
