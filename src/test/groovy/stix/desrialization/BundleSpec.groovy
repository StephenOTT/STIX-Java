package stix.desrialization

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sro.objects.Relationship
import spock.lang.Specification

class BundleSpec extends Specification {

    def "Basic Derived-From Relationship in Bundle"(){
        when:
        Relationship duplicateRelationship = Relationship.builder()
                .relationshipType("derived-from")
                .sourceRef(AttackPattern.builder()
                        .name("Some Attack Pattern 1")
                        .build())
                .targetRef(AttackPattern.builder()
                        .name("Some Other Attack Patter 2")
                        .build())
                .build()

        Bundle stixBundleObject = Bundle.builder()
                .addObjects(duplicateRelationship)
                .build()

        then:
        assert stixBundleObject.getObjects().size() == 1
        println stixBundleObject
    }
}