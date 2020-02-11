package com.stephenott.stix.objects.core.sco.extension.objects

import com.stephenott.stix.common.BusinessRulesExtensionValidator
import com.stephenott.stix.common.CompanionExtensionType
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.type.StixInteger
import com.stephenott.stix.type.StixStringList

interface UnixAccountExtensionExt : ScoExtension {

    val gid: StixInteger?
    val groups: StixStringList?
    val homeDir: String?
    val shell: String?

    companion object :
        CompanionExtensionType,
        BusinessRulesExtensionValidator<UnixAccountExtensionExt> {

        override val extensionType: String = "unix-account-ext"

        override fun objectValidationRules(obj: UnixAccountExtensionExt) {
            require(listOf(obj.gid, obj.groups, obj.homeDir, obj.shell).any { it != null },
                lazyMessage = {"unix-account-ext must have at least one property provided/is not null."})
        }
    }
}

data class UnixAccountExtension(
    override val gid: StixInteger? = null,
    override val groups: StixStringList? = null,
    override val homeDir: String? = null,
    override val shell: String? = null
) : UnixAccountExtensionExt {

    init {
        UnixAccountExtensionExt.objectValidationRules(this)
    }
}