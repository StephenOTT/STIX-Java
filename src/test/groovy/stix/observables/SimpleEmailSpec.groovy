package stix.observables;

import java.time.Instant

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.coo.objects.EmailAddress
import io.digitalstate.stix.coo.objects.EmailMessage
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ObservedData
import io.digitalstate.stix.sdo.objects.ObservedDataSdo
import spock.lang.Specification;

public class SimpleEmailSpec extends Specification {
	
	def "Create email message with object_refs"() {
		when: "create 2 email addresses and a message"
		
		// manually set object keys
		EmailAddress email0 = EmailAddress.builder()
								.value("jdoe@example.com")
								.displayName("John Doe")
								.observableObjectKey("0")
								.build()
		EmailAddress email1 = EmailAddress.builder()
								.value("mary@example.com")
								.displayName("Mary Smith")
								.observableObjectKey("1")
								.build()
								
		EmailMessage msg = EmailMessage.builder()
								.subject("Saying Hello")
								.date(Instant.now())
								.observableObjectKey("2")
								.fromRef(email0.getObservableObjectKey())
								.toRefs(new HashSet(Arrays.asList(email1.getObservableObjectKey())))
								.build()
								
		then: "add to observed data"
		ObservedDataSdo observedData = ObservedData.builder()
		.firstObserved(Instant.now())
		.numberObserved(3)
		.lastObserved(Instant.now())
		.addObjects(msg)
		.build()
		
		and: "Create a bundle with the observed data"
		
		Bundle bundle = Bundle.builder()
				.addObjects(observedData)
				.build()

		println bundle.toJsonString()

		println StixParsers.parseBundle(bundle.toJsonString()).inspect()
	}

}
