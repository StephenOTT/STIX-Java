package com.stephenott.stix.objects.core.sco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.StixRegistries
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.extension.ScoExtension
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.type.*
import com.stephenott.stix.type.StixSpecVersion.Companion.StixVersions
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

interface IPv4AddressSco : StixCyberObservableObject {

    val value: String
//    val resolvesToRefs: StixIdentifiers //@TODO DEPRECATED add elevator
//    val belongsToRefs: StixIdentifiers  //@TODO DEPRECATED add elevator

    companion object:
        CompanionStixType,
        BusinessRulesValidator<IPv4AddressSco>,
        CompanionIdContributingProperties<IPv4AddressSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("ipv4-addr")

        override val idContributingProperties: List<KProperty1<IPv4AddressSco, Any?>> = listOf(
            IPv4AddressSco::value
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                IPv4AddressSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class  //@TODO  mac-addr
            ),
            AllowedRelationship(
                IPv4AddressSco::class,
                RelationshipType("belongs-to"),
                AutonomousSystemSco::class
            )
        )

        override fun objectValidationRules(obj: IPv4AddressSco, stixRegistries: StixRegistries) {
            requireStixType(this.stixType, obj)
        }
    }
}

data class IPv4Address(
    override val value: String,
    override val type: StixType = StixType(IPv4AddressSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixRegistries: StixRegistries = Stix.defaultRegistries
) : IPv4AddressSco {

    init {
        IPv4AddressSco.objectValidationRules(this, stixRegistries)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}