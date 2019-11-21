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

interface MacAddressSco : StixCyberObservableObject {

    val value: String

    companion object :
        CompanionStixType,
        BusinessRulesValidator<MacAddressSco>,
        CompanionIdContributingProperties<MacAddressSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("mac-addr")

        override val idContributingProperties: List<KProperty1<MacAddressSco, Any?>> = listOf(
            MacAddressSco::value
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(

        )

        override fun objectValidationRules(obj: MacAddressSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            //@TODO The MAC address value ​MUST​ be represented as a single colon-delimited, lowercase MAC-48 address, which ​MUST​ include leading zeros for each octet.
        }

    }
}

data class MacAddress(
    override val value: String,
    override val type: StixType = StixType(MacAddressSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : MacAddressSco {

    init {
        if (this.stixValidateOnConstruction) {
            MacAddressSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}