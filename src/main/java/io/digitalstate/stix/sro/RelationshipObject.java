package io.digitalstate.stix.sro;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.digitalstate.stix.common.StixCommonProperties;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.common.StixLabels;
import io.digitalstate.stix.common.StixModified;
import io.digitalstate.stix.common.StixRevoked;

public interface RelationshipObject extends
        StixCommonProperties,
        StixCustomProperties,
        StixLabels,
        StixModified,
        StixRevoked {

}
