package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*

interface ObservedDataSdo : StixDomainObject {
    val firstObserved: StixTimestamp
    val lastObserved: StixTimestamp
    val numberObserved: StixInteger
    val objectRefs: StixIdentifiers

    companion object : CompanionStixType,
        BusinessRulesValidator<ObservedDataSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("observed-data")

        override fun objectValidationRules(obj: ObservedDataSdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

            require(obj.lastObserved.instant >= obj.firstObserved.instant,
                lazyMessage = { "last_observed must be greater than or equal to first_observed." })

            require(obj.numberObserved.value in 1..999999999,
                lazyMessage = { "number_observed must be between 1 and 999,999,999." })

            require(obj.objectRefs.any { it.type in stixInstance.registries.objectRegistry.scoRegistry.keys },
                lazyMessage = { "object_refs must contain at least one SCO." })
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class ObservedData(
        override val firstObserved: StixTimestamp,
        override val lastObserved: StixTimestamp,
        override val numberObserved: StixInteger,
        override val objectRefs: StixIdentifiers,
        override val type: StixType = ObservedDataSdo.stixType,
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
) : ObservedDataSdo {

    init {
        if (this.stixValidateOnConstruction) {
            ObservedDataSdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ObservedDataSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}