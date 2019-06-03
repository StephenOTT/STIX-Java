package stix.bundle

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.common.StixInstant
import io.digitalstate.stix.coo.objects.DomainName
import io.digitalstate.stix.graph.StixGraphGenerator
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.ObservedData
import io.digitalstate.stix.sro.objects.Relationship
import io.digitalstate.stix.sro.objects.Sighting
import spock.lang.Specification

class BundleGraphSpec extends Specification {

    def "Generate a Simple Graph of STIX data"() {
        when: "Create Objects"
        AttackPattern attackPattern1 = AttackPattern.builder().name("attk1").build()
        AttackPattern attackPattern2 = AttackPattern.builder().name("attk2").build()

        Relationship relationship1 = Relationship.builder()
                .sourceRef(attackPattern1)
                .targetRef(attackPattern2)
                .relationshipType("related-to")
                .build()

        DomainName domainName1 = DomainName.builder()
                .value("http://google.com")
                .build()
        ObservedData observedData1 = ObservedData.builder()
                .addObjects(domainName1)
                .firstObserved(new StixInstant())
                .lastObserved(new StixInstant())
                .numberObserved(2)
                .build()

        Sighting sighting1 = Sighting.builder()
            .firstSeen(new StixInstant())
            .lastSeen(new StixInstant())
            .count(1)
            .sightingOfRef(attackPattern1)
            .addObservedDataRef(observedData1)
            .build()

        then: "build bundle"

        Bundle bundle = Bundle.builder()
                .addObjects(attackPattern1, attackPattern2, relationship1, observedData1, sighting1)
                .build()

        println bundle.toString()

        then: "generate graph json"

        StixGraphGenerator graph = new StixGraphGenerator(bundle)

        println graph.process().toString()

        println graph.toJson()

    }
}
