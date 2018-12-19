package stix.customprops

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import spock.lang.Shared
import spock.lang.Specification

class CustomPropsSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()

    def "Parse Attack Pattern with custom properties"(){

        when:"setup file access to attack pattern"

        String attackJson = getClass()
                .getResource("/stix/json/customprops/AttackPattern-with-customprops-1.json").getText("UTF-8")

        then: "Parse json into bundle"
        AttackPattern attackPattern = (AttackPattern)StixParsers.parseObject(attackJson)
        println attackPattern.getCustomProperties().inspect()

        println attackPattern.toJsonString()
    }
}