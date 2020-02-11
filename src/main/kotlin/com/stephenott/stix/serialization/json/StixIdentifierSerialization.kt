package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixIdentifier


fun createStixIdentifierSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixIdentifier::class.java, StixIdentifierSerializer())
        .addDeserializer(StixIdentifier::class.java, StixIdentifierDeserializer())
}

class StixIdentifierSerializer() : StdSerializer<StixIdentifier>(StixIdentifier::class.java) {
    override fun serialize(value: StixIdentifier?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeString(value!!.toString())
    }
}

class StixIdentifierDeserializer() : StdDeserializer<StixIdentifier>(StixIdentifier::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixIdentifier {
        try {
            return StixIdentifier.parse(p!!.text)
        } catch (e: Exception) {
            throw IllegalArgumentException("Unable to parse identifier", e)
        }

    }
}