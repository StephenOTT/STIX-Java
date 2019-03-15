package io.digitalstate.stix.validation.contraints.defaulttypevalue;

import io.digitalstate.stix.coo.CyberObservableObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * This is used on any class that implements <strong>CyberObservableObject</strong>.
 */
public class StixDefaultTypeValueCyberObservableValidator implements ConstraintValidator<DefaultTypeValue, CyberObservableObject> {

    private String defaultTypeValue;

    @Override
    public void initialize(DefaultTypeValue relationshipTypeLimitConstraint) {
        defaultTypeValue = relationshipTypeLimitConstraint.value();
    }

    @Override
    public boolean isValid(CyberObservableObject cyberObservableObject,
                           ConstraintValidatorContext cxt) {
        String type = cyberObservableObject.getType();
        if (type == null || type.isEmpty()){
            try {
                Field typeField = cyberObservableObject.getClass().getDeclaredField("type");
                typeField.setAccessible(true);
                typeField.set(cyberObservableObject, defaultTypeValue);
            } catch (NoSuchFieldException e) {
                cxt.disableDefaultConstraintViolation();
                String violationMessage = "Cant find Field: 'type' for: " + cyberObservableObject.getClass();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;

            } catch (IllegalAccessException e) {
                cxt.disableDefaultConstraintViolation();
                String violationMessage = "Illegal Access Exception for: 'type' for: " + cyberObservableObject.getClass();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;
            }
        } else {
            if (cyberObservableObject.getType().equals(defaultTypeValue)){
                return true;
            } else{
                cxt.disableDefaultConstraintViolation();
                String violationMessage = "Field 'type' must have value of " + defaultTypeValue + "for class " + cyberObservableObject.getClass().getCanonicalName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}

