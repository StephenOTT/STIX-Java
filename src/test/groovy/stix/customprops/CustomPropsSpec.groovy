package stix.customprops

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.bundle.BundleObject
import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Malware
import io.digitalstate.stix.sdo.types.KillChainPhase
import io.digitalstate.stix.sro.objects.Relationship
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant

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