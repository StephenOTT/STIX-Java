package stix.datamarkings

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.datamarkings.GranularMarking
import io.digitalstate.stix.datamarkings.MarkingDefinition
import io.digitalstate.stix.datamarkings.Tlp
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Identity
import spock.lang.Specification

import javax.validation.ConstraintViolationException

class MarkingDefinitionSpec extends Specification {

    def "definition_type and marking object constraint with valid values"(String tlpValue) {

        when:
        Tlp tlp = Tlp.builder().tlp(tlpValue).build()
        MarkingDefinition markingDefinition = MarkingDefinition.builder()
                .definition(tlp)
                .build()

        then:
        assert markingDefinition.getDefinitionType() == "tlp"
        assert markingDefinition.getDefinition() instanceof Tlp
        assert ((Tlp) markingDefinition.getDefinition()).tlp == tlpValue

        where:
        tlpValue | _
        "white"  | _
        "green"  | _
        "amber"  | _
        "red"    | _
    }


    def "definition_type and marking object constraint with Invalid values"(String tlpValue) {

        when:
        Tlp tlp = Tlp.builder().tlp(tlpValue).build()
        MarkingDefinition markingDefinition = MarkingDefinition.builder()
                .definition(tlp)
                .build()

        then:
        thrown(ConstraintViolationException)

        where:
        tlpValue        | _
        "off-white"     | _
        "winter-green"  | _
        "yellow"        | _
        "fire"          | _
    }

    def "mark Test 1"(){

        when:
        Tlp tlp = Tlp.builder().tlp("red").build()
        MarkingDefinition markingDefinition = MarkingDefinition.builder()
                .definition(tlp)
                .definitionType("tlp")
                .build()

        GranularMarking granularMarking = GranularMarking.builder()
                .markingRef(markingDefinition)
                .addSelectors("granular_markings", "created_by_ref")
                .addSelectors("created")
                .build()

        AttackPattern attackPattern = AttackPattern.builder()
                .name("some Attack Pattern")
                .addGranularMarkings(granularMarking)
                .createdByRef(Identity.builder()
                    .name("some Identity")
                    .identityClass("individual")
                    .build())
                .build()

        Bundle bundle = Bundle.builder()
            .addObjects(attackPattern).build()

        then:
        println attackPattern.toJsonString()
        println bundle.toJsonString()

    }

}