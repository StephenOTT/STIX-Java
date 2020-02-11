package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.StixBoolean
import com.stephenott.stix.type.vocab.WindowsIntegrityLevelEnum
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

interface WindowsProcessExtensionExt : ScoExtension {

    val aslrEnabled: StixBoolean?
    val depEnabled: StixBoolean?
    val priority: WindowsProcessExtensionPriority?
    val ownerSid: String?
    val windowTitle: String?
    val startupInfo: LinkedHashMap<String, String>? //@TODO refactor with https://github.com/oasis-tcs/cti-stix2/issues/185#issuecomment-543309219
    val integrityLevel: WindowsIntegrityLevelEnum?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<WindowsProcessExtensionExt> {

        override val extensionType: String = "windows-process-ext"

        override fun objectValidationRules(obj: WindowsProcessExtensionExt) {
            require(obj::class.memberProperties
                .filter {it.visibility == KVisibility.PUBLIC}
                .any { it.getter.call() != null },
                lazyMessage = {"At least one property must be provided/not null."})
        }
    }
}

data class WindowsProcessExtensionPriority(
    val priority: String,
    val enforceSuffixRule: Boolean = WindowsProcessExtensionPriority.enforceSuffixRuleDefault
): CharSequence by priority {

    companion object{
        var enforceSuffixRuleDefault: Boolean = true
    }

    init {
        if (enforceSuffixRule){
            require(priority.endsWith("_CLASS"))
        }
    }
}

data class WindowsProcessExtension(
    override val aslrEnabled: StixBoolean? = null,
    override val depEnabled: StixBoolean? = null,
    override val priority: WindowsProcessExtensionPriority? = null,
    override val ownerSid: String? = null,
    override val windowTitle: String? = null,
    override val startupInfo: LinkedHashMap<String, String>? = null,
    override val integrityLevel: WindowsIntegrityLevelEnum? = null
) : WindowsProcessExtensionExt {

    init {
        WindowsProcessExtensionExt.objectValidationRules(this)
    }
}