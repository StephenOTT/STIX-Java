package com.stephenott.stix.objects.meta.datamarking.objects

import com.stephenott.stix.objects.meta.datamarking.MarkingObject

interface StatementMo : MarkingObject {
    val statement: String

}

data class Statement(
    override val statement: String

): StatementMo