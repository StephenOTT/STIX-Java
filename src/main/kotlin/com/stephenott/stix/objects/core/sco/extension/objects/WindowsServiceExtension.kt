package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.StixIdentifiers
import com.stephenott.stix.type.StixStringList
import com.stephenott.stix.type.vocab.WindowsServiceStartTypeEnum
import com.stephenott.stix.type.vocab.WindowsServiceStatusEnum
import com.stephenott.stix.type.vocab.WindowsServiceTypeEnum
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

interface WindowsServiceExtensionExt : ScoExtension {

    val serviceName: String?
    val descriptions: StixStringList?
    val displayName: String?
    val groupName: String?
    val startType: WindowsServiceStartTypeEnum?
    val serviceDllRefs: StixIdentifiers?
    val serviceType: WindowsServiceTypeEnum?
    val serviceStatus: WindowsServiceStatusEnum?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<WindowsServiceExtensionExt> {

        override val extensionType: String = "windows-service-ext"

        override fun objectValidationRules(obj: WindowsServiceExtensionExt) {
            require(this::class.memberProperties
                .filter {it.visibility == KVisibility.PUBLIC}
                .any { it.getter.call() != null },
                lazyMessage = {"windows-service-extension requires at least one property must be provided/not null."})
        }
    }
}

data class WindowsServiceExtension(
    override val serviceName: String? = null,
    override val descriptions: StixStringList? = null,
    override val displayName: String? = null,
    override val groupName: String? = null,
    override val startType: WindowsServiceStartTypeEnum? = null,
    override val serviceDllRefs: StixIdentifiers? = null,
    override val serviceType: WindowsServiceTypeEnum? = null,
    override val serviceStatus: WindowsServiceStatusEnum? = null

) : WindowsServiceExtensionExt {

    init {
        WindowsServiceExtensionExt.objectValidationRules(this)
    }
}