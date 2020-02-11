package com.stephenott.stix

import com.stephenott.stix.common.StixMarkingObjectRegistry
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.common.StixObjectRelationshipRegistry
import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

data class Stix(
        val registries: StixRegistries = StixRegistries()
) {

    //@TODO future place that common configurations will go.

    companion object {
        val defaultRegistries: StixRegistries = StixRegistries()

        var defaultValidateOnConstruction: Boolean = true

        val defaultStixInstance: Stix = Stix(defaultRegistries)
    }


    inline fun <reified T:StixContent> create(stixContent: T): T{
            val copy = stixContent::class.memberFunctions.first { it.name == "copy" }
            val instanceParam = copy.instanceParameter!!
            val validateParam = copy.parameters.first { it.name == StixContent::stixValidateOnConstruction.name }
            val stixInstanceParam = copy.parameters.first { it.name == StixContent::stixInstance.name }
            return copy.callBy(mapOf(instanceParam to stixContent, validateParam to true, stixInstanceParam to this)) as T
    }

}

data class StixRegistries(
    val objectRegistry: StixObjectRegistry = StixObjectRegistry(),
    val relationshipRegistry: StixObjectRelationshipRegistry = StixObjectRelationshipRegistry(),
    val markingObjectRegistry: StixMarkingObjectRegistry = StixMarkingObjectRegistry()
) {}