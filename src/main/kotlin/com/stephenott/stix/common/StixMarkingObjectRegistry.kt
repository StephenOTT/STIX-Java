package com.stephenott.stix.common

import com.stephenott.stix.objects.meta.datamarking.MarkingObject
import com.stephenott.stix.objects.meta.datamarking.objects.Statement
import com.stephenott.stix.objects.meta.datamarking.objects.Tlp
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv
import kotlin.reflect.KClass

class StixMarkingObjectRegistry() {

    val registry: MutableMap<MarkingDefinitionTypeOv, KClass<out MarkingObject>> = mutableMapOf(
        Pair(MarkingDefinitionTypeOv("statement"), Statement::class),
        Pair(MarkingDefinitionTypeOv("tlp"), Tlp::class)
    )
}