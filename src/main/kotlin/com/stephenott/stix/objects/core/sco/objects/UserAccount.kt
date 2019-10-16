package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionIdContributingProperties
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KProperty1

interface UserAccountSco : StixCyberObservableObject {

    companion object:
        CompanionStixType,
        BusinessRulesValidator<UserAccountSco>,
        CompanionIdContributingProperties<UserAccountSco>,
        CompanionAllowedRelationships{

        override val stixType = StixType("user-account")

        override val idContributingProperties: List<KProperty1<UserAccountSco, Any?>> = listOf(

        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: UserAccountSco) {

        }

    }
}

data class UserAccount(
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