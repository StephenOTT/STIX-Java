package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixBoolean
import kotlin.IllegalArgumentException


fun createStixBooleanSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixBoolean::class.java, StixBooleanSerializer())
        .addDeserializer(StixBoolean::class.java, StixBooleanDeserializer())
}

class StixBooleanSerializer() : StdSerializer<StixBoolean>(StixBoolean::class.java) {

    override fun isEmpty(provider: SerializerProvider?, value: StixBoolean?): Boolean {
        return value == null || !value.isDefinedValue
    }

    override fun serialize(value: StixBoolean?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeBoolean(value!!.value)
    }
}

class StixBooleanDeserializer() : StdDeserializer<StixBoolean>(StixBoolean::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixBoolean {
        try {
            return StixBoolean.parse(p!!.text)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse boolean.", e)
        }
    }
}