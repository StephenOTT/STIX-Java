package com.stephenott.stix.common

import com.stephenott.stix.`object`.sdo.`object`.*
import com.stephenott.stix.`object`.sro.`object`.AllowedRelationship
import com.stephenott.stix.`object`.sro.`object`.RelationshipSro

object StixObjectRelationshipRegistry {

    var registry: List<AllowedRelationship> =
        RelationshipSro.allowedCommonRelationships +
                AttackPatternSdo.allowedRelationships +
                CampaignSdo.allowedRelationships +
                CourseOfActionSdo.allowedRelationships +
                GroupingSdo.allowedRelationships +
                IdentitySdo.allowedRelationships +
                IndicatorSdo.allowedRelationships +
                InfrastructureSdo.allowedRelationships +
                IntrusionSetSdo.allowedRelationships +
                LocationSdo.allowedRelationships +
                MalwareSdo.allowedRelationships +
                MalwareAnalysisSdo.allowedRelationships +
                NoteSdo.allowedRelationships +
                ObservedDataSdo.allowedRelationships +
                OpinionSdo.allowedRelationships +
                ReportSdo.allowedRelationships +
                ThreatActorSdo.allowedRelationships +
                ToolSdo.allowedRelationships +
                VulnerabilitySdo.allowedRelationships

}