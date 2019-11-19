package com.stephenott.stix.objects.meta.lco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.objects.AttackPatternSdo
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.meta.lco.LanguageContentObject
import com.stephenott.stix.type.*

interface LanguageContentLco : LanguageContentObject {
    val objectRef: StixIdentifier
    val objectModified: StixTimestamp?
    val contents: LanguageContentDictionary

    companion object: CompanionStixType,
        BusinessRulesValidator<LanguageContentLco>,
        CompanionAllowedRelationships {
        override fun objectValidationRules(obj: LanguageContentLco, stixInstance: Stix) {
            requireStixType(this.stixType, obj)
        }

        override val stixType = StixType("language-content")

        override val allowedRelationships: List<AllowedRelationship> = listOf(
        )

    }
}

data class LanguageContent(
        override val objectRef: StixIdentifier,
        override val objectModified: StixTimestamp? = null,
        override val contents: LanguageContentDictionary,
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
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : LanguageContentLco {

    init {
        LanguageContentLco.objectValidationRules(this, stixInstance)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return LanguageContentLco.allowedRelationships
    }

}