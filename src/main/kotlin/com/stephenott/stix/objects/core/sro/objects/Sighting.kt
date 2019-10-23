package com.stephenott.stix.objects.core.sro.objects

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.objects.core.sdo.objects.IdentitySdo
import com.stephenott.stix.objects.core.sdo.objects.LocationSdo
import com.stephenott.stix.objects.core.sdo.objects.ObservedDataSdo
import com.stephenott.stix.objects.core.sro.StixRelationshipObject
import com.stephenott.stix.type.*

interface SightingSro : StixRelationshipObject {
    val description: String?
    val firstSeen: StixInstant?
    val lastSeen: StixInstant? //@TODO *** REVIEW SPEC: Says must be after first seen.  But does not say "equals
    val count: StixInteger?
    val sightingOfRef: StixIdentifier
    val observedDataRefs: StixIdentifiers?
    val whereSightedRefs: StixIdentifiers?
    val summary: StixBoolean

    companion object : CompanionStixType,
        BusinessRulesValidator<SightingSro> {

        override val stixType: StixType = StixType("sighting")

        override fun objectValidationRules(obj: SightingSro) {
            if (obj.firstSeen != null && obj.lastSeen != null) {
                require(obj.lastSeen!!.instant.isAfter(obj.firstSeen!!.instant),
                    lazyMessage = { "last_seen must be later than first_seen." })
            }

            obj.count?.let {
                require(it.value in 0..999999999,
                    lazyMessage = { "count must be between 0 and 999,999,999." })
            }

            require(obj.sightingOfRef.type in StixObjectRegistry.sdoRegistry.keys,
                lazyMessage = { "sighting_of_ref must reference only a SDO." }) // @TODO should also support custom objects

            obj.observedDataRefs?.let {
                require(it.all { id -> id.type == ObservedDataSdo.stixType },
                    lazyMessage = { "observed_data_refs must only have values that are references to Observed Data SDO." })
            }

            obj.whereSightedRefs?.let {
                require(it.all { id -> id.type in listOf(IdentitySdo.stixType, LocationSdo.stixType) },
                    lazyMessage = { "where_sighted_refs must only have values that reference Identity or Location SDO." })
            }
        }
    }
}

data class Sighting(
    override val description: String? = null,
    override val firstSeen: StixInstant? = null,
    override val lastSeen: StixInstant? = null,
    override val count: StixInteger? = null,
    override val sightingOfRef: StixIdentifier,
    override val observedDataRefs: StixIdentifiers? = null,
    override val whereSightedRefs: StixIdentifiers? = null,
    override val summary: StixBoolean = StixBoolean(),
    override val type: StixType = SightingSro.stixType,
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
) : SightingSro {

    init {
        SightingSro.objectValidationRules(this)
    }

    override fun allowedRelationships(): List<AllowedRelationship> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}