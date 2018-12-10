package stix.desrialization

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.bundle.BundleObject
import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.common.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Malware
import io.digitalstate.stix.sdo.types.KillChainPhase
import io.digitalstate.stix.sro.objects.Relationship
import spock.lang.Specification

import java.time.Instant

class BundleSpec extends Specification {

    def "Basic 'uses' Relationship object and addition to bundle"(){
        when: "Create a Relationship with Attack Pattern and Malware"

        Relationship usesRel = Relationship.builder()
                .relationshipType("uses")
                .created(Instant.now())
                .sourceRef(AttackPattern.builder()
                        .name("Some Attack Pattern 1")
                        .build())
                .targetRef(Malware.builder()
                        .name("dog")
                        .addLabels("worm")
                        .build())
                .build()

        then: "print the JSON string version of the created relationship object"
        println usesRel.toJsonString()

        then: "parse the string back into a relationship object"
        BundleableObject parsedRelationship = StixParsers.parseBundleableObject(usesRel.toJsonString())
        assert parsedRelationship instanceof Relationship

        Relationship typedRelation = (Relationship)parsedRelationship

        and: "print the parsed relation"
        println typedRelation

        then: "ensure the original JSON matches the new JSON"
        assert usesRel.toJsonString() == typedRelation.toJsonString()

        then: "add the relationship into a bundle"
        Bundle bundle = Bundle.builder()
                .addObjects(usesRel)
                .build()

        and: "print the bundle json"
        println bundle.toJsonString()

        then: "parse json bundle back into object"
        BundleObject parsedBundle = StixParsers.parseBundle(bundle.toJsonString())
        assert parsedBundle instanceof Bundle
        Bundle typedBundle = (Bundle)parsedBundle

        then: "ensure original bundle and parsed bundles match in their json forms"
        assert bundle.toJsonString() == typedBundle.toJsonString()

    }

    def "bundleable object parsing"(){
        when: "setup parser and object"
        String attackPatternString = AttackPattern.builder()
                                        .name("Some Attack Pattern 1")
                                        .build().toJsonString()

        then: "can parse the json back into a attack Pattern"
        BundleableObject parsedAttackPatternBo = StixParsers.parseBundleableObject(attackPatternString)
        assert parsedAttackPatternBo instanceof AttackPattern

        AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseBundleableObject(attackPatternString)
        println parsedAttackPattern.toJsonString()
    }

    def "Update a Stix Object with new data"() {

        when: "a object is created, it is immutable"
        AttackPattern attackPattern = AttackPattern.builder()
                .name("Some Attack Pattern 1")
                .build()

        then: "additional data needs to be added to the attack pattern"

        KillChainPhase killChainPhase = KillChainPhase.builder()
                .phaseName("some phase")
                .killChainName("some name")
                .build()

        AttackPattern newAttackPattern = attackPattern.withKillChainPhases(killChainPhase)
        assert attackPattern != newAttackPattern

        BundleableObject newObject = StixParsers.parseObject(newAttackPattern.toJsonString())
        assert newObject instanceof AttackPattern
        println newObject.toJsonString()

        println newAttackPattern.inspect()

        println attackPattern.toJsonString()
        println newAttackPattern.toJsonString()
    }
}