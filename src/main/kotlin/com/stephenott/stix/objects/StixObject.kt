package com.stephenott.stix.objects

import com.stephenott.stix.StixContent
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship

/**
 * Parent object for all STIX objects: SDO, SCO, SRO, Metadata
 */
interface StixObject :
    StixContent {

    fun allowedRelationships(): List<AllowedRelationship>

}