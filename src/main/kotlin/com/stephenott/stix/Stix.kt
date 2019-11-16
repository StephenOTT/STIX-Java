package com.stephenott.stix

import com.stephenott.stix.common.StixMarkingObjectRegistry
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.common.StixObjectRelationshipRegistry

class Stix() {

    //@TODO future place that common configurations will go.

    companion object {
        val defaultRegistries: StixRegistries = StixRegistries()
    }

}

data class StixRegistries(
    val objectRegistry: StixObjectRegistry = StixObjectRegistry(),
    val relationshipRegistry: StixObjectRelationshipRegistry = StixObjectRelationshipRegistry(),
    val markingObjectRegistry: StixMarkingObjectRegistry = StixMarkingObjectRegistry()
) {}