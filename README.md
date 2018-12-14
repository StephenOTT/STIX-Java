# STIX 2.x Java library

The libary aims to provide a flexible full implementation of [STIX 2.x](https://oasis-open.github.io/cti-documentation/resources#stix-20-specification).  
This means that a default implementation is provided that meets the STIX JSON specification and the core objects 
and properties are provided in such a way as you can easily override and extend any implementation detail to meet your 
variation of the specification.

Current Spec Target: **2.0**

## Java

Example unit test showing usage.

```groovy
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.bundle.BundleObject
import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.common.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Malware
import io.digitalstate.stix.sro.objects.Relationship
import spock.lang.Specification

import java.time.Instant

class BundleSpec extends Specification {

    def "Basic 'uses' Relationship object and addition to bundle"(){
        when: "Create a Relationship with Attack Pattern and Malware"

        Relationship usesRel = Relationship.builder()
                .relationshipType("uses")
                .created(Instant.now())
                .sourceRef(AttackPattern.builder()
                        .name("Some Attack Pattern 1")
                        .build())
                .targetRef(Malware.builder()
                        .name("dog")
                        .addLabels("worm")
                        .build())
                .build()

        then: "print the JSON string version of the created relationship object"
        println usesRel.toJsonString()

        then: "parse the string back into a relationship object"
        BundleableObject parsedRelationship = StixParsers.parseObject(usesRel.toJsonString())
        assert parsedRelationship instanceof Relationship

        Relationship typedRelation = (Relationship)parsedRelationship

        and: "print the parsed relation"
        println typedRelation

        then: "ensure the original JSON matches the new JSON"
        assert usesRel.toJsonString() == typedRelation.toJsonString()

        then: "add the relationship into a bundle"
        Bundle bundle = Bundle.builder()
                .addObjects(usesRel)
                .build()

        and: "print the bundle json"
        println bundle.toJsonString()

        then: "parse json bundle back into object"
        BundleObject parsedBundle = StixParsers.parseBundle(bundle.toJsonString())
        assert parsedBundle instanceof Bundle
        Bundle typedBundle = (Bundle)parsedBundle

        then: "ensure original bundle and parsed bundles match in their json forms"
        assert bundle.toJsonString() == typedBundle.toJsonString()

    }

    def "bundleable object parsing"(){
        when: "setup parser and object"
        String attackPatternString = AttackPattern.builder()
                                        .name("Some Attack Pattern 1")
                                        .build().toJsonString()

        then: "can parse the json back into a attack Pattern"
        BundleableObject parsedAttackPatternBo = StixParsers.parseObject(attackPatternString)
        assert parsedAttackPatternBo instanceof AttackPattern

        AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseObject(attackPatternString)
        println parsedAttackPattern.toJsonString()
    }
}
```


## JSON

1. All objects (Bundles, SDO, SRO, and Marking Definitions; anything that is a "bundleable object" + bundle) are able 
to be individually converted into their json equivalent.

1. All objects (Bundles, SDO, SRO, and Marking Definitions; anything that is a "bundleable object" + bundle) can be 
individually parsed from Json into a object.

1. Object references within out objects (for example a "object_markings" property), will create "dehydrated" objects 
when parsing from json into objects.  This means that the object will detect the "type" based on the Id value, 
extract the type, and create a object of the specified type with the "hydrated" attribute marked as false.


# Charon Data Flow

![Charon data flow](./docs/Diagrams/Generic-Data-Flow.png)

# Workflow / BPM / BPMN

## Example Process Usage

![course of action processing 1](./docs/BPMN/course_of_action_1.png)

![taxii collection updates](./docs/BPMN/taxii_collection_propagation.png)

![process example 1](./docs/BPMN/sample_processes_1.png)

![Report Review Process 1](./docs/BPMN/report_review.png)

![Translation Process](./docs/BPMN/stix_data_translation.png)

![Cyber Observable Processing](./docs/BPMN/cyber-observable-processing-1.png)

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
    