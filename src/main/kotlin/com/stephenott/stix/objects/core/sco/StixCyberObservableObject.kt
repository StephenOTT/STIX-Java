package com.stephenott.stix.objects.core.sco

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject

interface StixCyberObservableObject :
    StixObject,
    StixSpecVersionProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixDefangedProp,
    StixExtensionsProp {}