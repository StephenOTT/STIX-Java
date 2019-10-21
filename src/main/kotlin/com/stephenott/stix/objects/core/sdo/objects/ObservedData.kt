package com.stephenott.stix.objects.core.sdo.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.type.*

interface ObservedDataSdo : StixDomainObject {
    val firstObserved: StixInstant
    val lastObserved: StixInstant
    val numberObserved: StixInteger
    val objectRefs: StixIdentifiers

    companion object : CompanionStixType,
        BusinessRulesValidator<ObservedDataSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("observed-data")

        override fun objectValidationRules(obj: ObservedDataSdo) {
            require(obj.lastObserved.instant >= obj.firstObserved.instant,
                lazyMessage = { "last_observed must be greater than or equal to first_observed." })

            require(obj.numberObserved.value in 1..999999999,
                lazyMessage = { "number_observed must be between 1 and 999,999,999." })

            require(obj.objectRefs.any { it.type in StixObjectRegistry.scoRegistry.keys },
                lazyMessage = { "object_refs must contain at least one SCO." })
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class ObservedData(
    override val firstObserved: StixInstant,
    override val lastObserved: StixInstant,
    override val numberObserved: StixInteger,
    override val objectRefs: StixIdentifiers,
    override val type: StixType = ObservedDataSdo.stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean(),
    override val confidence: StixConfidence? = null,
    override val lang: StixLang? = null
) :
    ObservedDataSdo {

    init {
        ObservedDataSdo.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ObservedDataSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}