package com.stephenott.stix.objects.meta.lco.objects

import com.stephenott.stix.objects.core.sdo.objects.AttackPatternSdo
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.meta.lco.LanguageContentObject
import com.stephenott.stix.type.*

interface LanguageContentLco : LanguageContentObject {
    val objectRef: StixIdentifier
    val objectModified: StixInstant?
    val contents: LanguageContentDictionary

    companion object {
        val stixType = StixType("language-content")

        val allowedRelationships: List<AllowedRelationship> = listOf(
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
    override val confidence: StixConfidence? = null
) :
    LanguageContentLco {

    override fun allowedRelationships(): List<AllowedRelationship> {
        return LanguageContentLco.allowedRelationships
    }

}