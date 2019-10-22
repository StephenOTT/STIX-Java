package com.stephenott.stix

import com.stephenott.stix.objects.core.sco.objects.IPv6Address
import com.stephenott.stix.objects.core.sdo.objects.AttackPattern
import com.stephenott.stix.objects.core.sro.objects.Relationship
import com.stephenott.stix.type.RelationshipType

object MainRunner {

    @JvmStatic
    fun main(args: Array<String>){

        val ap1 = AttackPattern("124")
        val ap2 = AttackPattern("124")
        val ip6 = IPv6Address("dog")

        println(ap1)
        val rel = Relationship(
            relationshipType = RelationshipType("duplicate-of"),
            sourceRef = ap1,
            targetRef = ip6
        )
    }
}