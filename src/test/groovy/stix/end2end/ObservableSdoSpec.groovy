package stix.end2end

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.coo.objects.Artifact
import io.digitalstate.stix.coo.objects.ArtifactCoo
import io.digitalstate.stix.coo.objects.Ipv4Address
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ObservedData
import io.digitalstate.stix.sdo.objects.ObservedDataSdo
import spock.lang.Specification

import java.time.Instant

import javax.validation.ConstraintViolationException

import org.junit.Rule
import org.junit.rules.TestName

class ObservableSdoSpec extends Specification {
	@Rule public TestName name = new TestName();
	
    def "Create Observable with a Cyber Observable and add it to bundle"() {

        when: "create a artifact COO"
		System.out.println(name.getMethodName());
		
        ArtifactCoo artifact = Artifact.builder()
							.mimeType("image/jpeg")
							.build()

        then: "add artifact to observed data SDO"
        ObservedDataSdo observedData = ObservedData.builder()
        .firstObserved(Instant.now())
        .numberObserved(1)
        .lastObserved(Instant.now())
        .putObjects("0",artifact)
        .build()

        and: "Create a bundle with the observed data"
        Bundle bundle = Bundle.builder()
                .addObjects(observedData)
                .build()

        println bundle.toJsonString()

        println StixParsers.parseBundle(bundle.toJsonString()).inspect()

    }

	def "Create Invalid Artifact"() {
		
		when: "create a artifact COO with that won't pass validation"
		System.out.println(name.getMethodName());
		// the mimeType has a OptionalPattern regexp to match
		// not sure how to make the actual exception print out
		
		ArtifactCoo artifact = Artifact.builder()
							.mimeType("HelloWorld")
							.build()

		then: "expect a ConstraintViolation"

		thrown(ConstraintViolationException)

	}
	
	def "Create an IPv4 address"() {
		when: "create a IPv4 COO"
		System.out.println(name.getMethodName());
		Ipv4Address ip = Ipv4Address.builder()
						.value("198.51.100.3")
						.build()

		then: "add artifact to observed data SDO"
		ObservedDataSdo observedData = ObservedData.builder()
		.firstObserved(Instant.now())
		.numberObserved(1)
		.lastObserved(Instant.now())
		.putObjects("0",ip)
		.build()

		and: "Create a bundle with the observed data"
		Bundle bundle = Bundle.builder()
				.addObjects(observedData)
				.build()

		println bundle.toJsonString()

		println StixParsers.parseBundle(bundle.toJsonString()).inspect()
	}
		
}