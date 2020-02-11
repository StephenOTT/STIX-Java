package com.stephenott.stix.type

class ExifTagsDictionary(private val dictionary: LinkedHashMap<String, ExifTag>):
    Map<String, ExifTag> by dictionary {

    init {
        //@TODO

        //@TODO Add a JsonCreator constructor for the Map

    }
}

interface ExifTag{
    val value: Any
}

data class ExifTagStringValue(override val value: String): ExifTag

data class ExifTagIntegerValue(override val value: StixInteger): ExifTag