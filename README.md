# Charon

:exclamation: THIS IS A ACTIVE WORK IN PROGRESS: NOT PRODUCTION READY :exclamation:

# STIX 2.x Java Library

The libary aims to provide a flexible full implementation of [STIX 2.x](https://oasis-open.github.io/cti-documentation/resources#stix-20-specification).  
This means that a default implementation is provided that meets the STIX JSON specification and the core objects 
and properties are provided in such a way as you can easily override and extend any implementation detail to meet your 
variation of the specification.

Current Spec Target: **2.0**

## Java

Here is a Unit Test for the Report SDO
It shows a End to End testing from Creating a Object in Java, and then converting that object into JSON, and then parsing that Json back into a Report Java Object.

This test shows some of the granular methods to create objects.  Objects typically have helper constructors and methods to eliminate the manual creation of some of the intermediate objects (such as Relation.class objects)

```groovy
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
```

The json generated by the object would be:

```json
{
  "type": "report",
  "id": "report--d0c4a031-6563-425d-8941-366c3f89874c",
  "created": "2018-11-30T18:20:24.021Z",
  "modified": "2018-11-30T18:20:24.021Z",
  "revoked": false,
  "labels": [
    "threat-report"
  ],
  "name": "Some Report",
  "published": "2018-11-30T18:20:23.528Z",
  "object_refs": [
    "attack-pattern--0edcddd5-1686-43a4-a312-9a95deb888a0",
    "identity--2e24cf89-eb91-46eb-b6e5-86bf10061261",
    "relationship--41321e93-c244-41a6-8b40-cd11fc4e98e7"
  ]
}
```

All Objects have support for `.toJsonString()`; allowing any STIX related object to be individually exported into JSON.

### Special Methods

There are several special methods that enable helpers:


`someBundleableObject.toJsonString()` This method allows any object to be converted into a JSON string, including a bundle.

`AttackPattern someAttackPattern = AttackPattern.parse(someJsonString)` This method can be used on any object to parse its Json, including Bundles.

`someAttackPattern.hydrateRelationsWithObjects(LinkedHashSet<BundleObject> bundleObjects)` This method will take a list of bundleObjects and will hydrate a object's relation fields with the objects in the provided list.  This enables the recreation of the tree of relationships within the Java objects.

`somebundle.autoDectectObjectProcessor()` this method will search through the objects in a bundle, and find all of the inner BundleObjects that you may not have manually added into the bundle; for each object it finds it will add it into the bundle.  This saves you from having to manually add all objects into a bundle. 


## JSON
JSON can be outputted for any individual object as well as a Bundle which will perform recursive conversion to JSON for each nested object.

The below is a the output from a full bundle with a typical stack of objects

```json
{
  "type": "bundle",
  "id": "bundle--7a0fe49d-4569-4e9e-87e1-a81231fd72fb",
  "spec_version": "2.0",
  "objects": [
    {
      "type": "attack-pattern",
      "id": "attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6",
      "created": "2018-11-26T01:17:26.416Z",
      "modified": "2018-11-29T01:17:26.416Z",
      "revoked": false,
      "object_marking_refs": [
        "marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50"
      ],
      "granular_markings": [
        {
          "selectors": [
            "pattern1",
            "pattern2",
            "pattern3"
          ],
          "marking_ref": "marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689"
        }
      ],
      "name": "some pattern",
      "kill_chain_phases": [
        {
          "kill_chain_name": "Chain1",
          "phase_name": "phase1"
        },
        {
          "kill_chain_name": "Chain1",
          "phase_name": "phase2"
        }
      ],
      "x_someCustomKey": "My custom value",
      "x_someOtherCustom_key": 3939
    },
    {
      "type": "observed-data",
      "id": "observed-data--79ec3f75-6fbf-4fe7-b7d0-c540fcf65753",
      "created": "2018-11-26T01:17:26.461Z",
      "modified": "2018-11-26T01:17:26.461Z",
      "revoked": false,
      "object_marking_refs": [
        "marking-definition--a2eb976a-aa5c-4999-8c34-0b74a0d46bef"
      ],
      "first_observed": "2018-11-26T01:17:26.448Z",
      "last_observed": "2018-11-26T01:17:26.448Z",
      "number_observed": 3,
      "objects": {
        "some artifact": {
          "type": "artifact",
          "url": "someURL"
        },
        "some AS": {
          "type": "autonomous-system",
          "number": 5,
          "rir": "someRIR"
        }
      }
    },
    {
      "type": "sighting",
      "id": "sighting--cbae7c1e-57ef-47b4-9e50-22d9169c246d",
      "created": "2018-11-26T01:17:26.941Z",
      "modified": "2018-11-26T01:17:26.941Z",
      "revoked": false,
      "sighting_of_ref": "attack-pattern--4c465e41-3c02-4667-8887-c4608e6d3ae9",
      "where_sighted_refs": [
        "identity--d442813b-7e72-49a6-937a-3e351e219a18"
      ],
      "summary": false
    },
    {
      "type": "marking-definition",
      "id": "marking-definition--b17db0c7-1c2e-4c6a-9cab-34d0cacfbf50",
      "created": "2018-11-26T01:17:26.435Z",
      "object_marking_refs": [
        "marking-definition--a2eb976a-aa5c-4999-8c34-0b74a0d46bef"
      ],
      "definition_type": "tlp",
      "definition": {
        "tlp": "white"
      }
    },
    {
      "type": "marking-definition",
      "id": "marking-definition--a2eb976a-aa5c-4999-8c34-0b74a0d46bef",
      "created": "2018-11-26T01:17:26.444Z",
      "granular_markings": [
        {
          "selectors": [
            "marking-pattern1",
            "pattern2",
            "pattern3"
          ],
          "marking_ref": "marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689"
        }
      ],
      "definition_type": "statement",
      "definition": {
        "statement": "Internal review of data allows for sharing as per ABC-009 Standard"
      }
    },
    {
      "type": "marking-definition",
      "id": "marking-definition--0f1a0afd-ba25-47a2-b7e1-4d0ab65b1689",
      "created": "2018-11-26T01:17:26.436Z",
      "definition_type": "tlp",
      "definition": {
        "tlp": "red"
      }
    },
    {
      "type": "relationship",
      "id": "relationship--40c2a4e3-e5cc-4c8a-98fa-9c411a749beb",
      "created": "2018-11-26T01:17:26.933Z",
      "modified": "2018-11-26T01:17:26.933Z",
      "revoked": false,
      "relationship_type": "targets",
      "source_ref": "attack-pattern--477b763e-226f-46b3-a211-3cb5b86978a6",
      "target_ref": "identity--d442813b-7e72-49a6-937a-3e351e219a18"
    },
    {
      "type": "attack-pattern",
      "id": "attack-pattern--4c465e41-3c02-4667-8887-c4608e6d3ae9",
      "created": "2018-11-26T01:17:26.941Z",
      "modified": "2018-11-26T01:17:26.941Z",
      "revoked": false,
      "name": "someOtherATTK2"
    },
    {
      "type": "identity",
      "id": "identity--d442813b-7e72-49a6-937a-3e351e219a18",
      "created": "2018-11-26T01:17:26.929Z",
      "modified": "2018-11-26T01:17:26.929Z",
      "revoked": false,
      "name": "Stephen",
      "identity_class": "individual"
    }
  ]
}
```

# Charon Data Flow

![Charon data flow](./docs/Diagrams/Generic-Data-Flow.png)

# Workflow / BPM / BPMN

## Example Process Usage

![process example 1](./docs/BPMN/sample_processes_1.png)

![Report Review Process 1](./docs/BPMN/report_review.png)

![Translation Process](./docs/BPMN/stix_data_translation.png)


-----

# Raw notes

This project is a packaging of multiple components that will be split into individual projects at some point.

1. A full [CTI STIX](https://oasis-open.github.io/cti-documentation/) (Structured Threat Information Expression Language) Java8 implementation. (`io.digitalstate.stix`).  The goal is for flexibility and reuse by other Java projects.
1. Charon Application ("Charon Server"):
    1. A SpringBoot based instance of [Camunda BPM](https://docs.camunda.org/manual/7.9/)
    1. Possible: A [TAXII](https://oasis-open.github.io/cti-documentation/taxii/intro) server implemented in SpringBoot, tied to the Camunda BPM SpringBoot instance.
    1. A bridge of CTI STIX with Camunda BPM BPMN Java-Delegates and Scripting Usage.
    1. Possible: Mongo Spring Data Mapping from the STIX Java8 implementation to Mongo Documents.
    1. Possible: Additional REST APIs in addition to the TAXII API that provide missing functionality that is available from using Camunda.
    1. Monitoring of BPMN and STIX data usage with [Prometheus Camunda Plugin](https://github.com/StephenOTT/camunda-prometheus-process-engine-plugin) 
    

**Overall Concept:** Enable organizations working with CTI and STIX to have the "application" 
(Charon) easily adapt to the organizations internal or standardized security processes.  
An organization should not have to change their security models for a "vendor".

Further Notes:

By Using STIX as a common language for CTI, automated and manual workflows can be created for multi-system use:
Where each CTI system can generate STIX data and the downstream workflows can process the CTI for whatever purpose 
without the need to understand the upstream CTI system (whether it be new and shinny, old and legacy, or 
some customized CTI DB that has little understanding outside of the specific domain-usage).

Leverage CTI's Who, Why, Where, How, What data to process data into meaningful automation and iterate as needed with the Charon engine.
When you need manual intervention, with human eyes and hands, you can continue to leverage Charon to process those human tasks.

 - Process Information
 - Detection
 - Artifact storage and processing
 - Analyze
 - Respond
 
 
 Share CTI across the CTI network with the common exchange format, and enrich the data with STIX Data Enrichment from downstream enrichment processes.
 
 "Automation Engine" with OSS.
 
 Initiate Translation Services through the automation: When translation of CTI is required, it can be packaged as a 
 STIX bundle and processed into a workflow for processing: automatically or with human intervention.


Share Workflows (Shareable Workflows) across organizations and within the organization.  Allowing standardization regardless of the specific engine being used.

Monitor and Track real-time events from your upstream incident response system, and process those events in STIX for 
downstream CTI event processing by actors that may not have access or want access to the upstream incident response system.

Integration ML and AI at any point within the automation.  Do not tightly couple the ML and AI into the workflows: 
This allows easy swapping of ML and AI technologies, products, and innovations as a underlying capability / 
force multiplier without having to integration "a whole other system".

Enable third-party organizations to deploy the same engine within your network, collect data, 
and stream it upstream to central CTI processors.  Enable the third-parties to implement their specific CTI processing 
processes directly in the engine without the need to "conform to how the upstream system wants to do things".
Then when the upstream system receives the STIX CTI, it can be processed based on your knowledge of that third-party 
systems processes: to which you can have transparency into with reviewing their "processes" defined in the engine.

Implement organization HR conditions into the workflows: If someone does not response or review in X time, if someone is on holidays, etc

Push out IOCs and Attack patterns to downstream consumers: Consumers can establish business rules that outline their 
systems and networks, and as IOCs and APs are pushed, they can be evaluated automatically (and/or manually) 
by the downstream consumer system.
Same can be applied to Abnormal Behaviour reporting or Pattern awareness.

Data Interruption reporting: Reporting in standard formats for org, and having to consume into STIX.

Use the same engine to automate CTI testing and python scripts.  Engine can be wrapped up into a single JAR, including DB.
Can be used as a CLI tool as well.

Measure the impacts of CTI and the various STIX data being received.

Use the STIX stream as auditing trail capability: being able to collect STIX events and cross those 
events based on aggregation, counts over period of time, etc. Then generate a Request for review based on the conditions.

Very easy to tie in ML for Event data: Such as Kibana xPack reviewing the various types of 
data and detecting new patterns such as counts and increases in periods or locations.

Remove the problem of "Lack of Time to implement processes" (reported as 43% of the time).  
Champions can create common processes to be easily and quickly adopted by downstream groups.



Example Use Cases:
1. STIX Bureaucracy Management using Charon.
1. Security Approvals and Revocations for STIX data exposed through TAXII.
1. Managing TAXII "Hubs" / Central Databases and the review, adjustements, and approvals of submissions of data into the Charon / STIX DB.
1. Life-cycling STIX data through BPMN.
1. Managing TAXII / STIX data access requests, apply-and-removal of STIXX markings.
1. Multi-Department STIX Data Isolation with STIX data submission to UpStream STIX datasource.
1. Execute automated and manual workflows based on STIX data events (CRUD events in STIX data). 
1. Concept of "Shareable Workflows":
    1. Ability to share common business and technical workflows across a single organization and across many organiztions.
    1. Ability to create Executable Workflows based on data events within STIX data.
    