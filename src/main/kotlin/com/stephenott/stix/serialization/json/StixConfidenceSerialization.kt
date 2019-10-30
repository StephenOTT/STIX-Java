package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixConfidence
import com.stephenott.stix.type.StixType


fun createStixConfidenceSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixConfidence::class.java, StixConfidenceSerializer())
        .addDeserializer(StixConfidence::class.java, StixConfidenceDeserializer())
}

class StixConfidenceSerializer() : StdSerializer<StixConfidence>(StixConfidence::class.java) {
    override fun isEmpty(provider: SerializerProvider?, value: StixConfidence?): Boolean {
        return value == null || value.stixValue == null
    }

    override fun serialize(value: StixConfidence?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeNumber(value!!.getConfidence())
    }
}

class StixConfidenceDeserializer() : StdDeserializer<StixConfidence>(StixConfidence::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixConfidence {
        if (p!!.numberType == JsonParser.NumberType.INT){
            return StixConfidence(p.intValue)
        } else {
            throw IllegalArgumentException("Invalid confidence value.")
        }
    }
}