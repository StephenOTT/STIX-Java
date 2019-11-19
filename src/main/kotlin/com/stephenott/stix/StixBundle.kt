package com.stephenott.stix

import com.stephenott.stix.common.BusinessRulesValidator
import com.stephenott.stix.common.CompanionStixType
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.type.StixIdentifier
import com.stephenott.stix.type.StixType

interface StixBundle : StixContent {
    val objects: LinkedHashSet<StixObject>

    companion object :
            CompanionStixType,
            BusinessRulesValidator<StixBundle> {

        override val stixType = StixType("bundle")

        override fun objectValidationRules(obj: StixBundle, stixInstance: Stix) {
            //@TODO
        }
    }
}


data class Bundle(override val type: StixType = StixBundle.stixType,
                  override val id: StixIdentifier = StixIdentifier(type),
                  override val objects: LinkedHashSet<StixObject>,
                  override val stixInstance: Stix = Stix.defaultStixInstance,
                  override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
) : StixBundle {

    init {
        StixBundle.objectValidationRules(this, stixInstance)
    }

}