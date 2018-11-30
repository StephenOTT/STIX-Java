package stix.bundlepopulation

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.domainobjects.AttackPattern
import io.digitalstate.stix.domainobjects.Identity
import io.digitalstate.stix.relationshipobjects.Relation
import io.digitalstate.stix.relationshipobjects.Relationship
import spock.lang.Shared
import spock.lang.Specification

import java.time.Instant
import java.time.ZoneId


class BundlePopulationSpec extends Specification {

//    @Shared String jsonPath = '/stix/json/domainobjects/AttackPattern-Full-Bundle-1.json'
//    @Shared ObjectMapper mapper = new ObjectMapper()
//    @Shared URL jsonUrl = getClass().getResource(jsonPath)
//    @Shared JsonNode fullJson = mapper.readTree(jsonUrl)

    def "AttackPattern Minimum Properties"() {
        when: "Creating a Bundle"
        Bundle bundle = new Bundle()

        and: "Create a attack pattern"
        AttackPattern attackPattern = new AttackPattern("Some Attack Pattern Name")

        and: "Create a Identity"
        Identity identity = new Identity("Some Name", "individual")

        and: "Relate the attack pattern to the identity with 'targets' relation"
        Relationship relationship = new Relationship('targets', attackPattern, identity)
        attackPattern.getTargets().add(new Relation(relationship))

        and: "Add attack pattern to bundle"
        bundle.addObjects(attackPattern)

        and: "Auto-populate the bundle with the identity and the relationship"
        bundle.autoDetectBundleObjects()

        then: "bundle should have 3 objects"
        assert bundle.getObjects().size() == 3
        assert bundle.getObjects().contains(attackPattern)
        assert bundle.getObjects().contains(relationship)
        assert bundle.getObjects().contains(identity)
    }

}