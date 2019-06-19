package stix.custom

import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.custom.StixCustomObject
import io.digitalstate.stix.custom.objects.CustomObject
import io.digitalstate.stix.json.StixParsers
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class CustomObjectSpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generic Object Test 1"() {
        when: "Attempt to Parse a custom object"
            String jsonString = getClass().getResource("/stix/custom/custom_object_1.json").getText("UTF-8")

        then:
            StixCustomObject originalObject = (StixCustomObject)StixParsers.parseObject(jsonString)
            StixCustomObject originalObjectGeneric = StixParsers.parse(jsonString, CustomObject.class)
            BundleableObject bundleableObject = StixParsers.parse(jsonString, BundleableObject.class)
//            println originalObject
//            println originalObjectGeneric
//            println bundleableObject
//            println "********"
    }
}
