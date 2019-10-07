package com.stephenott.stix.sdo.objects

import com.stephenott.stix.sdo.StixDomainObject
import com.stephenott.stix.type.*

interface CampaignSdo : StixDomainObject {
    val name: String
    val description: String?
    val aliases: String?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant?
    val objective: String?
}

data class Campaign
    (
    override val name: String,
    override val description: String? = null,
    override val aliases: String? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val objective: String? = null,
    override val type: StixType = stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean()
) : CampaignSdo {

    companion object{
        val stixType = StixType("campaign")
    }

}