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

interface DomainNameSco : StixCyberObservableObject {

    val value: String //@TODO add validation
    //@TODO deprecated resolves_to_refs, add elevator code to create relationships from the 2.1 objects

    companion object:
        CompanionStixType,
        BusinessRulesValidator<DomainNameSco>,
        CompanionIdContributingProperties<DomainNameSco>,
        CompanionAllowedRelationships,
        CompanionAllowedExtensions {

        override val allowedExtensions: List<KClass<out ScoExtension>> = listOf()

        override val stixType = StixType("domain-name")

        override val idContributingProperties: List<KProperty1<DomainNameSco, Any?>> = listOf(
            DomainNameSco::value
        )

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                DomainNameSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class
            ),
            AllowedRelationship(
                DomainNameSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class // @TODO IPV4-addr
            ),
            AllowedRelationship(
                DomainNameSco::class,
                RelationshipType("resolves-to"),
                DomainNameSco::class // @TODO IPV6-addr
            )
        )

        override fun objectValidationRules(obj: DomainNameSco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

        }

    }
}

data class DomainName(
    override val value: String,
    override val type: StixType = StixType(DomainNameSco.stixType),
    override val id: StixIdentifier = StixIdentifier(type),
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(StixVersions.TWO_DOT_ONE, false),
    override val extensions: Extensions? = null,
    override val defanged: StixBoolean = StixBoolean(),
    override val stixInstance: Stix = Stix.defaultStixInstance,
    override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : DomainNameSco {

    init {
        if (this.stixValidateOnConstruction) {
            DomainNameSco.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}