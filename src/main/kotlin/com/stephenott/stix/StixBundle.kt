package com.stephenott.stix

import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.type.StixIdentifier
import com.stephenott.stix.type.StixType

interface StixBundle: StixContent{
    val objects: LinkedHashSet<StixObject>

    companion object {
        val stixType = StixType("bundle")
    }
}



data class Bundle(override val type: StixType = StixBundle.stixType,
                  override val id: StixIdentifier = StixIdentifier(type),
                  override val objects: LinkedHashSet<StixObject>
): StixBundle{

}