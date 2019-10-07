package com.stephenott.stix

interface ValidatorManager<in I, out O> {
    var isValid: Boolean

    fun validate(data: I ): O
}