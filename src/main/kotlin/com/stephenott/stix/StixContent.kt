package com.stephenott.stix

import com.fasterxml.jackson.annotation.JsonIgnore
import com.stephenott.stix.common.StixIdentifierProp
import com.stephenott.stix.common.StixTypeProp

interface StixContent :
    StixTypeProp,
    StixIdentifierProp {

    val stixInstance: Stix
    val stixValidateOnConstruction: Boolean

    companion object{}

}