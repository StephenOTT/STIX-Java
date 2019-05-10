package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Campaign
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import faker.StixMockDataGenerator

class CampaignSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()
    @Shared StixMockDataGenerator stixMockDataGenerator = new StixMockDataGenerator()

    @Unroll
    def "Generate Campaign Data: Run: '#i'"() {
        when: "Generating Campaign Data"
            Campaign originalCampaign = stixMockDataGenerator.mockCampaign()
//            println "Original Object: ${originalCampaign.toString()}"

        then: "Convert Campaign to Json"
            JsonNode originalJson = mapper.readTree(originalCampaign.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Campaign Object"
            Campaign parsedCampaign = (Campaign)StixParsers.parseObject(originalJsonString)
            Campaign parsedCampaignGeneric = StixParsers.parse(originalJsonString, Campaign.class)
//            println "Parsed Object: ${parsedCampaign}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Identity Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedCampaign.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
