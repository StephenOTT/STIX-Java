package com.stephenott.stix.objects.meta.datamarking.objects

import com.stephenott.stix.objects.meta.datamarking.MarkingObject
import com.stephenott.stix.objects.meta.datamarking.MarkingObjectCompanionDefType
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv


interface TlpMo : MarkingObject {
    companion object: MarkingObjectCompanionDefType{
        override val definitionType: MarkingDefinitionTypeOv = MarkingDefinitionTypeOv("tlp")
    }

    val tlp: String
}

data class Tlp(
    override val tlp: String

): TlpMo