package com.stephenott.stix.objects.meta.datamarking

import com.stephenott.stix.objects.meta.StixMetaObject
import com.stephenott.stix.common.*

interface DataMarking: StixMetaObject,
    StixCreatedByRef,
    StixCreatedProp,
    StixExternalReferencesProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp{
}