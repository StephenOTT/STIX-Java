package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import com.stephenott.stix.type.vocab.LanguageCodes
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface SoftwareSco : StixCyberObservableObject {

    val name: String
    val cpe: String?
    val languages: LanguageCodes?
    val vendor: String?
    val version: String?

    companion object :
        CompanionStixType,
        BusinessRulesValidator<SoftwareSco>,
        CompanionIdContributingProperties<SoftwareSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("software")

        override val idContributingProperties: List<KProperty1<SoftwareSco, Any?>> = listOf(
            SoftwareSco::name,
            SoftwareSco::cpe,
            SoftwareSco::vendor,
            SoftwareSco::version
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: SoftwareSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

        }

    }
}

data class Software(
    override val name: String,
    override val cpe: String? = null,
    override val languages: LanguageCodes? = null,
    override val vendor: String? = null,
    override val version: String? = null,
    override val type: StixType = StixType(SoftwareSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : SoftwareSco {

    init {
        if (this.stixValidateOnConstruction) {
            SoftwareSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}