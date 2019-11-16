package com.stephenott.stix.objects.meta.lco.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.StixRegistries
import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionAllowedRelationships
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.requireStixType
import com.stephenott.stix.objects.core.sdo.objects.AttackPatternSdo
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.SightingSro
import com.stephenott.stix.objects.meta.lco.LanguageContentObject
import com.stephenott.stix.type.*

interface LanguageContentLco : LanguageContentObject {
    val objectRef: StixIdentifier
    val objectModified: StixInstant?
    val contents: LanguageContentDictionary

    companion object: CompanionStixType,
        BusinessRulesValidator<LanguageContentLco>,
        CompanionAllowedRelationships {
        override fun objectValidationRules(obj: LanguageContentLco, stixRegistries: StixRegistries) {
            requireStixType(this.stixType, obj)
        }

        override val stixType = StixType("language-content")

        override val allowedRelationships: List<AllowedRelationship> = listOf(
        )

    }
}

data class LanguageContent(
    override val objectRef: StixIdentifier,
    override val objectModified: StixInstant? = null,
    override val contents: LanguageContentDictionary,
    override val type: StixType = AttackPatternSdo.stixType,
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
    override val stixRegistries: StixRegistries = Stix.defaultRegistries
) : LanguageContentLco {

    init {
        LanguageContentLco.objectValidationRules(this, stixRegistries)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        return LanguageContentLco.allowedRelationships
    }

}