package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.StixBoolean
import com.stephenott.stix.type.StixInteger
import com.stephenott.stix.type.vocab.NetworkSocketAddressFamilyEnum
import com.stephenott.stix.type.vocab.NetworkSocketTypeEnum

interface NetworkSocketExtensionExt: ScoExtension{

    val addressFamily: NetworkSocketAddressFamilyEnum
    val isBlocking: StixBoolean?
    val isListening: StixBoolean?
    val options: LinkedHashMap<String, String>? //@TODO *** Refactor: https://github.com/oasis-tcs/cti-stix2/issues/185
    val socketType: NetworkSocketTypeEnum?
    val socketDescriptor: StixInteger?
    val socketHandle: StixInteger?

    companion object:
        CompanionExtensionType,
        BusinessRulesExtensionValidator<NetworkSocketExtensionExt> {

        override val extensionType: String = "socket-ext"

        override fun objectValidationRules(obj: NetworkSocketExtensionExt) {

        }
    }
}

data class NetworkSocketExtension(
    override val addressFamily: NetworkSocketAddressFamilyEnum,
    override val isBlocking: StixBoolean? = null,
    override val isListening: StixBoolean? = null,
    override val options: LinkedHashMap<String, String>? = null,
    override val socketType: NetworkSocketTypeEnum? = null,
    override val socketDescriptor: StixInteger? = null,
    override val socketHandle: StixInteger? = null

): NetworkSocketExtensionExt {

    init {
        NetworkSocketExtensionExt.objectValidationRules(this)
    }
}