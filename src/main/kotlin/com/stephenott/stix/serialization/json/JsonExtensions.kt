package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.stephenott.stix.StixBundle
import com.stephenott.stix.StixContent
import com.stephenott.stix.StixRegistries
import com.stephenott.stix.serialization.StixContentMapper
import com.fasterxml.jackson.module.kotlin.*
import com.stephenott.stix.Stix

class StixJsonContentMapper(
        override val stixRegistries: StixRegistries = StixRegistries(),
        override val mapper: ObjectMapper = createStixJsonObjectMapper(stixRegistries)
) : StixContentMapper {

    /**
     * Parse a json string into any kind of Stix Content (SDO, SCO, SRO, Relationships, etc)
     */
    inline fun <reified T : StixContent> parseJson(json: String): T {
        try {
            return mapper.readValue(json)
        } catch (e: Exception) {
            throw IllegalArgumentException("Unable to parse json.", e)
        }
    }

    fun toJson(value: StixContent): String {
        return this.mapper.writeValueAsString(value)
    }

    fun toJson(value: StixBundle): String {
        return this.mapper.writeValueAsString(value)
    }

    companion object {

        fun fromStixInstance(instance: Stix): StixJsonContentMapper {
            return StixJsonContentMapper(instance.registries)
        }

        fun createStixJsonObjectMapper(stixRegistries: StixRegistries): ObjectMapper {
            return ObjectMapper()
                    .registerModule(KotlinModule()) //@TODO see jackson kotlin module issue #87.  Waiting for fix.  Currently if a subtype does not exist then it fails to provide a meaningful error.
                    .registerModule(JavaTimeModule())
                    .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                    .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true)
                    .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true)
                    .registerModule(createStixTimestampSerializationModule())
                    .registerModule(createStixIdentifierSerializationModule())
                    .registerModule(createStixTypeSerializationModule())
                    .registerModule(createStixSpecVersionSerializationModule())
                    .registerModule(createStixBooleanSerializationModule())
                    .registerModule(createStixContentSerializationModule(stixRegistries.objectRegistry))
                    .registerModule(createRelationshipTypeSerializationModule())
                    .registerModule(createStixIntegerSerializationModule())
                    .registerModule(createStixConfidenceSerializationModule())
                    .registerModule(createStixOpenVocabSerializationModule())
                    .registerModule(createStixMarkingObjectSerializationModule(stixRegistries.markingObjectRegistry))
        }
    }
}

/**
 * Creates a StixJsonContentMapper based on the Stix instance
 */
fun Stix.toJsonMapper(): StixJsonContentMapper {
    return StixJsonContentMapper(this.registries)
}

/**
 * Convert StixContent to a json string.
 */
fun StixContent.toJson(mapper: StixJsonContentMapper): String {
    return mapper.mapper.writeValueAsString(this)
}

/**
 * Convert a StixBundle to a json string.
 */
fun StixBundle.toJson(mapper: StixJsonContentMapper): String {
    return mapper.mapper.writeValueAsString(this)
}