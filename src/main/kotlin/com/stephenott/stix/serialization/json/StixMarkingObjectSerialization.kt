package com.stephenott.stix.serialization.json

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.jsontype.NamedType
import com.fasterxml.jackson.databind.module.SimpleModule
import com.stephenott.stix.common.StixMarkingObjectRegistry
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.objects.meta.datamarking.MarkingDefinition
import com.stephenott.stix.objects.meta.datamarking.MarkingDefinitionDm
import com.stephenott.stix.objects.meta.datamarking.MarkingObject
import com.stephenott.stix.objects.meta.datamarking.objects.Statement
import com.stephenott.stix.objects.meta.datamarking.objects.Tlp

fun createStixMarkingObjectSerializationModule(markingObjectRegistry: StixMarkingObjectRegistry): SimpleModule {

    val module = SimpleModule()

    // Registers each markingObject that is located in the MarkingObjectRegistry
    //@TODO review implications for race event where these will not be loaded when create the StixMapper class.  Likely will want to pass the data into the function.
    markingObjectRegistry.registry.forEach { (typeOv, clazz) ->
         module.registerSubtypes(NamedType(clazz.java, typeOv.getValue()))
     }

    module.setMixInAnnotation(MarkingDefinition::class.java, MarkingObjectDefinitionTypeMixin::class.java)

    return module
}

abstract class MarkingObjectDefinitionTypeMixin {

    @get:JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = MarkingDefinitionDm.definitionPolymorphicFieldName
    )
    abstract val definition: MarkingObject
}