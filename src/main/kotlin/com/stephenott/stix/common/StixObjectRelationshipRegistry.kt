package com.stephenott.stix.common

import com.stephenott.stix.objects.core.sdo.objects.*
import com.stephenott.stix.objects.core.sro.objects.AllowedRelationship
import com.stephenott.stix.objects.core.sro.objects.RelationshipSro

class StixObjectRelationshipRegistry() {

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