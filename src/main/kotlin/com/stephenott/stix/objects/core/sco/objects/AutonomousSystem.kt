package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface AutonomousSystemSco : StixCyberObservableObject {

    val number: StixInteger
    val name: String?
    val rir: String?

    companion object:
        CompanionStixType,
        BusinessRulesValidator<AutonomousSystemSco>,
        CompanionIdContributingProperties<AutonomousSystemSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("autonomous-system")

        override val idContributingProperties: List<KProperty1<AutonomousSystemSco, Any?>> = listOf(
            AutonomousSystemSco::number
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: AutonomousSystemSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
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
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : AutonomousSystemSco {

    init {
        if (this.stixValidateOnConstruction) {
            AutonomousSystemSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}