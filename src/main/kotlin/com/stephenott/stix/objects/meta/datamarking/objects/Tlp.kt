package com.stephenott.stix.objects.meta.datamarking.objects

import com.stephenott.stix.objects.meta.datamarking.MarkingObject


interface TlpMo : MarkingObject {
    val tlp: String
}

data class Tlp(
    override val tlp: String

): TlpMo