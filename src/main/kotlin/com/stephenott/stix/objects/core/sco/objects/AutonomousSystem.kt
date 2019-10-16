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

interface AutonomousSystemSco : StixCyberObservableObject {

    val number: StixInteger
    val name: String?
    val rir: String?

    companion object:
        CompanionStixType,
        BusinessRulesValidator<AutonomousSystemSco>,
        CompanionIdContributingProperties<AutonomousSystemSco>,
        CompanionAllowedRelationships{

        override val stixType = StixType("autonomous-system")

        override val idContributingProperties: List<KProperty1<AutonomousSystemSco, Any?>> = listOf(
            AutonomousSystemSco::number
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: AutonomousSystemSco) {

        }

    }
}

data class AutonomousSystem(
    override val number: StixInteger,
    override val name: String? = null,
    override val rir: String? = null,
    override val type: StixType = StixType(AutonomousSystemSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean()
) : AutonomousSystemSco {

    init {
        AutonomousSystemSco.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}