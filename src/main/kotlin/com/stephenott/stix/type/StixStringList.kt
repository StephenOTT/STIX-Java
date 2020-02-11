package com.stephenott.stix.type


//@TODO refactor to generic
class StixStringList(private val list: List<String>): List<String> by list{
    init {
        require(list.isNotEmpty(), lazyMessage = {"STIX Spec does not allow empty lists."}) // https://github.com/oasis-tcs/cti-stix2/issues/144#issuecomment-481370524
    }
}