package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.fasterxml.jackson.databind.module.SimpleModule
import com.stephenott.stix.Stix
import com.stephenott.stix.StixContent
import com.stephenott.stix.StixRegistries
import com.stephenott.stix.common.StixObjectRegistry


fun createStixContentSerializationModule(objectRegistry: StixObjectRegistry): SimpleModule {
    val module: SimpleModule = SimpleModule()

    objectRegistry.registry.forEach { (type, clazz) ->
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
interface StixContentTypeMixin{
    @get:JsonIgnore //@TODO Review for need of a 'set:'
    val stixInstance: Stix

    @get:JsonIgnore
    val stixValidateOnConstruction: Boolean
}