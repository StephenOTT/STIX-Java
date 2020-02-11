package com.stephenott.stix.objects.meta.datamarking.objects

import com.stephenott.stix.objects.meta.datamarking.MarkingObject
import com.stephenott.stix.objects.meta.datamarking.MarkingObjectCompanionDefType
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv

interface StatementMo : MarkingObject {
    companion object: MarkingObjectCompanionDefType {
        override val definitionType: MarkingDefinitionTypeOv = MarkingDefinitionTypeOv("statement")
    }

    val statement: String

}

data class Statement(
    override val statement: String
): StatementMo