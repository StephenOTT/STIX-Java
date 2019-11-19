package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.*
import com.stephenott.stix.type.vocab.WindowsPebinaryTypeOv
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

interface WindowsPeBinaryFileExtensionExt : ScoExtension {

    val peType: WindowsPebinaryTypeOv
    val imphash: String?
    val machineHex: StixHex?
    val numberOfSections: StixInteger?
    val timeDateStamp: StixTimestamp?
    val pointerToSymbolTableHex: StixHex?
    val numberOfSymbols: StixInteger?
    val sizeOfOptionalHeader: StixInteger?
    val characteristicsHex: StixHex?
    val fileHeaderHashes: HashesDictionary?
    val optionalHeader: WindowsPeOptionalHeaderType?
    val sections: WindowsPeSectionTypes?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<WindowsPeBinaryFileExtensionExt> {

        override val extensionType: String = "windows-pebinary-ext"

        override fun objectValidationRules(obj: WindowsPeBinaryFileExtensionExt) {
            require(obj.timeDateStamp?.subSecondPrecision == 0,
                lazyMessage = {"time_date_stamp cannot have sub-second precision"})
            require(obj.sizeOfOptionalHeader?.value!! >= 0,
                lazyMessage = {"size_of_optional_header must not be negative."})
            require(obj::class.memberProperties
                .filter {
                    it.visibility == KVisibility.PUBLIC && it.name != obj::peType.name
                }.any { it.getter.call() != null },
                lazyMessage = { "At least one property other than pe_type must be provided/be not null." })
        }
    }
}

data class WindowsPeBinaryFileExtension(
        override val peType: WindowsPebinaryTypeOv,
        override val imphash: String? = null,
        override val machineHex: StixHex? = null,
        override val numberOfSections: StixInteger? = null,
        override val timeDateStamp: StixTimestamp? = null,
        override val pointerToSymbolTableHex: StixHex? = null,
        override val numberOfSymbols: StixInteger? = null,
        override val sizeOfOptionalHeader: StixInteger? = null,
        override val characteristicsHex: StixHex? = null,
        override val fileHeaderHashes: HashesDictionary? = null,
        override val optionalHeader: WindowsPeOptionalHeaderType? = null,
        override val sections: WindowsPeSectionTypes? = null

) : WindowsPeBinaryFileExtensionExt {

    init {
        WindowsPeBinaryFileExtensionExt.objectValidationRules(this)
    }
}