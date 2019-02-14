package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Report
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ReportSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Report Data: Run: '#i'"() {
        when: "Generating Report Data"
        Report originalReport = mockReport()
            println "Original Object: ${originalReport.toString()}"

        then: "Convert Report to Json"
            JsonNode originalJson = mapper.readTree(originalReport.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Report Object"
            Report parsedReport = (Report)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedReport}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Report back to into Json"
            JsonNode newJson =  mapper.readTree(parsedReport.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100) // More tests are run because of the large variation of probabilities and number of combinations
    }
}
