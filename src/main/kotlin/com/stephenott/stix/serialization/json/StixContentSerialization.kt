package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.fasterxml.jackson.databind.module.SimpleModule
import com.stephenott.stix.StixContent
import com.stephenott.stix.common.StixObjectRegistry


fun createStixContentSerializationModule(): SimpleModule {
    val module: SimpleModule = SimpleModule()

    StixObjectRegistry.registry.forEach { (type, clazz) ->
        module.registerSubtypes(NamedType(clazz.java, type.toString()))
    }

    module.setMixInAnnotation(StixContent::class.java, StixContentTypeMixin::class.java)

    return module
}

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    visible = true,
    property = "type"
)
interface StixContentTypeMixin {
}


//class StixContentSerializer(): StdSerializer<StixContent>(StixContent::class.java) {
//    override fun serializeWithType(
//        value: StixContent?,
//        gen: JsonGenerator?,
//        serializers: SerializerProvider?,
//        typeSer: TypeSerializer?
//    ) {
//
//        super.serializeWithType(value, gen, serializers, typeSer)
//    }
//
//    override fun serialize(value: StixContent?, gen: JsonGenerator?, provider: SerializerProvider?) {
//
//    }
//
//}


//class StixContentDeserializer() : StdDeserializer<StixContent>(StixContent::class.java) {
//
//    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): StixContent {
//
//        val node: JsonNode = p!!.codec.readTree(p)
//        val typeProp = node.get("type")
//        if (typeProp != null) {
//            val stixType = StixType.parse(typeProp.asText())
//
//            val objectClass: KClass<out StixObject> = StixObjectRegistry.registry.getOrElse(stixType, defaultValue = {
//                throw IllegalArgumentException("Unable to parse the object. Ensure object type is supported.")
//            })
//
//            return p.codec.treeToValue(node, objectClass.java)
//
//        } else {
//            throw IllegalArgumentException("type property was null or not provided.")
//        }
//    }
//}