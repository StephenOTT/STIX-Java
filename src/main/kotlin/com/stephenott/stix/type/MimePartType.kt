package com.stephenott.stix.type

import com.stephenott.stix.objects.core.sco.objects.ArtifactSco
import com.stephenott.stix.objects.core.sco.objects.FileSco

class MimePartTypes(private val parts: List<MimePartType>): List<MimePartType> by parts{
    init {
        //@TODO
    }
}

data class MimePartType(
    val body: String?,
    val bodyRawRef: StixIdentifier?,
    val ContentType: String?,
    val contentDisposition: String?
) {

    init {
        if (body != null) {
            require(bodyRawRef == null, lazyMessage = { "Only one of body or body_raw_ref must be included." })
        }
        if (bodyRawRef != null) {
            require(body == null, lazyMessage = { "Only one of body or body_raw_ref must be included." })
            require(bodyRawRef.type == ArtifactSco.stixType ||
                    bodyRawRef.type == FileSco.stixType,
                lazyMessage = { "body_raw_ref must be a SCO of type artifact or file." })
        }
    }

}