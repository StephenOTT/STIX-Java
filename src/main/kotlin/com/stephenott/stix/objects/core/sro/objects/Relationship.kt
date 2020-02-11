package com.stephenott.stix.objects.core.sro.objects

import com.stephenott.stix.Stix
import com.stephenott.stix.common.*
import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.sro.StixRelationshipObject
import com.stephenott.stix.type.*
import java.lang.IllegalStateException
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

interface RelationshipSro : StixRelationshipObject {
    val relationshipType: RelationshipType
    val description: String?
    val sourceRef: StixIdentifier
    val targetRef: StixIdentifier
    val startTime: StixTimestamp?
    val stopTime: StixTimestamp?

    companion object : CompanionStixType,
            BusinessRulesValidator<RelationshipSro> {

        override val stixType = StixType("relationship")

        override fun objectValidationRules(obj: RelationshipSro, stixInstance: Stix) {
            requireStixType(this.stixType, obj)

        }

        val allowedCommonRelationships: List<AllowedRelationship> = listOf(
                AllowedRelationship(
                        StixObject::class,
                        RelationshipType("duplicate-of"),
                        StixObject::class,
                        RelationshipRule { obj ->
                            require(obj.sourceRef.type == obj.targetRef.type,
                                    lazyMessage = { "duplicate-of relationship (${obj.id}) requires source(${obj.sourceRef}) and target(${obj.targetRef}) must be same type" })
                        }
                ),
                AllowedRelationship(
                        StixObject::class,
                        RelationshipType("derived-from"),
                        StixObject::class,
                        RelationshipRule { obj ->
                            require(obj.sourceRef.type == obj.targetRef.type,
                                    lazyMessage = { "derived-from relationship (${obj.id}) requires source(${obj.sourceRef}) and target(${obj.targetRef}) must be same type" })
                        }
                ),
                AllowedRelationship(
                        StixObject::class,
                        RelationshipType("related-to"),
                        StixObject::class
                )
        )
    }
}

data class RelationshipRule(private val rule: (relObj: RelationshipSro) -> Unit) {
    fun execute(relObj: RelationshipSro) {
        rule(relObj)
    }
}

data class AllowedRelationship(
        val from: KClass<out StixObject>,
        val type: RelationshipType,
        val to: KClass<out StixObject>,
        val rule: RelationshipRule? = null
) {}

data class Relationship(
        override val relationshipType: RelationshipType,
        override val description: String? = null,
        override val sourceRef: StixIdentifier,
        override val targetRef: StixIdentifier,
        override val startTime: StixTimestamp? = null,
        override val stopTime: StixTimestamp? = null,
        override val type: StixType = RelationshipSro.stixType,
        override val id: StixIdentifier = StixIdentifier(type),
        override val createdByRef: String? = null,
        override val created: StixTimestamp = StixTimestamp(),
        override val externalReferences: ExternalReferences? = null,
        override val objectMarkingsRefs: String? = null,
        override val granularMarkings: String? = null,
        override val specVersion: StixSpecVersion = StixSpecVersion(),
        override val labels: StixLabels? = null,
        override val modified: StixTimestamp = StixTimestamp(created),
        override val revoked: StixBoolean = StixBoolean(),
        override val stixInstance: Stix = Stix.defaultStixInstance,
        override val stixValidateOnConstruction: Boolean = Stix.defaultValidateOnConstruction
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
            startTime: StixTimestamp? = null,
            stopTime: StixTimestamp? = null,
            type: StixType = RelationshipSro.stixType,
            id: StixIdentifier = StixIdentifier(type),
            createdByRef: String? = null,
            created: StixTimestamp = StixTimestamp(),
            externalReferences: ExternalReferences? = null,
            objectMarkingsRefs: String? = null,
            granularMarkings: String? = null,
            specVersion: StixSpecVersion = StixSpecVersion(),
            labels: StixLabels? = null,
            modified: StixTimestamp = StixTimestamp(created),
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
        if (this.stixValidateOnConstruction) {
            val sourceClass: KClass<out StixObject> = this.stixInstance.registries.objectRegistry.registry[sourceRef.type]
                    ?: throw IllegalStateException("Unable to find sourceRef in Object Registry")

            val targetClass: KClass<out StixObject> = this.stixInstance.registries.objectRegistry.registry[targetRef.type]
                    ?: throw IllegalStateException("Unable to find targetRef in Object Registry")


            //@TODO add support for x- custom objects
            val allowedRelationships: List<AllowedRelationship> = this.stixInstance.registries.relationshipRegistry
                    .registry.filter {
                sourceClass.isSubclassOf(it.from) &&
                        it.type == this.relationshipType &&
                        targetClass.isSubclassOf(it.to)
            }

            if (allowedRelationships.size > 1) {
                println("Duplicate relationships found: $allowedRelationships")
                //@TODO add some logging for a warning to indicate multiple duplication objects are registered
            }

            require(allowedRelationships.isNotEmpty(),
                    lazyMessage = { "${this.id} is not a valid relationship for a ${this.sourceRef.type}" }
            )

            //@TODO To a deeper test of this functionality
            allowedRelationships[0].rule?.execute(this)
//            allowedRelationships[0].rule?.execute(this)
        }
    }

}