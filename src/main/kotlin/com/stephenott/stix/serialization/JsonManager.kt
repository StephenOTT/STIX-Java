package com.stephenott.stix.serialization

interface JsonReader<out T> {

    fun readJson(jsonString: String): T

}

interface JsonWriter {

    fun toJson(): String

}