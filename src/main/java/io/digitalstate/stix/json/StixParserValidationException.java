package io.digitalstate.stix.json;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.Set;

public class StixParserValidationException extends RuntimeException {

    public StixParserValidationException(ValidationException exceptionCause) {
        super(exceptionCause);
    }

    /**
     * If Cause is ${@link ConstraintViolationException},
     * then returns the set of constraint validations, or else returns null.
     * @return Set of Constraint Violations
     */
    public Set<ConstraintViolation<?>> getConstraintValidations() {
            if (getCause().getClass().equals(ConstraintViolationException.class)) {
               return  ((ConstraintViolationException) getCause()).getConstraintViolations();
            } else {
                return Collections.emptySet();
            }
        }
    }
