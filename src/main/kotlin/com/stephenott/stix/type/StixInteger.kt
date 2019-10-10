package com.stephenott.stix.type

class StixInteger (val value: Int) {
    init {
        require(value in 1..999999999)
    }
}