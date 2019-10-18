package com.stephenott.stix.type

import com.stephenott.stix.objects.core.sco.extension.ScoExtension

class Extensions(private val dictionary: Set<ScoExtension>): Set<ScoExtension> by dictionary {}