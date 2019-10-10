package com.stephenott.stix.common

interface ValidatorManager<in I, out O> {
    var isValid: Boolean

    fun validate(data: I ): O
}