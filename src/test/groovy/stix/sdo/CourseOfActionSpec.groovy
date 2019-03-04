package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.CourseOfAction
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import stix.StixMockDataGenerator

class CourseOfActionSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Course of Action Data: Run: '#i'"() {
        when: "Generating Identity Data"
            CourseOfAction originalCourseOfAction = mockCourseOfAction()
//            println "Original Object: ${originalCourseOfAction.toString()}"

        then: "Convert Course of Action to Json"
            JsonNode originalJson = mapper.readTree(originalCourseOfAction.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Course of Action Object"
            CourseOfAction parsedCourseOfAction = (CourseOfAction)StixParsers.parseObject(originalJsonString)
//            println "Parsed Object: ${parsedCourseOfAction}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Identity Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedCourseOfAction.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }
}
