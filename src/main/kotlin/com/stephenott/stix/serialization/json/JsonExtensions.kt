package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.stephenott.stix.StixBundle
import com.stephenott.stix.StixContent
import kotlin.reflect.full.cast

fun createStixMapper(): ObjectMapper {
    return ObjectMapper()
        .registerModule(KotlinModule()) //@TODO see jackson kotlin module issue #87.  Waiting for fix.  Currently if a subtype does not exist then it fails to provide a meaningful error.
        .registerModule(JavaTimeModule())
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
        .registerModule(createStixOpenVocabSerializationModule())
        .registerModule(createStixMarkingObjectSerializationModule())
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
//        val content: T

        try {
            return jsonMapper.readValue(json, T::class.java)
//            return content
        } catch (e: Exception){
            throw IllegalArgumentException("Unable to parse json.", e)
        }

//        try {
//            return T::class.cast(content)
//        } catch (e: Exception){
//            throw IllegalArgumentException("Unable to parse json.", e)
//        }
    }

}

fun StixContent.toJson(mapper: StixContentMapper): String{
    return mapper.jsonMapper.writeValueAsString(this)
}

fun StixBundle.toJson(mapper: StixContentMapper): String{
    return mapper.jsonMapper.writeValueAsString(this)
}