package com.stephenott.stix.objects.core.sdo

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject

interface StixDomainObject :
    StixObject,
    StixCreatedByRef,
    StixCreatedProp,
    StixExternalReferencesProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp,
    StixModifiedProp,
    StixLabelsProp,
    StixRevokedProp,
    StixLangProp,
    StixConfidenceProp {}