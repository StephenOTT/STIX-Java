package io.digitalstate.stix.sdo;

import io.digitalstate.stix.common.*;
import io.digitalstate.stix.validation.SdoDefaultValidator;

/**
 * Base interface used by Immutable STIX Domain Objects
 */
public interface DomainObject extends StixCommonProperties, StixCustomProperties,
        StixLabels, StixModified, StixRevoked,
        DomainObjectCommonRelationships,
        SdoDefaultValidator {


}
