package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sco.objects.ArtifactSco
import com.stephenott.stix.type.HttpRequestHeaderDictionary
import com.stephenott.stix.type.StixIdentifier
import com.stephenott.stix.type.StixInteger

interface HttpRequestExtensionExt : ScoExtension {
    val requestMethod: String
    val requestValue: String
    val requestVersion: String?
    val requestHeader: HttpRequestHeaderDictionary?
    val messageBodyLength: StixInteger?
    val messageBodyDataRef: StixIdentifier?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<HttpRequestExtensionExt> {

        override val extensionType: String = "http-request-ext"

        override fun objectValidationRules(obj: HttpRequestExtensionExt) {
            require(obj.messageBodyDataRef?.type == ArtifactSco.stixType)
        }
    }
}

data class HttpRequestExtension(
    override val requestMethod: String,
    override val requestValue: String,
    override val requestVersion: String? = null,
    override val requestHeader: HttpRequestHeaderDictionary? = null,
    override val messageBodyLength: StixInteger? = null,
    override val messageBodyDataRef: StixIdentifier? = null
) : HttpRequestExtensionExt {

    init {
        HttpRequestExtensionExt.objectValidationRules(this)
    }
}