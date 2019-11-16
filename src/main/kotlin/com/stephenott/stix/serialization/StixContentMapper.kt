package com.stephenott.stix.serialization

import com.stephenott.stix.StixRegistries

interface StixContentMapper {
    val mapper: Any

    val stixRegistries: StixRegistries
}

