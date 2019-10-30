package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sco.extension.objects.UnixAccountExtensionExt
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import com.stephenott.stix.type.vocab.AccountType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface UserAccountSco : StixCyberObservableObject {

    val userId: String?
    val credential: String?
    val accountLogin: String?
    val accountType: AccountType?
    val displayName: String?
    val isServiceAccount: StixBoolean?
    val isPrivilege: StixBoolean?
    val canEscalatePrivs: StixBoolean?
    val isDisabled: StixBoolean?
    val accountCreated: StixInstant?
    val accountExpires: StixInstant?
    val credentialLastChanged: StixInstant?
    val accountFirstLogin: StixInstant?
    val accountLastLogin: StixInstant?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<UserAccountSco>,
        CompanionIdContributingProperties<UserAccountSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val stixType = StixType("user-account")

        override val idContributingProperties: List<KProperty1<UserAccountSco, Any?>> = listOf(
            UserAccountSco::accountType,
            UserAccountSco::userId,
            UserAccountSco::accountLogin
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf(
            UnixAccountExtensionExt::class
        )

        override fun objectValidationRules(obj: UserAccountSco) {
            requireStixType(this.stixType, obj)
        }

    }
}

data class UserAccount(
    override val userId: String? = null,
    override val credential: String? = null,
    override val accountLogin: String? = null,
    override val accountType: AccountType? = null,
    override val displayName: String? = null,
    override val isServiceAccount: StixBoolean? = null,
    override val isPrivilege: StixBoolean? = null,
    override val canEscalatePrivs: StixBoolean? = null,
    override val isDisabled: StixBoolean? = null,
    override val accountCreated: StixInstant? = null,
    override val accountExpires: StixInstant? = null,
    override val credentialLastChanged: StixInstant? = null,
    override val accountFirstLogin: StixInstant? = null,
    override val accountLastLogin: StixInstant? = null,
    override val type: StixType = StixType(UserAccountSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean()
) : UserAccountSco {

    init {
        UserAccountSco.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}