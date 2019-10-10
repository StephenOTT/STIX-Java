package com.stephenott.stix.common

import com.stephenott.stix.`object`.StixObject
import com.stephenott.stix.`object`.sdo.`object`.*
import com.stephenott.stix.type.StixType
import kotlin.reflect.KClass

object StixObjectRegistry {

    var registry: Map<StixType, KClass<out StixObject>> = mutableMapOf(
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
}