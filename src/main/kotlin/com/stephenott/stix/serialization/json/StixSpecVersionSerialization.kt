package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixSpecVersion


fun createStixSpecVersionSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixSpecVersion::class.java, StixSpecVersionSerializer())
        .addDeserializer(StixSpecVersion::class.java, StixSpecVersionDeserializer())
}

class StixSpecVersionSerializer() : StdSerializer<StixSpecVersion>(StixSpecVersion::class.java) {
    override fun isEmpty(provider: SerializerProvider?, value: StixSpecVersion?): Boolean {
        return value == null || !value.isDefinedValue
    }

    override fun serialize(value: StixSpecVersion?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeString(value.toString())
    }
}

class StixSpecVersionDeserializer() : StdDeserializer<StixSpecVersion>(StixSpecVersion::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixSpecVersion {
        try {
            return StixSpecVersion.parse(p!!.text)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse spec_version.", e)
        }
    }
}