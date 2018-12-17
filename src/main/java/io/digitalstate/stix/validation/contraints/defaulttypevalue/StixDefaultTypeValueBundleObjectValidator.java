package io.digitalstate.stix.validation.contraints.defaulttypevalue;

import io.digitalstate.stix.bundle.BundleObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * This is used solely for a STIX Bundle.
 */
public class StixDefaultTypeValueBundleObjectValidator implements ConstraintValidator<DefaultTypeValue, BundleObject> {

    private String defaultTypeValue;

    @Override
    public void initialize(DefaultTypeValue relationshipTypeLimitConstraint) {
        defaultTypeValue = relationshipTypeLimitConstraint.value();
    }

    @Override
    public boolean isValid(BundleObject bundleObject,
                           ConstraintValidatorContext cxt) {

        String type = bundleObject.getType();
        if (type == null || type.isEmpty()){
            try {
                Field typeField = bundleObject.getClass().getDeclaredField("type");
                typeField.setAccessible(true);
                typeField.set(bundleObject, defaultTypeValue);
            } catch (NoSuchFieldException e) {
                String violationMessage = "Cant find Field: 'type' for: " + bundleObject.getClass();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;

            } catch (IllegalAccessException e) {
                String violationMessage = "Illegal Access Exception for: 'type' for: " + bundleObject.getClass();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;
            }
        } else {
            if (bundleObject.getType().equals(defaultTypeValue)){
                return true;
            } else{
                String violationMessage = "Field 'type' must have value of " + defaultTypeValue + "for class " + bundleObject.getClass().getCanonicalName();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        }

        String id = bundleObject.getId();
        if (id == null || id.isEmpty()){
            try {
                Field idField = bundleObject.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                String idValue = String.join("--", defaultTypeValue, UUID.randomUUID().toString());
                idField.set(bundleObject, idValue);

            } catch (IllegalAccessException e) {
                String violationMessage = "Illegal Access Exception for: 'id' for: " + bundleObject.getClass();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;

            } catch (NoSuchFieldException e) {
                String violationMessage = "Cant find Field: 'id' for: " + bundleObject.getClass();
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                e.printStackTrace();
                return false;
            }
        } else {
            boolean idStartsWithType = id.startsWith(defaultTypeValue + "--");
            if (!idStartsWithType){
                String violationMessage = "Id does not start with the proper type value(Looking for '" + defaultTypeValue + "--' : provided 'id' value was: " + id;
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}

