package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixTimestamp


fun createStixTimestampSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixTimestamp::class.java, StixTimestampSerializer())
        .addDeserializer(StixTimestamp::class.java, StixTimestampDeserializer())
}

class StixTimestampSerializer() : StdSerializer<StixTimestamp>(StixTimestamp::class.java) {
    override fun serialize(value: StixTimestamp, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toString())
    }
}

class StixTimestampDeserializer() : StdDeserializer<StixTimestamp>(StixTimestamp::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): StixTimestamp {
        try {
            return StixTimestamp.parse(p.text)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse timestamp.", e)
        }

    }
}