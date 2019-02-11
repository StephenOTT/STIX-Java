package stix.observables


import io.digitalstate.stix.coo.extension.types.PdfFileExtension
import io.digitalstate.stix.coo.extension.types.PdfFileExtensionExt
import io.digitalstate.stix.coo.extension.types.WindowsProcessExtension
import io.digitalstate.stix.coo.objects.*
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
                        .isAslrEnabled(true)
                        .windowTitle("some title")
                        .build())
                .build()

        EmailMessageCoo emailMessage = EmailMessage.builder()
            .isMultipart(true)
//            .body("DOG!")
            .build()

        PdfFileExtensionExt pdfFileExtensionExt = PdfFileExtension.builder()
            .pdfId0("123")
            .build()

        then: "Add Process to observed data SDO"

        ObservedDataSdo observedData = ObservedData.builder()
                .firstObserved(Instant.now())
                .numberObserved(1)
                .lastObserved(Instant.now())
                .addObject(process)
                .build()

        DirectoryCoo dir2 = Directory.builder().path("cat/cat/cat").build()
        DirectoryCoo dir1 = Directory.builder().path("cat/cat").addContainsRef(dir2.getObservableObjectKey()).build()

        DirectoryCoo directoryCoo = Directory.builder()
                .path("/123/123/123")
                .addContainsRef(dir2.getObservableObjectKey())
                .build()

        ObservedDataSdo directoryTest1 = ObservedData.builder()
                .firstObserved(Instant.now())
                .numberObserved(1)
                .lastObserved(Instant.now())
                .addObjects(directoryCoo, dir2)
                .build()

        println directoryTest1.toJsonString()
        println StixParsers.parseObject(directoryTest1.toJsonString())

//        and: "Create a bundle with the observed data"
//
//        Bundle bundle = Bundle.builder()
//                .addObjects(observedData)
//                .build()
//
//        println bundle.toJsonString()
//
//        println StixParsers.parseBundle(bundle.toJsonString()).inspect()

    }

}