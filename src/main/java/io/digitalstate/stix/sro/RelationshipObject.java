package io.digitalstate.stix.sro;

import io.digitalstate.stix.common.*;

import java.io.Serializable;

public interface RelationshipObject extends Serializable,
        StixCommonProperties,
        StixCustomProperties,
        StixLabels,
        StixModified,
        StixRevoked {

}
