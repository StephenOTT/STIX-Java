package com.stephenott.stix.type

class Latitude(val value: Float){
    init {
        require(value in -90.0..90.0)
    }
}

class Longitude(val value: Float){
    init {
        require(value in -180.0..180.0)
    }
}

class LatLongPrecision(val value: Float){
}