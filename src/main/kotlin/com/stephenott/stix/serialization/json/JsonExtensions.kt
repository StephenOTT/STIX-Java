package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.stephenott.stix.StixBundle
import com.stephenott.stix.StixContent
import kotlin.reflect.full.cast

fun createStixMapper(): ObjectMapper {
    return jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .registerModule(createStixSdoModule())
        .registerModule(createStixSroModule())
        .registerModule(createStixScoModule())
        .registerModule(createStixMetaObjectModule())
        .registerModule(createStixBundleModule())
        .registerModule(createStixCustomObjectsModule())
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        .registerModule(createStixInstantSerializationModule())
        .registerModule(createStixIdentifierSerializationModule())
        .registerModule(createStixTypeSerializationModule())
        .registerModule(createStixSpecVersionSerializationModule())
        .registerModule(createStixBooleanSerializationModule())
        .registerModule(createStixContentSerializationModule())
        .registerModule(createRelationshipTypeSerializationModule())
        .registerModule(createStixIntegerSerializationModule())
        .registerModule(createStixConfidenceSerializationModule())
}

class StixContentMapper(){
    /**
     * Should generally not be needed. But provided just in case
     */
    val jsonMapper: ObjectMapper = createStixMapper()

    /**
     * Parse a json string into any kind of Stix Content (SDO, SCO, SRO, Relationships, etc)
     */
    inline fun <reified T: StixContent> parseJson(json: String): T {
        val content: StixContent

        try {
            content = jsonMapper.readValue(json, StixContent::class.java)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse json.", e)
        }

        try {
            return T::class.cast(content)
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse json.", e)
        }
    }

}

fun createStixSdoModule(): SimpleModule {
    val module = SimpleModule()

    return module
}

fun createStixScoModule(): SimpleModule {
    val module = SimpleModule()

    return module
}

fun createStixSroModule(): SimpleModule {
    val module = SimpleModule()

    return module
}

fun createStixMetaObjectModule(): SimpleModule {
    val module = SimpleModule()

    return module
}

fun createStixBundleModule(): SimpleModule {
    val module = SimpleModule()

    return module
}

fun createStixCustomObjectsModule(): SimpleModule {
    val module = SimpleModule()

    return module
}

fun StixContent.toJson(mapper: StixContentMapper): String{
    return mapper.jsonMapper.writeValueAsString(this)
}

fun StixBundle.toJson(mapper: StixContentMapper): String{
    return mapper.jsonMapper.writeValueAsString(this)
}