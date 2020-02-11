package com.stephenott.stix.objects.core.sco

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.StixCoreObject

interface StixCyberObservableObject :
    StixCoreObject,
    StixSpecVersionProp,
    StixObjectMarkingsRefsProp,
    StixGranularMarkingsProp,
    StixDefangedProp,
    StixExtensionsProp {}