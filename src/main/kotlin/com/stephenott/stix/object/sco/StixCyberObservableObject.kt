package com.stephenott.stix.object.sco

import com.stephenott.stix.common.*
import com.stephenott.stix.`object`.StixObject

interface StixCyberObservableObject:
    StixObject,
    StixCreatedProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixSpecVersionProp,
    StixModified
    StixRevoked {

}