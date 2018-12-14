package stix.datamarkings

import io.digitalstate.stix.datamarkings.MarkingDefinition
import io.digitalstate.stix.datamarkings.Tlp
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

}