package io.digitalstate.stix.sro;

import io.digitalstate.stix.sdo.*;
import io.digitalstate.stix.sdo.StixLabels;
import io.digitalstate.stix.sdo.StixModified;
import io.digitalstate.stix.sdo.StixRevoked;
import io.digitalstate.stix.validation.SdoDefaultValidator;

public interface RelationshipObject extends StixCommonProperties,
        StixLabels,
        StixModified,
        StixRevoked,
        DomainObjectCommonRelationships,
        SdoDefaultValidator {

}
