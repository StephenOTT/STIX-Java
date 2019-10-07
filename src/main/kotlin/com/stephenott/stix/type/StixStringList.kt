package com.stephenott.stix.type

class StixStringList(private val list: LinkedHashSet<String>): Set<String> by list{
    init {
        list.all { it.isNotEmpty()}
    }
}