# STIX Java library Notes

# Relation.class

The _Relation_ class is a wrapper class for _Stix Relationships Objects_ (SROs) (Relationship.class and Sighting.class).

Relation.class allows the wrapping of a String Id or a Bundleable Object. This means SDOs, SROs, and Data Markings 
(Marking Definitions).

When a object is converted into a JSON string, any relationships that are within the Java object 
(attributes that link to another object, whether a list of 1-1) are converted to a String ID.  
When a JSON string is parsed back into a Java Object, there is no guarantee that the parsed object will contain 
the other objects to complete the relationship chain.  Therefore the Relation.class wrapper is used.

When any Object is converted into JSON String, it will convert all attributes that are relations into the String ID 
of the related object.

When any  object is parsed from JSON into a Java object, all relation fields will be parsed into a Relation 
object that contains only the ID.
If you wish to "hydrate" the objects' relations with the pointers to the actual Objects, and thus make them navigable, 
you can use the "hydrate" method helpers found in each object.

# Object Hydration

The process of providing a list of bundleable objects (`<BundleObject>`) to which the object to whihc you call 
the hydration method on, will search through the list and create pointers to the real object and update the 
Relation Wrapper from being String ID to using the actual Object.
...

# Bundle Auto Population

The process of a bundle performing a recursive search on all objects in the bundle, and finding any nested objects 
that should be added into the bundle as "Bundle Objects".  This allows someone to generate STIX objects in a fluent 
and nested fashion, and not have to worry about remembering to add all objects into the bundle. 
...