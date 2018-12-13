package io.digitalstate.stix.validation;

import org.immutables.value.Value;

public interface GenericValidation extends SdoDefaultValidator {

    @Value.Check
    default public void validateEntity(){
        this.validate();
    }

}
