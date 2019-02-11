package io.digitalstate.stix.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;


@Target({ METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { OptionalPattern.Validator.class })
@Documented
/**
 * Custom validator annotation for checking Strings in an Optional
 *
 */
public @interface OptionalPattern {
    String regexp();

    String message() default "{io.digitalstate.stix.validation.contraints.Optional}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Validator implements ConstraintValidator<OptionalPattern, Optional<String>> {
        String regexp;

        @Override
        public void initialize(OptionalPattern c) {
            this.regexp = c.regexp();
        }

        @Override
        public boolean isValid(Optional<String> item, ConstraintValidatorContext ctx) {
            if (item != null) {
                if (item.isPresent()) {
                    String str = item.get();
                    // if the string does not match the regex, return false
                    if (!str.matches(this.regexp)) {
                        String violationMessage = "Optional value: ${validatedValue} does not match {regexp} ";
                        ctx.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                        return false;
                    }
                }
                return true; // nothing to validate
            }

            return true;// Or throw exception when item is null
        }
    }
}
