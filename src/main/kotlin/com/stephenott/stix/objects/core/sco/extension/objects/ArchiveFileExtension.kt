package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sco.objects.DirectorySco
import com.stephenott.stix.objects.core.sco.objects.FileSco
import com.stephenott.stix.type.StixIdentifiers

interface ArchiveFileExtensionExt : ScoExtension {
    val containsRefs: StixIdentifiers
    val comment: String?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<ArchiveFileExtensionExt> {

        override val extensionType: String = "archive-ext"

        override fun objectValidationRules(obj: ArchiveFileExtensionExt) {
            require(obj.containsRefs.all { it.type in listOf(FileSco.stixType, DirectorySco.stixType) },
                lazyMessage = { "contains_refs can only contain references to File and Directory SCOs." })
        }
    }
}

data class ArchiveFileExtension(
    override val containsRefs: StixIdentifiers,
    override val comment: String? = null
) : ArchiveFileExtensionExt {

    init {
        ArchiveFileExtensionExt.objectValidationRules(this)
    }
}