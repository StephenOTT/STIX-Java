package io.digitalstate.stix.sdo;

import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.immutables.value.Value;

/**
 * Base interface used by Immutable STIX Domain Objects
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface DomainObject extends StixCommonProperties,
        StixLabels, StixModified, StixRevoked,
        DomainObjectCommonRelationships,
        SdoDefaultValidator {


}
