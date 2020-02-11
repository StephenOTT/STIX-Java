package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.AlternateDataStreams

interface NtfsFileExtensionExt: ScoExtension{
    val sid: String?
    val alternateDataStreams: AlternateDataStreams?

    companion object:
        CompanionExtensionType,
        BusinessRulesExtensionValidator<NtfsFileExtensionExt> {

        override val extensionType: String = "ntfs-ext"

        override fun objectValidationRules(obj: NtfsFileExtensionExt) {
            require(obj.sid != null || obj.alternateDataStreams != null,
                lazyMessage = {"At least 1 property must be provided in the ntfs-ext extension."})
        }
    }
}

data class NtfsFileExtension(
    override val sid: String? = null,
    override val alternateDataStreams: AlternateDataStreams? = null
): NtfsFileExtensionExt {

    init {
        NtfsFileExtensionExt.objectValidationRules(this)
    }
}