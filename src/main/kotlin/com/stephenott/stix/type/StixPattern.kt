package com.stephenott.stix.type

class StixPattern(private val pattern: String):
    CharSequence by pattern {

    init {
        pattern.isNotEmpty()
    }
}