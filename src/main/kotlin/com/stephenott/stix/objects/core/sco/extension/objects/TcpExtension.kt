package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.StixHex

interface TcpExtensionExt : ScoExtension {

    val srcFlagsHex: StixHex?
    val dstFlagsHex: StixHex?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<TcpExtensionExt> {

        override val extensionType: String = "tcp-ext"

        override fun objectValidationRules(obj: TcpExtensionExt) {
            require(obj.srcFlagsHex != null ||
                    obj.dstFlagsHex != null,
                lazyMessage = { "At least one property must be provided for tcp-ext" })
        }
    }
}

data class TcpExtension(
    override val srcFlagsHex: StixHex? = null,
    override val dstFlagsHex: StixHex? = null
) : TcpExtensionExt {

    init {
        TcpExtensionExt.objectValidationRules(this)
    }
}