package com.stephenott.stix.`object`.sro.`object`

import com.stephenott.stix.`object`.StixObject
import com.stephenott.stix.`object`.sro.StixRelationshipObject
import com.stephenott.stix.common.StixObjectRegistry
import com.stephenott.stix.common.StixObjectRelationshipRegistry
import com.stephenott.stix.type.*
import java.lang.IllegalStateException
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

interface RelationshipSro : StixRelationshipObject {
    val relationshipType: RelationshipType
    val description: String?
    val sourceRef: StixIdentifier
    val targetRef: StixIdentifier
    val startTime: StixInstant?
    val stopTime: StixInstant?

    companion object {
        val stixType = StixType("relationship")

        val allowedCommonRelationships: List<AllowedRelationship> = listOf(
            AllowedRelationship(
                StixObject::class,
                RelationshipType("duplicate-of"),
                StixObject::class
            ),
            AllowedRelationship(
                StixObject::class,
                RelationshipType("derived-from"),
                StixObject::class
            ),
            AllowedRelationship(
                StixObject::class,
                RelationshipType("related-to"),
                StixObject::class
            )
        )
    }
}

data class AllowedRelationship(
    val from: KClass<out StixObject>,
    val type: RelationshipType,
    val to: KClass<out StixObject>
) {}

data class Relationship(
    override val relationshipType: RelationshipType,
    override val description: String? = null,
    override val sourceRef: StixIdentifier,
    override val targetRef: StixIdentifier,
    override val startTime: StixInstant? = null,
    override val stopTime: StixInstant? = null,
    override val type: StixType = RelationshipSro.stixType,
    override val id: StixIdentifier = StixIdentifier(type),
    override val createdByRef: String? = null,
    override val created: StixInstant = StixInstant(),
    override val externalReferences: ExternalReferences? = null,
    override val objectMarkingsRefs: String? = null,
    override val granularMarkings: String? = null,
    override val specVersion: StixSpecVersion = StixSpecVersion(),
    override val labels: StixLabels? = null,
    override val modified: StixInstant = StixInstant(created),
    override val revoked: StixBoolean = StixBoolean()
) : RelationshipSro {

    /**
     * No Relationships are supported for RelationshipSro
     */
    override fun allowedRelationships(): List<AllowedRelationship> {
        return listOf()
    }

    constructor(
        relationshipType: RelationshipType,
        description: String? = null,
        sourceRef: StixObject,
        targetRef: StixObject,
        startTime: StixInstant? = null,
        stopTime: StixInstant? = null,
        type: StixType = RelationshipSro.stixType,
        id: StixIdentifier = StixIdentifier(type),
        createdByRef: String? = null,
        created: StixInstant = StixInstant(),
        externalReferences: ExternalReferences? = null,
        objectMarkingsRefs: String? = null,
        granularMarkings: String? = null,
        specVersion: StixSpecVersion = StixSpecVersion(),
        labels: StixLabels? = null,
        modified: StixInstant = StixInstant(created),
        revoked: StixBoolean = StixBoolean()
    ) : this(
        relationshipType, description, sourceRef.id,
        targetRef.id, startTime, stopTime,
        type, id, createdByRef,
        created, externalReferences, objectMarkingsRefs,
        granularMarkings, specVersion, labels,
        modified, revoked
    )


    init {
        val sourceClass: KClass<out StixObject> = StixObjectRegistry.registry[sourceRef.type]
            ?: throw IllegalStateException("Unable to find sourceRef in Object Registry")

        val targetClass: KClass<out StixObject> = StixObjectRegistry.registry[targetRef.type]
            ?: throw IllegalStateException("Unable to find targetRef in Object Registry")

        //@TODO add support for x- custom objects
        val allowedRelationships: List<AllowedRelationship> = StixObjectRelationshipRegistry
            .registry.filter { sourceClass.isSubclassOf(it.from) &&
                    it.type == this.relationshipType &&
                    targetClass.isSubclassOf(it.to)
            }

        if (allowedRelationships.size > 1){
            println("Duplicate relationships found: $allowedRelationships")
            //@TODO add some logging for a warning to indicate multiple duplication objects are registered
        }

        require(allowedRelationships.isNotEmpty(), lazyMessage = {
            "${this.id} is not a valid relationship for a ${this.sourceRef.type}"
        })
    }

}