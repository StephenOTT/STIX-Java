# STIX-JAVA-KOTLIN

A STIX 2.1 library built in Kotlin with targets of Kotlin and Java.

## Concepts

The entire STIX 2.1 spec covered in interfaces and implementations.  
This allows for anyone to build new implementations from the common interfaces that map out the entire STIX 2.1 spec.  
All business rules can be found in the interfaces such as supported relationships, supported extensions, and field level controls.  

The interfaces maintain the hierarchy of the STIX spec:

1. Stix Content
  1.1 Stix Object
     1.1.1 Stix Core Object
        1.1.1.1 Stix Domain Object (SDO)
        1.1.1.2 Stix Relationship Object (SRO)
        1.1.1.3 Stix Cyber Observable Object (SCO)
     1.1.2 Stix Meta Object
        1.1.2.1 Data Marking
        1.1.2.2 Language Content Object
  1.2. Stix Bundle
  
### Technical design details

1. All Stix Content is immutable.
1. Stix Content can be created using the default spec as its configuration, but using the `Stix` instance you can transform th object to using the `Stix` instance's configuration.
  
  
## `Sitx` instance

A `Stix` class instance provides the ability to create instance of a Stix spec configuration.  
A Stix spec configuration provides the ability to configure the rules and configurations for the specific trust group.

Example:  A system you are developing only accepts the Indicator SDO.  When creating a `Stix` instance you can disable 
all other objects except for the Indicator SDO.  When you generate a JSON content mapper and attempt to pass another object 
other than the Indicator SDO, the parser will throw an error.  The default JSON content mapper that this library provides, 
allows you to create a JSON content mapper from the `Stix` instance.

`Stix` instances are applied to object creation.  There is a default `Stix` instance available as a static object that represents a spec matching configuration.

A `Stix` instance provides a `create(...)` method that takes a StixContent value.  This value will be shallow copied and have the business rules validation run against the calling `Stix` instance. 

## JSON Serialization

Example:

```kotlin
val stix1 = Stix()

val ap = AttackPattern(name = "124", confidence = StixConfidence(33))

val ap1: AttackPattern = stix1.create(AttackPattern(name = "124"))

val ap2: AttackPattern = stix1.create(AttackPattern(name = "124"))

val mapper = stix1.toJsonMapper()
// val mapper = StixJsonContentMapper.fromStixInstance(stix1)

// Note that any of the object implementations or any of the interfaces in the hierarchy can be used.
// The parser will use the polymorphic support to detect what is the proper class to return.
 
mapper.parseJson<AttackPattern>(mapper.toJson(ap1)) // You can use the mapper to convert a object to json
mapper.parseJson<AttackPatternSdo>(mapper.toJson(ap1))
mapper.parseJson<AttackPatternSdo>(ap1.toJson(mapper)) // A extension provides StixContent and StixBundle will a toJson(mapper) method
mapper.parseJson<StixDomainObject>(ap1.toJson(mapper))
mapper.parseJson<StixCoreObject>(ap1.toJson(mapper))
mapper.parseJson<StixObject>(ap1.toJson(mapper))
mapper.parseJson<StixContent>(ap1.toJson(mapper))


val rel = Relationship(
    relationshipType = RelationshipType("duplicate-of"),
    sourceRef = ap1,
    targetRef = ap2
)
mapper.parseJson<StixContent>(rel.toJson(mapper))
mapper.parseJson<StixObject>(rel.toJson(mapper))
mapper.parseJson<StixCoreObject>(rel.toJson(mapper))
mapper.parseJson<StixRelationshipObject>(rel.toJson(mapper))
mapper.parseJson<RelationshipSro>(rel.toJson(mapper))
mapper.parseJson<Relationship>(rel.toJson(mapper))


```


## Object Validation

All Stix Content has a "stixValidateOnConstruction" parameter used to indicate if business rule validation should be executed during object creation.

When you use `stixInstance.create(...)` it will force business rule validation.