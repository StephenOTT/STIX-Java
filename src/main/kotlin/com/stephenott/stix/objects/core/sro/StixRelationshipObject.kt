package com.stephenott.stix.objects.core.sro

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.StixCoreObject

interface StixRelationshipObject:
    StixCoreObject,
    StixCreatedByRef,
    StixCreatedProp,
    StixExternalReferencesProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp,
    StixModifiedProp,
    StixLabelsProp,
    StixRevokedProp {

}