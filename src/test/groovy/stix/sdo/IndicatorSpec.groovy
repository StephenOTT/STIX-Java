package stix.sdo

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Identity
import io.digitalstate.stix.sdo.objects.Indicator
import spock.lang.Specification
import spock.lang.Unroll

class IndicatorSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Indicator Data: Run: '#i'"() {
        when: "Generating Indicator Data"
        Indicator originalIndicator = mockIndicator()
            println "Original Object: ${originalIndicator.toString()}"

        then: "Convert Indicator to Json"
            String originalJson = originalIndicator.toJsonString()
            println "Original Json: ${originalJson}"

        then: "Parse Json back into Indicator Object"
            Indicator parsedIndicator = (Indicator)StixParsers.parseObject(originalJson)
            println "Parsed Object: ${parsedIndicator}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Indicator back to into Json"
            String newJson = parsedIndicator.toJsonString()
            println "New Json: ${newJson}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
