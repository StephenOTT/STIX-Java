package com.stephenott.stix.objects.meta.lco

import com.stephenott.stix.objects.meta.StixMetaObject
import com.stephenott.stix.common.*

/**
 * Built to support future custom language handling classes
 */
interface LanguageContentObject: StixMetaObject,
    StixCreatedByRef,
    StixCreatedProp,
    StixExternalReferencesProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp,
    StixModifiedProp,
    StixLabelsProp,
    StixRevokedProp,
    StixConfidenceProp {
}