package com.stephenott.stix.`object`

import com.stephenott.stix.StixContent
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship

/**
 * Parent object for all STIX objects: SDO, SCO, SRO, Metadata
 */
interface StixObject :
    StixContent {

    fun allowedRelationships(): List<AllowedRelationship>

}