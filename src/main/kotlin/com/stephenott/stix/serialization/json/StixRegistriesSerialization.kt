package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.module.SimpleModule
import com.stephenott.stix.StixContent
import com.stephenott.stix.StixRegistries

/**
 * Provides JsonIgnore annotation on the stixRegistries object in StixContent
 */
fun createStixContentStixRegistriesSerializationModule(): SimpleModule {
    val module: SimpleModule = SimpleModule()

    module.setMixInAnnotation(StixContent::class.java, StixContentStixRegistriesMixin::class.java)

    return module
}


interface StixContentStixRegistriesMixin {

    @get:JsonIgnore
    val stixRegistries: StixRegistries
}