package com.stephenott.stix.`object`.sdo

import com.stephenott.stix.common.*
import com.stephenott.stix.`object`.StixObject

interface StixDomainObject :
    StixObject,
    StixCreatedByRef,
    StixCreatedProp,
    StixExternalReferencesProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp,
    StixModified,
    StixLabels,
    StixRevoked {}