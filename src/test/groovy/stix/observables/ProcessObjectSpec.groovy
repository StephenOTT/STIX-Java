package stix.observables

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.coo.extension.types.TcpExtension
import io.digitalstate.stix.coo.extension.types.WindowsProcessExtension
import io.digitalstate.stix.coo.objects.EmailMessage
import io.digitalstate.stix.coo.objects.EmailMessageCoo
import io.digitalstate.stix.coo.objects.Process
import io.digitalstate.stix.coo.objects.ProcessCoo
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.ObservedData
import io.digitalstate.stix.sdo.objects.ObservedDataSdo
import spock.lang.Specification

import java.time.Instant

class ProcessObjectSpec extends Specification {

    def "Create Observable with a Cyber Observable and add it to bundle"() {

        when: "Create a Process COO with a Extension"

        ProcessCoo process = Process.builder()
                .isHidden(true)
                .name("Dog")
                .addExtensions(
                    WindowsProcessExtension.builder()
                        .aslrEnabled(true)
                        .windowTitle("some title")
                        .build())
                .build()

        EmailMessageCoo emailMessage = EmailMessage.builder()
            .isMultipart(true)
//            .body("DOG!")
            .build()

        then: "Add Process to observed data SDO"

        ObservedDataSdo observedData = ObservedData.builder()
                .firstObserved(Instant.now())
                .numberObserved(1)
                .lastObserved(Instant.now())
                .addObjects(process)
                .build()

        and: "Create a bundle with the observed data"

        Bundle bundle = Bundle.builder()
                .addObjects(observedData)
                .build()

        println bundle.toJsonString()

        println StixParsers.parseBundle(bundle.toJsonString()).inspect()

    }

}