package stix.sdo

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Campaign
import spock.lang.Specification
import spock.lang.Unroll

class CampaignSpec extends Specification implements StixMockDataGenerator {

    @Unroll
    def "Generate Campaign Data: Run: '#i'"() {
        when: "Generating Campaign Data"
        Campaign originalCampaign = mockCampaign()
            println "Original Object: ${originalCampaign.toString()}"

        then: "Convert Campaign to Json"
            String originalJson = originalCampaign.toJsonString()
            println "Original Json: ${originalJson}"

        then: "Parse Json back into Campaign Object"
            Campaign parsedCampaign = (Campaign)StixParsers.parseObject(originalJson)
            println "Parsed Object: ${parsedCampaign}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Identity Object back to into Json"
            String newJson = parsedCampaign.toJsonString()
            println "New Json: ${newJson}"

        then: "New Json should match Original Json"
            assert newJson == originalJson

        where:
            i << (1..100)
    }
}
