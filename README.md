# Charon

:exclamation: THIS IS A ACTIVE WORK IN PROGRESS: NOT PRODUCTION READY :exclamation:

# STIX Java Library

The libary aims to provide a flexible full implementation of [STIX](https://oasis-open.github.io/cti-documentation/resources#stix-20-specification).  
This means that a default implementation is provided that meets the STIX JSON specification and the core objects 
and properties are provided in such a way as you can easily override any implementation detail to meet your 
variation of the specification.

## Usage

There are two primary purposes of usage: 
1. create STIX Java Objects (Through code or conversion from a string of STIX JSON) and,
2. convert STIX Java objects into JSON that is STIX Spec compliant.

## Java

```java
AttackPattern attackPattern = new AttackPattern("some pattern");
attackPattern.setKillChainPhases(
        new KillChainPhase("Chain1", "phase1"),
        new KillChainPhase("Chain1", "phase2"));

attackPattern.setModified(attackPattern.getCreated().plusDays(3));

MarkingDefinition markingDefinition = new MarkingDefinition(
        new TlpMarking("white"));

attackPattern.addObjectMarkingRefs(markingDefinition);

MarkingDefinition statement1 = new MarkingDefinition(
        new StatementMarking("Internal review of data allows for sharing as per ABC-009 Standard"));

markingDefinition.addObjectMarkingRefs(statement1);

ZonedDateTime observedTime = ZonedDateTime.now();
ObservedData observedData = new ObservedData(observedTime, observedTime, 3,
        new Artifact(){{
            setUrl("someURL");
        }},
        new AutonomousSystem(3){{
            setRir("someRIR");
        }});
observedData.addObjectMarkingRefs(statement1);

Bundle bundle = new Bundle(attackPattern);

bundle.addObjects(observedData);

bundle.autoAddDataMarkingsToBundle();

bundle.toJsonString();
```

## JSON

The below is a the output from the java example above.

```json
{
  "type": "bundle",
  "id": "bundle--0fcdfa2c-18e0-4f43-8083-b2c3db71ee1d",
  "objects": [
    {
      "type": "attack-pattern",
      "id": "attack-pattern--3bd7d7a8-a8cf-43d2-b7d2-2c18143aa690",
      "created": "2018-11-20T12:34:42.887-0500",
      "modified": "2018-11-23T12:34:42.887-0500",
      "revoked": false,
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
      "object_marking_refs": [
        "marking-definition--18dc13e0-a509-4766-a6b7-8f6422f410d7"
      ]
    },
    {
      "type": "observed-data",
      "id": "observed-data--811ef6c8-4c1d-4d1c-a0db-a6f5f0d5834d",
      "created": "2018-11-20T12:34:42.929-0500",
      "modified": "2018-11-20T12:34:42.929-0500",
      "revoked": false,
      "objects": [
        {
          "type": "artifact",
          "url": "someURL"
        },
        {
          "type": "autonomous-system",
          "number": 3,
          "rir": "someRIR"
        }
      ],
      "first_observed": "2018-11-20T12:34:42.918-0500",
      "last_observed": "2018-11-20T12:34:42.918-0500",
      "number_observed": 3,
      "object_marking_refs": [
        "marking-definition--2f6f7e50-ec7c-41c6-82de-28dc87878938"
      ]
    },
    {
      "type": "marking-definition",
      "id": "marking-definition--18dc13e0-a509-4766-a6b7-8f6422f410d7",
      "created": "2018-11-20T12:34:42.916-0500",
      "definition": {
        "tlp": "white"
      },
      "definition_type": "tlp",
      "object_marking_refs": [
        "marking-definition--2f6f7e50-ec7c-41c6-82de-28dc87878938"
      ]
    },
    {
      "type": "marking-definition",
      "id": "marking-definition--2f6f7e50-ec7c-41c6-82de-28dc87878938",
      "created": "2018-11-20T12:34:42.918-0500",
      "definition": {
        "statement": "Internal review of data allows for sharing as per ABC-009 Standard"
      },
      "definition_type": "statement"
    }
  ],
  "spec_version": "2.0"
}
```

# Workflow / BPM / BPMN

## Example Process Usage

![process example 1](./docs/BPMN/sample_processes_1.png)


-----

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
    