package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.ContextualDeserializer
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.stephenott.stix.type.vocab.OpenVocab


fun createStixOpenVocabSerializationModule(): SimpleModule {
    return SimpleModule()
        .addSerializer(OpenVocab::class.java, StixOpenVocabSerializer())
        .addDeserializer(OpenVocab::class.java, StixOpenVocabDeserializer())
}

class StixOpenVocabSerializer() : StdSerializer<OpenVocab>(OpenVocab::class.java) {
    override fun serialize(value: OpenVocab?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen!!.writeString(value!!.getValue())
    }
}

class StixOpenVocabDeserializer() : StdDeserializer<OpenVocab>(OpenVocab::class.java) {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): OpenVocab {
        println(ctxt!!.contextualType)

        return p!!.readValueAs(ctxt.contextualType.rawClass) as OpenVocab
    }
}