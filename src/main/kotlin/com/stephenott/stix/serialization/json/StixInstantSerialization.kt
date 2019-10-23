package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixInstant


fun createStixInstantSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixInstant::class.java, StixInstantSerializer())
        .addDeserializer(StixInstant::class.java, StixInstantDeserializer())
}

class StixInstantSerializer() : StdSerializer<StixInstant>(StixInstant::class.java) {
    override fun serialize(value: StixInstant, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeString(value.toString())
    }
}

class StixInstantDeserializer() : StdDeserializer<StixInstant>(StixInstant::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): StixInstant {
        try {
            return StixInstant.parse(p.text)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse timestamp.", e)
        }

    }
}