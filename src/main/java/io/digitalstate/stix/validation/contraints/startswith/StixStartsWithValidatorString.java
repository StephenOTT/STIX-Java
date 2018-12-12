package io.digitalstate.stix.validation.contraints.startswith;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StixStartsWithValidatorString implements ConstraintValidator<StartsWith, String> {

    private String prefix;

    @Override
    public void initialize(StartsWith startsWithConstraint) {
        prefix = startsWithConstraint.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        if (value.startsWith(prefix)){
            return true;
        } else{
            String violationMessage = "StartsWith violation: string must start with value: "
                    + prefix + ", but provided value: " + value;
            cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
            return false;
        }
    }
}
