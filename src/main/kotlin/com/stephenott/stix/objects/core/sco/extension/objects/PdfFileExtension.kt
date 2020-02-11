package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.StixBoolean

interface PdfFileExtensionExt: ScoExtension{

    val version: String?
    val isOptimized: StixBoolean?
    val documentInfoDict: LinkedHashMap<String, String>?
    val pdfid0: String?
    val pdfid1: String?

    companion object:
        CompanionExtensionType,
        BusinessRulesExtensionValidator<PdfFileExtensionExt> {

        override val extensionType: String = "pdf-ext"

        override fun objectValidationRules(obj: PdfFileExtensionExt) {
            require(obj.version != null ||
                    obj.isOptimized != null ||
                    obj.documentInfoDict != null ||
                    obj.pdfid0 != null ||
                    obj.pdfid1 != null,
                lazyMessage = {"PDF File Extension must have at least one property provided."})
        }
    }
}

data class PdfFileExtension(
    override val version: String? = null,
    override val isOptimized: StixBoolean? = null,
    override val documentInfoDict: LinkedHashMap<String, String>? = null,
    override val pdfid0: String? = null,
    override val pdfid1: String? = null
): PdfFileExtensionExt {

    init {
        PdfFileExtensionExt.objectValidationRules(this)
    }
}