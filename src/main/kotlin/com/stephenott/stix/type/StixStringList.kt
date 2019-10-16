package com.stephenott.stix.type

class StixStringList(private val list: List<String>): List<String> by list{
    init {
        list.all { it.isNotEmpty()}
    }
}