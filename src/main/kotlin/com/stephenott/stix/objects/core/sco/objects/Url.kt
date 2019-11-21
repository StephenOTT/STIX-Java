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

interface UrlSco : StixCyberObservableObject {

    val value: String

    companion object :
        CompanionStixType,
        BusinessRulesValidator<UrlSco>,
        CompanionIdContributingProperties<UrlSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("url")

        override val idContributingProperties: List<KProperty1<UrlSco, Any?>> = listOf(
            UrlSco::value
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: UrlSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

    }
}

data class Url(
    override val value: String,
    override val type: StixType = StixType(UrlSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : UrlSco {

    init {
        if (this.stixValidateOnConstruction) {
            UrlSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}