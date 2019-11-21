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

interface IPv6AddressSco : StixCyberObservableObject {

    val value: String
//    val resolvesToRefs: StixIdentifiers //@TODO DEPRECATED add elevator
//    val belongsToRefs: StixIdentifiers  //@TODO DEPRECATED add elevator

    companion object :
        CompanionStixType,
        BusinessRulesValidator<IPv6AddressSco>,
        CompanionIdContributingProperties<IPv6AddressSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("ipv6-addr")

        override val idContributingProperties: List<KProperty1<IPv6AddressSco, Any?>> = listOf(
            IPv6AddressSco::value
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                IPv6AddressSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class  //@TODO  mac-addr
            ),
            AllowedRelationship(
                IPv6AddressSco::class,
                RelationshipType("belongs-to"),
                AutonomousSystemSco::class
            )
        )

        override fun objectValidationRules(obj: IPv6AddressSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

    }
}

data class IPv6Address(
    override val value: String,
    override val type: StixType = StixType(IPv6AddressSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : IPv6AddressSco {

    init {
        if (this.stixValidateOnConstruction) {
            IPv6AddressSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}