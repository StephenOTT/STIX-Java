package com.stephenott.stix.`object`.sro

import com.stephenott.stix.common.*
import com.stephenott.stix.`object`.StixObject

interface StixRelationshipObject:
    StixObject,
    StixCreatedByRef,
    StixCreatedProp,
    StixExternalReferencesProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp,
    StixModified,
    StixLabels,
    StixRevoked {

}