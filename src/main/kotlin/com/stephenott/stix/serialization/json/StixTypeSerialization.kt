package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.type.WritableTypeId
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixType


fun createStixTypeSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixType::class.java, StixTypeSerializer())
        .addDeserializer(StixType::class.java, StixTypeDeserializer())
}

class StixTypeSerializer() : StdSerializer<StixType>(StixType::class.java) {

//    override fun serializeWithType(
//        value: StixType?,
//        gen: JsonGenerator?,
//        serializers: SerializerProvider?,
//        typeSer: TypeSerializer?
//    ) {
//        val typeId: WritableTypeId = typeSer!!.typeId(value, JsonToken.VALUE_STRING)
//        gen!!.writeString(value.toString())
//        typeId.wrapperWritten = !gen.canWriteTypeId()
//        typeSer.writeTypeSuffix(gen, typeId)
//    }

    override fun serialize(value: StixType?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeString(value!!.toString())
    }
}

class StixTypeDeserializer() : StdDeserializer<StixType>(StixType::class.java) {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixType {
        try {
            return StixType.parse(p!!.text)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse type property.", e)
        }
    }
}