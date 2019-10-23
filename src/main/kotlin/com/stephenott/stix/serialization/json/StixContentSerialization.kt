package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.stephenott.stix.StixContent
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.type.StixType
import kotlin.reflect.KClass


fun createStixContentSerializationModule(): SimpleModule {
    return SimpleModule()
        .addDeserializer(StixContent::class.java, StixContentDeserializer())
}

class StixContentDeserializer() : StdDeserializer<StixContent>(StixContent::class.java) {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixContent {

        val node: JsonNode = p!!.codec.readTree(p)
        val typeProp = node.get("type")
        if (typeProp != null) {
            val stixType = StixType.parse(typeProp.asText())

            val objectClass: KClass<out StixObject> = StixObjectRegistry.registry.getOrElse(stixType, defaultValue = {
                throw IllegalArgumentException("Unable to parse the object. Ensure object type is supported.")
            })

            return p.codec.treeToValue(node, objectClass.java)

        } else {
            throw IllegalArgumentException("type property was null or not provided.")
        }
    }
}