package com.stephenott.stix.sdo

import com.stephenott.stix.*

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