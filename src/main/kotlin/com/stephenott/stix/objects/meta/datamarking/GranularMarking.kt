package com.stephenott.stix.objects.meta.datamarking

import com.stephenott.stix.type.StixIdentifier
import com.stephenott.stix.type.StixLang
import com.stephenott.stix.type.StixStringList

interface GranularMarkingGm{
    val lang: StixLang?
    val markingRef: StixIdentifier?
    val selectors: StixStringList
}

data class GranularMarking(
    override val lang: StixLang?,
    override val markingRef: StixIdentifier?,
    override val selectors: StixStringList
): GranularMarkingGm {

    init {
        if (lang == null){
            require(markingRef != null)
            require(markingRef.type == MarkingDefinitionDm.stixType)
        } else {
            require(markingRef == null)
        }


    }

    constructor(lang: StixLang, selectors: StixStringList): this(lang, null, selectors)
    constructor(markingRef: StixIdentifier, selectors: StixStringList): this(null, markingRef, selectors)
}