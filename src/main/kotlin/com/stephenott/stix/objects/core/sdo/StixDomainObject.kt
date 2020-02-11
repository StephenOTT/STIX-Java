package com.stephenott.stix.objects.core.sdo

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.StixCoreObject

interface StixDomainObject :
    StixCoreObject,
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