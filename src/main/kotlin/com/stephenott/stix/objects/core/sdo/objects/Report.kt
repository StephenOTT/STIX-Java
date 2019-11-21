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
import com.stephenott.stix.type.vocab.ReportTypes

interface ReportSdo : StixDomainObject {
    val name: String
    val description: String?
    val reportTypes: ReportTypes
    val published: StixTimestamp
    val objectRefs: StixIdentifiers

    companion object : CompanionStixType,
        BusinessRulesValidator<ReportSdo>,
        CompanionAllowedRelationships {

        override val stixType = StixType("report")

        override fun objectValidationRules(obj: ReportSdo, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

        override val allowedRelationships: List<AllowedRelationship> = listOf()
    }
}

data class Report(
        override val name: String,
        override val description: String? = null,
        override val reportTypes: ReportTypes,
        override val published: StixTimestamp,
        override val objectRefs: StixIdentifiers,
        override val type: StixType = ReportSdo.stixType,
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
) : ReportSdo {

    init {
        if (this.stixValidateOnConstruction) {
            ReportSdo.objectValidationRules(this, stixInstance)
        }
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return ReportSdo.allowedRelationships + RelationshipSro.allowedCommonRelationships
    }

}