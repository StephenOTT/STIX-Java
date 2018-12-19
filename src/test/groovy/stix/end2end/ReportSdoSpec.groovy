package stix.end2end

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Report
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

class ReportSdoSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()

    def "Create Report SDO and cycle from object to Json back to object"() {

        when: "a objects are created,"
        AttackPattern attackPattern1 = AttackPattern.builder()
                .name("Some Attack Pattern 1")
                .build()

        println attackPattern1.toJsonString()

        AttackPattern attackPattern2 = AttackPattern.builder()
                .name("Some Attack Pattern 2")
                .build()

        println attackPattern2.toJsonString()

        then: "Create a Report and Add Attack Patterns as Report Objects"

        Report report = Report.builder()
                .name("Some Report")
                .addLabels("attack-pattern")
                .published(Instant.now())
                .addObjectRefs(attackPattern1, attackPattern2)
                .build()

        println report.toJsonString()
        println StixParsers.parseObject(report.toJsonString()).inspect()

        and: "Create a bundle with the report"
        Bundle bundle = Bundle.builder()
                .addObjects(report)
                .build()

        println bundle.toJsonString()

        println StixParsers.parseBundle(bundle.toJsonString()).inspect()

    }

}