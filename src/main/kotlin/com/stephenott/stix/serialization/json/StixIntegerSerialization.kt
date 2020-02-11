package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixInteger


fun createStixIntegerSerializationModule(): SimpleModule {
    return SimpleModule()
        .addSerializer(StixInteger::class.java, StixIntegerSerializer())
        .addDeserializer(StixInteger::class.java, StixIntegerDeserializer())
}

class StixIntegerSerializer() : StdSerializer<StixInteger>(StixInteger::class.java) {
    override fun serialize(value: StixInteger?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeNumber(value!!.value)
    }
}

class StixIntegerDeserializer() : StdDeserializer<StixInteger>(StixInteger::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixInteger {
        if (p!!.numberType == JsonParser.NumberType.INT) {
            return StixInteger(p.intValue)
        } else {
            throw IllegalArgumentException("value is not expected integer format.")
        }
    }
}