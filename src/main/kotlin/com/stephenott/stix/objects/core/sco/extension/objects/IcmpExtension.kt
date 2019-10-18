package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.*

interface IcmpExtensionExt: ScoExtension{

    val icmpTypeHex: StixHex
    val icmpCodeHex: StixHex

    companion object:
        CompanionExtensionType,
        BusinessRulesValidator<IcmpExtensionExt> {

        override val extensionType: String = "icmp-ext"

        override fun objectValidationRules(obj: IcmpExtensionExt) {

        }
    }
}

data class IcmpExtension(
    override val icmpTypeHex: StixHex,
    override val icmpCodeHex: StixHex

): IcmpExtensionExt {

    init {
        IcmpExtensionExt.objectValidationRules(this)
    }
}