package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.KeyDeserializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.StixType

fun createStixTypeSerializationModule(): SimpleModule{
    return SimpleModule()
        .addSerializer(StixType::class.java, StixTypeSerializer())
        .addDeserializer(StixType::class.java, StixTypeDeserializer())
        .addKeyDeserializer(StixType::class.java, StixTypeKeyDeserializer())
}

class StixTypeSerializer() : StdSerializer<StixType>(StixType::class.java) {

    override fun serialize(value: StixType?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeString(value!!.toString())
    }
}


class StixTypeKeyDeserializer() : KeyDeserializer(){
    //@TODO *** why this was needed.  Was getting a key map error from jackson when not inlcuded.
    // It started to occur for a unknown reason
    //This should not be required
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): Any {
        return key!!
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