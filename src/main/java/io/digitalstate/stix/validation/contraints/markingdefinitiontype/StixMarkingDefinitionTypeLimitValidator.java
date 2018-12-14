package io.digitalstate.stix.validation.contraints.markingdefinitiontype;

import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.datamarkings.StixMarkingObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class StixMarkingDefinitionTypeLimitValidator implements ConstraintValidator<MarkingDefinitionTypeLimit, MarkingDefinitionDm> {

    private Class<? extends StixMarkingObject> markingObject;
    private String markingDefinitionType;

    @Override
    public void initialize(MarkingDefinitionTypeLimit markingDefinitionTypeLimitConstraint) {
        markingObject = markingDefinitionTypeLimitConstraint.markingObject();
        markingDefinitionType = markingDefinitionTypeLimitConstraint.markingDefinitionType();
    }

    @Override
    public boolean isValid(MarkingDefinitionDm markingDefinitionDm,
                           ConstraintValidatorContext cxt) {

        // Throws error is the definition property is null.
        // This is semi duplication of the notNull annotation on the attribute
        if (markingDefinitionDm.getDefinition() == null){
            String violationMessage = "Definition attribute of a Marking Definition cannot be null";
            cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
            return false;

            // Default Value helper to populate the definition_type attribute based on the definition  attribute's class
        } else if (markingDefinitionDm.getDefinitionType() == null) {
            if (markingObject.isAssignableFrom(markingDefinitionDm.getDefinition().getClass())) {
                try {
                    Field typeField = markingDefinitionDm.getClass().getDeclaredField("definitionType");
                    typeField.setAccessible(true);
                    typeField.set(markingDefinitionDm, markingDefinitionType);

                } catch (NoSuchFieldException e) {
                    String violationMessage = "Cant find Field: 'definitionType' for: " + markingObject;
                    cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                    e.printStackTrace();
                    return false;

                } catch (IllegalAccessException e) {
                    String violationMessage = "Illegal Access Exception for: 'definitionType' for: " + markingObject;
                    cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                    e.printStackTrace();
                    return false;
                }
            }
        }

        // Checks if the definition and the definition type match as per the spec and class requirements
        // Requirements are stated in the class level(type) of the Marking Definition.
        if (markingObject.isAssignableFrom(markingDefinitionDm.getDefinition().getClass())){
            if (markingDefinitionDm.getDefinitionType().equals(markingDefinitionType)){
                return true;
            } else {
                String violationMessage = "Marking Definition Type is incorrect for Marking Object";
                cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                return false;
            }
        } else {
            return true;
        }
    }
}
