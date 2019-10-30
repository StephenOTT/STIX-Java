package com.stephenott.stix.common

import com.stephenott.stix.StixContent
import com.stephenott.stix.type.StixType

fun requireStixType(type: StixType, obj: StixContent){
    require(obj.type == type,
        lazyMessage = {"Object has incorrect type value. Value was: ${obj.type}, but expected type value was $type"})
}