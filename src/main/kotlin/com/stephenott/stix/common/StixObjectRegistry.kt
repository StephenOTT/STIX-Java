package com.stephenott.stix.common

import com.stephenott.stix.objects.StixObject
import com.stephenott.stix.objects.core.sco.objects.*
import com.stephenott.stix.objects.core.sdo.objects.*
import com.stephenott.stix.type.StixType
import kotlin.reflect.KClass

object StixObjectRegistry {

    var sdoRegistry: Map<StixType, KClass<out StixObject>> = mutableMapOf(
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

    var scoRegistry: Map<StixType, KClass<out StixObject>> = mutableMapOf(
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

    var registry: Map<StixType, KClass<out StixObject>> = sdoRegistry + scoRegistry
}