package stix.end2end

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.coo.objects.Artifact
import io.digitalstate.stix.coo.objects.ArtifactCoo
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ObservedData
import io.digitalstate.stix.sdo.objects.ObservedDataSdo
import spock.lang.Specification

import java.time.Instant

class ObservableSdoSpec extends Specification {

    def "Create Observable with a Cyber Observable and add it to bundle"() {

        when: "create a artifact COO"

        ArtifactCoo artifact = Artifact.builder()
        .url("http://someurl.com")
        .build()

        then: "add artifact to observed data SDO"
        ObservedDataSdo observedData = ObservedData.builder()
        .firstObserved(Instant.now())
        .numberObserved(1)
        .lastObserved(Instant.now())
        .addObjects(artifact)
        .build()

        and: "Create a bundle with the observed data"
        Bundle bundle = Bundle.builder()
                .addObjects(observedData)
                .build()

        println bundle.toJsonString()

        println StixParsers.parseBundle(bundle.toJsonString()).inspect()

    }

}