

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.domainobjects.AttackPattern
import io.digitalstate.stix.domainobjects.Identity
import io.digitalstate.stix.domainobjects.Report
import io.digitalstate.stix.helpers.StixDataFormats
import io.digitalstate.stix.relationshipobjects.Relation
import io.digitalstate.stix.relationshipobjects.Relationship
import spock.lang.Specification

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime


class ReportSdo extends Specification {

    def "End2End Report SDO: Create Object, Convert to Json, Convert Json back to Object"() {
        when: "Create a attack pattern"
        AttackPattern attackPattern = new AttackPattern("Some Attack Pattern Name")

        and: "Create a Identity"
        Identity identity = new Identity("Some Name", "individual")

        and: "Relate the attack pattern to the identity with 'targets' relation"
        Relationship relationship = new Relationship('targets', attackPattern, identity)
        attackPattern.getTargets().add(new Relation(relationship))

        and: "Create a Report and add attack pattern and Identity to the Report"
        Relation relationAttkPatt = new Relation(attackPattern)
        Relation relationIden = new Relation(identity)
        Relation relationRelationship = new Relation(relationship)
        LinkedHashSet<Relation> objectRefs = [relationAttkPatt, relationIden, relationRelationship]

        ZonedDateTime publishedDate = Instant.now().atZone(ZoneId.of(StixDataFormats.DATETIMEZONE))

        LinkedHashSet<String> labels = ["threat-report"]

        Report report = new Report("Some Report",
                labels,
                publishedDate,
                objectRefs)

        then: "Report should have objectRefs of the 3 relations"
        assert report.getObjectRefs().size() == 3
        assert report.getObjectRefs().contains(relationAttkPatt)
        assert report.getObjectRefs().contains(relationIden)
        assert report.getObjectRefs().contains(relationRelationship)

        and: "each relation should have a object"
        report.getObjectRefs().each {
            assert it.hasObject()
        }

        then: "Convert Report to JSON"
        String reportJson = report.toJsonString()

        and: "parse json into JSON object for eval"
        ObjectMapper mapper = new ObjectMapper()
        JsonNode parsedJson = mapper.readTree(reportJson)

        then: "Check that only the expected field names are returned"
        def expectedFieldNames = ["type",
                                 "id",
                                 "created",
                                 "modified",
                                 "revoked",
                                 "labels",
                                 "name",
                                 "published",
                                 "object_refs"]
        parsedJson.fieldNames().forEachRemaining { prop ->
            assert expectedFieldNames.contains(prop)
        }

        and: "core fields have the expected values"
        assert parsedJson.get("type").asText() == report.getType()
        assert parsedJson.get("id").asText() == report.getId()
        assert Instant.parse(parsedJson.get("created").asText())
                .atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)) == report.getCreated().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE))
        assert Instant.parse(parsedJson.get("modified").asText())
                .atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)) == report.getModified().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE))
        assert parsedJson.get("revoked").asText() == report.getRevoked().toString()

        and: "labels fields have the expected values"
        assert parsedJson.get("labels").isArray()
        assert parsedJson.get("labels").size() == 1
        assert parsedJson.get("labels").get(0).asText() == report.getLabels()[0]

        and: "Report specific fields have the expected values"
        assert parsedJson.get("name").asText() == report.getName()
        assert Instant.parse(parsedJson.get("published").asText())
                .atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)) == report.getPublished().withZoneSameInstant(ZoneId.of(StixDataFormats.DATETIMEZONE))

        and: "Object_refs have the expected values"
        assert parsedJson.get("object_refs").isArray()
        assert parsedJson.get("object_refs").size() == 3
        assert parsedJson.get("object_refs").get(0).asText() == report.getObjectRefs()[0].getObject().getId()
        assert parsedJson.get("object_refs").get(1).asText() == report.getObjectRefs()[1].getObject().getId()
        assert parsedJson.get("object_refs").get(2).asText() == report.getObjectRefs()[2].getObject().getId()

        then: "Parse JSON back into Report Object and compare"
        Report parsedReport = Report.parse(reportJson)

        and: "Create relations that match the Parsed Relations"
        Relation parsedRelationAttkPattern = new Relation(attackPattern.getId())
        Relation parsedRelationIdentity = new Relation(identity.getId())
        Relation parsedRelationRelationship = new Relation(relationship.getId())

        assert parsedReport.getObjectRefs().size() == 3
        assert parsedReport.getObjectRefs().contains(parsedRelationAttkPattern)
        assert parsedReport.getObjectRefs().contains(parsedRelationIdentity)
        assert parsedReport.getObjectRefs().contains(parsedRelationRelationship)

        and: "each relation should not have a object"
        parsedReport.getObjectRefs().each {
            assert it.hasObject() == false
            assert it.getId() != null
        }

    }

}