package com.stephenott.stix.type

import com.stephenott.stix.objects.core.sco.extension.ScoExtension

class Extensions(private val extensions: Set<ScoExtension> = linkedSetOf()):
    Set<ScoExtension> by extensions {
}