package stix.end2end

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Indicator
import io.digitalstate.stix.sdo.objects.IndicatorSdo
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

class IndicatorSdoSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()

    def "Create Indicator SDO and cycle from object to Json back to object"() {

        when: "a objects are created,"
        IndicatorSdo indicator1 = Indicator.builder()
                .addLabels("malicious-activity")
                .name("some name")
                .validFrom(Instant.now())
                .pattern("some pattern")
                .build()

        then: "Create a Report and Add Attack Patterns as Report Objects"

        println indicator1.toJsonString()
        String indicator1Json = indicator1.toJsonString()

        println StixParsers.parseObject(indicator1Json).inspect()

        and: "Create a bundle with the indicator"
        Bundle bundle = Bundle.builder()
                .addObjects(indicator1)
                .build()

        println bundle.toJsonString()

        println StixParsers.parseBundle(bundle.toJsonString()).inspect()

    }

}