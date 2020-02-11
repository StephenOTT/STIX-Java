package com.stephenott.stix.common

import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.sco.StixCyberObservableObject
import com.stephenott.stix.objects.core.sco.objects.*
import com.stephenott.stix.objects.core.sdo.StixDomainObject
import com.stephenott.stix.objects.core.sdo.objects.*
import com.stephenott.stix.objects.core.sro.StixRelationshipObject
import com.stephenott.stix.objects.core.sro.objects.Relationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro
import com.stephenott.stix.objects.core.sro.objects.Sighting
import com.stephenott.stix.objects.core.sro.objects.SightingSro
import com.stephenott.stix.objects.meta.StixMetaObject
import com.stephenott.stix.objects.meta.datamarking.MarkingDefinition
import com.stephenott.stix.objects.meta.datamarking.MarkingDefinitionDm
import com.stephenott.stix.objects.meta.datamarking.MarkingObject
import com.stephenott.stix.objects.meta.datamarking.objects.Statement
import com.stephenott.stix.objects.meta.datamarking.objects.StatementMo
import com.stephenott.stix.objects.meta.datamarking.objects.Tlp
import com.stephenott.stix.objects.meta.datamarking.objects.TlpMo
import com.stephenott.stix.objects.meta.lco.objects.LanguageContent
import com.stephenott.stix.objects.meta.lco.objects.LanguageContentLco
import com.stephenott.stix.type.StixType
import com.stephenott.stix.type.vocab.MarkingDefinitionTypeOv
import kotlin.reflect.KClass

data class StixObjectRegistry(
    val customSdoRegistry: Map<StixType, KClass<out StixDomainObject>> = mapOf(),
    val customScoRegistry: Map<StixType, KClass<out StixCyberObservableObject>> = mapOf(),
    val customSroRegistry: Map<StixType, KClass<out StixRelationshipObject>> = mapOf(),
    val customMetaObjectRegistry: Map<StixType, KClass<out StixMetaObject>> = mapOf(),
    val customMarkingObjectRegistry: Map<MarkingDefinitionTypeOv, KClass<out MarkingObject>> = mapOf()
) {

    // STIX OBJECT Registry:

    val sdoRegistry: Map<StixType, KClass<out StixDomainObject>> = mapOf(
        Pair(AttackPatternSdo.stixType, AttackPattern::class),
        Pair(CampaignSdo.stixType, Campaign::class),
        Pair(CourseOfActionSdo.stixType, CourseOfAction::class),
        Pair(GroupingSdo.stixType, Grouping::class),
        Pair(IdentitySdo.stixType, Identity::class),
        Pair(IndicatorSdo.stixType, Indicator::class),
        Pair(InfrastructureSdo.stixType, Infrastructure::class),
        Pair(IntrusionSetSdo.stixType, IntrusionSet::class),
        Pair(LocationSdo.stixType, Location::class),
        Pair(MalwareSdo.stixType, Malware::class),
        Pair(MalwareAnalysisSdo.stixType, MalwareAnalysis::class),
        Pair(NoteSdo.stixType, Note::class),
        Pair(ObservedDataSdo.stixType, ObservedData::class),
        Pair(OpinionSdo.stixType, Opinion::class),
        Pair(ReportSdo.stixType, Report::class),
        Pair(ThreatActorSdo.stixType, ThreatActor::class),
        Pair(VulnerabilitySdo.stixType, Vulnerability::class)
    )

    val scoRegistry: Map<StixType, KClass<out StixCyberObservableObject>> = mapOf(
        Pair(ArtifactSco.stixType, Artifact::class),
        Pair(AutonomousSystemSco.stixType, AutonomousSystem::class),
        Pair(DirectorySco.stixType, Directory::class),
        Pair(DomainNameSco.stixType, DomainName::class),
        Pair(EmailAddressSco.stixType, EmailAddress::class),
        Pair(EmailMessageSco.stixType, EmailMessage::class),
        Pair(FileSco.stixType, File::class),
        Pair(IPv4AddressSco.stixType, IPv4Address::class),
        Pair(IPv6AddressSco.stixType, IPv6Address::class),
        Pair(MacAddressSco.stixType, MacAddress::class),
        Pair(MutexSco.stixType, Mutex::class),
        Pair(NetworkTrafficSco.stixType, NetworkTraffic::class),
        Pair(ProcessSco.stixType, Process::class),
        Pair(SoftwareSco.stixType, Software::class),
        Pair(UrlSco.stixType, Url::class),
        Pair(UserAccountSco.stixType, UserAccount::class),
        Pair(WindowsRegistryKeySco.stixType, WindowsRegistryKey::class),
        Pair(X509CertificateSco.stixType, X509Certificate::class)
    )

    val sroRegistry: Map<StixType, KClass<out StixRelationshipObject>> = mapOf(
        Pair(RelationshipSro.stixType, Relationship::class),
        Pair(SightingSro.stixType, Sighting::class)
    )

    val metaObjectRegistry: Map<StixType, KClass<out StixMetaObject>> = mapOf(
        Pair(MarkingDefinitionDm.stixType, MarkingDefinition::class),
        Pair(LanguageContentLco.stixType, LanguageContent::class)
    )

    /**
     * Aggregate of the Stix Objects (spec defined and custom)
     */
    private fun aggregateObjects(): Map<StixType, KClass<out StixObject>> {
        val objects = mutableMapOf<StixType, KClass<out StixObject>>()
        objects.plusAssign(sdoRegistry)
        objects.plusAssign(scoRegistry)
        objects.plusAssign(sroRegistry)
        objects.plusAssign(metaObjectRegistry)
        objects.plusAssign(customSdoRegistry)
        objects.plusAssign(customScoRegistry)
        objects.plusAssign(customSroRegistry)
        objects.plusAssign(customMetaObjectRegistry)
        return objects
    }

    private var registryAggregate: Map<StixType, KClass<out StixObject>> = aggregateObjects()

    /**
     * Refreshes the Stix Object registry / regenerates the aggregated map of StixType and KClasses.
     * When you add new objects to the specific registries (SDO, SRO, Custom, etc)
     * you need to refresh for the values to become available.
     *
     * Typically used for polymorphic serialization/deserialization.
     *
     * The Refresh allows new objects to be added at runtime.
     */
    fun refreshRegistry(){
        registryAggregate = aggregateObjects()
    }

    /**
     * Master repository of all objects that are considered Stix Objects (Bundleable Objects) / tier 1 objects in a bundle.
     */
    val registry: Map<StixType, KClass<out StixObject>> = registryAggregate



    // MARKING OBJECT REGISTRY:

    val markingObjectRegistry: Map<MarkingDefinitionTypeOv, KClass<out MarkingObject>> = mapOf(
        Pair(TlpMo.definitionType, Tlp::class),
        Pair(StatementMo.definitionType, Statement::class)
    )

    private fun aggregateMarkingObjects(): Map<MarkingDefinitionTypeOv, KClass<out MarkingObject>> {
        val objects = mutableMapOf<MarkingDefinitionTypeOv, KClass<out MarkingObject>>()
        objects.plusAssign(markingObjectRegistry)
        objects.plusAssign(customMarkingObjectRegistry)

        return objects
    }

    private var markingObjectRegistryAggregate: Map<MarkingDefinitionTypeOv, KClass<out MarkingObject>> = aggregateMarkingObjects()

    /**
     * Refreshes the Marking Object registry
     *
     * Typically used for polymorphic serialization/deserialization.
     *
     * The Refresh allows new objects to be added at runtime.
     */
    fun refreshMarkingObjectRegistry(){
        markingObjectRegistryAggregate = aggregateMarkingObjects()
    }

}