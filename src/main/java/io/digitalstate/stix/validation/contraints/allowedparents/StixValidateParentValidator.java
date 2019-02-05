package io.digitalstate.stix.validation.contraints.allowedparents;

import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.coo.extension.CyberObservableExtension;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;

public class StixValidateParentValidator implements ConstraintValidator<ValidateExtensions, CyberObservableObject> {

    //@TODO ADD LOGGING!!!
    @Override
    public boolean isValid(CyberObservableObject cyberObservableObject, ConstraintValidatorContext cxt) {
        if (cyberObservableObject.getExtensions().size() >= 1) {
            for (CyberObservableExtension ext : cyberObservableObject.getExtensions()) {
                Optional<AllowedParents> annotation = Optional.ofNullable(ext.getClass().getDeclaredAnnotation(AllowedParents.class));

                if (annotation.isPresent()) {
//                    System.out.println("FOUND");
                    Class<? extends CyberObservableObject>[] values = annotation.get().value();

                    if (values.length >= 1) {
                        boolean hasAllowedParent = Arrays.stream(values).anyMatch(i->
                                i.isAssignableFrom(cyberObservableObject.getClass()));

                        if (hasAllowedParent){
//                            System.out.println("Class is assignable from Allowed-Parents interface list");
                            return true;
                        } else {
                            String violationMessage = "Class attempting to use extension is not part of allowedParents interface list found on the Extension.  Calling Cyber Observable Class: " +
                                    ext.getClass().getCanonicalName() + " and Extension only supports interfaces: " + Arrays.toString(values);
                            cxt.buildConstraintViolationWithTemplate(violationMessage).addConstraintViolation();
                            return false;
                        }

                    } else {
//                        System.out.println("No classes defined");
                        return true;
                    }
                } else {
//                    System.out.println("NOT FOUND");
                    return true;
                }
            }
        } else {
//            System.out.println("No Extensions");
            return true;
        }
//        System.out.println("nothing to do");
        return true;
    }
}
