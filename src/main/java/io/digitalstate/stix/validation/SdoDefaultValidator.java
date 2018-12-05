package io.digitalstate.stix.validation;

import io.digitalstate.stix.validation.groups.ValidateIdOnly;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public interface SdoDefaultValidator {

    Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    default void validate() throws ConstraintViolationException{
        Set<ConstraintViolation<SdoDefaultValidator>> violations = VALIDATOR.validate(this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    default void validateOnlyId() throws ConstraintViolationException{
        Set<ConstraintViolation<SdoDefaultValidator>> violations = VALIDATOR.validate(this, ValidateIdOnly.class);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}




