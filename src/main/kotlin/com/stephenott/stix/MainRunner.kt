package com.stephenott.stix

import com.stephenott.stix.objects.core.sdo.objects.AttackPattern
import com.stephenott.stix.objects.core.sro.objects.Relationship
import com.stephenott.stix.type.RelationshipType

object MainRunner {

    @JvmStatic
    fun main(args: Array<String>){

        val ap1 = AttackPattern("124")
        val ap2 = AttackPattern("124")
        val rel = Relationship(
            relationshipType = RelationshipType("related-to"),
            sourceRef = ap1,
            targetRef = ap2
        )
        println(rel)
    }
}