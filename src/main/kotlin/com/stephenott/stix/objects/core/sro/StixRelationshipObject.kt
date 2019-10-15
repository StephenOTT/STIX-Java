package com.stephenott.stix.objects.core.sro

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject

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