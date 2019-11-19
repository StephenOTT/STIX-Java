package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*
import com.stephenott.stix.type.KillChainPhases

interface AttackPatternSdo : StixDomainObject {
    val name: String
    val description: String?
    val killChainPhases: KillChainPhases? //@TODO

    companion object: CompanionStixType,
        BusinessRulesValidator<AttackPatternSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("attack-pattern")

        override fun objectValidationRules(obj: AttackPatternSdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("delivers"),
                MalwareSdo::class
            ),

            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("targets"),
                IdentitySdo::class
            ),
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("targets"),
                LocationSdo::class
            ),
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("targets"),
                VulnerabilitySdo::class
            ),

            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("uses"),
                MalwareSdo::class
            ),
            AllowedRelationship(
                AttackPatternSdo::class,
                RelationshipType("uses"),
                ToolSdo::class
            )
        )

    }

}

data class AttackPattern(
        override val name: String,
        override val description: String? = null,
        override val killChainPhases: KillChainPhases? = null,
        override val type: StixType = AttackPatternSdo.stixType,
        override val id: StixIdentifier = StixIdentifier(type),
        override val createdByRef: String? = null,
        override val created: StixTimestamp = StixTimestamp(),
        override val externalReferences: ExternalReferences? = null,
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(),
        override val labels: StixLabels? = null,
        override val modified: StixTimestamp = StixTimestamp(created),
        override val revoked: StixBoolean = StixBoolean(),
        override val confidence: StixConfidence? = null,
        override val lang: StixLang? = null,
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : AttackPatternSdo {

    init {
        if (this.stixValidateOnConstruction) {
            AttackPatternSdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
       return AttackPatternSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }
}