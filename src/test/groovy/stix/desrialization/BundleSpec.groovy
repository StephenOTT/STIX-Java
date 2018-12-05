package stix.desrialization

import io.digitalstate.stix.bundle.ImmutableStixBundleObject
import io.digitalstate.stix.sdo.objects.ImmutableAttackPatternSdo
import io.digitalstate.stix.sro.objects.ImmutableRelationshipSro
import spock.lang.Specification

class BundleSpec extends Specification {

    def "Basic D Relationship in Bundle"(){
        when:
        ImmutableRelationshipSro duplicateRelationship = ImmutableRelationshipSro.builder()
                .hydrated(true)
                .relationshipType("derived-from")
                .sourceRef(ImmutableAttackPatternSdo.builder()
                        .hydrated(true)
                        .name("Some Attack Pattern 1")
                        .build())
                .targetRef(ImmutableAttackPatternSdo.builder()
                        .hydrated(true)
                        .name("Some Other Attack Patter 2")
                        .build())
                .build()

        ImmutableStixBundleObject stixBundleObject = ImmutableStixBundleObject.builder()
                .addObjects(duplicateRelationship)
                .build()

        then:
        assert stixBundleObject.getObjects().size() == 1
        println stixBundleObject
    }

}