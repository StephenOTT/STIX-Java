package com.stephenott.stix.type

class ExternalReferences(private val references: LinkedHashSet<ExternalReference>):
    Set<ExternalReference> by references

data class ExternalReference(
    val sourceName: String,
    val description: String?,
    val url: String?,
    val hashes: HashesDictionary?,
    val externalId: String?
) {
    init {
        //@TODO add biz logic for what fields can be populated
    }
}