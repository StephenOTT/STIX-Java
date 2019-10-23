package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.RelationshipType


fun createRelationshipTypeSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(RelationshipType::class.java, RelationshipTypeSerializer())
        .addDeserializer(RelationshipType::class.java, RelationshipTypeDeserializer())
}

class RelationshipTypeSerializer() : StdSerializer<RelationshipType>(RelationshipType::class.java) {
    override fun serialize(value: RelationshipType?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeString(value.toString())
    }
}

class RelationshipTypeDeserializer() : StdDeserializer<RelationshipType>(RelationshipType::class.java) {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): RelationshipType {
        return RelationshipType(p!!.text)
    }
}