package com.stephenott.stix.type

class StixLabels(private val labels: LinkedHashSet<String>): Set<String> by labels {
    init {
        labels.all { it.isNotEmpty() }
    }
}