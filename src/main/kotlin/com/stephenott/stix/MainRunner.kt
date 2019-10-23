package com.stephenott.stix

import com.fasterxml.jackson.module.kotlin.readValue
import com.stephenott.stix.objects.core.sco.objects.IPv6Address
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sdo.objects.AttackPattern
import com.stephenott.stix.objects.core.sro.objects.Relationship
import com.stephenott.stix.serialization.json.createStixMapper
import com.stephenott.stix.type.RelationshipType
import com.stephenott.stix.type.StixBoolean
import com.stephenott.stix.objects.core.sdo.StixDomainObject.*
import com.stephenott.stix.objects.core.sdo.objects.AttackPatternSdo
import com.stephenott.stix.objects.core.sro.StixRelationshipObject
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.objects.meta.StixMetaObject
import com.stephenott.stix.serialization.json.StixContentMapper
import com.stephenott.stix.serialization.json.toJson

object MainRunner {

    @JvmStatic
    fun main(args: Array<String>){

        val ap1 = AttackPattern("124")
        val ap2 = AttackPattern(name="1245")
        val ip6 = IPv6Address("dog")

        val mapper = StixContentMapper()

        val jsonString: String = ap1.toJson(mapper)
        println(jsonString)

        println(mapper.parseJson<StixContent>(jsonString))
        println(mapper.parseJson<StixDomainObject>(jsonString))
        println(mapper.parseJson<AttackPatternSdo>(jsonString))
        println(mapper.parseJson<AttackPattern>(jsonString))


        val rel = Relationship(
            relationshipType = RelationshipType("duplicate-of"),
            sourceRef = ap1,
            targetRef = ap2
        )
        println(rel.toJson(mapper))
//        println(mapper.parseJson<StixMetaObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<StixRelationshipObject>(rel.toJson(mapper)))
//        println(mapper.parseJson<RelationshipSro>(rel.toJson(mapper)))
//        println(mapper.parseJson<Relationship>(rel.toJson(mapper)))
    }
}