package io.digitalstate.stix.datamarkings;

import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.immutables.value.Value;

public interface GenericValidation extends SdoDefaultValidator {

    @Value.Check
    default public void validateEntity(){
        this.validate();
    }

}
