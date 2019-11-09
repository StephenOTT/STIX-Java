package com.stephenott.stix.common

interface StixContentMapper {

    val markingObjectRegistry: StixMarkingObjectRegistry
    val objectRegistry: StixObjectRegistry
    val relationshipRegistry: StixObjectRelationshipRegistry

}