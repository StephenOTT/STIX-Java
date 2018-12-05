package io.digitalstate.stix.sro.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.objects.*;
import io.digitalstate.stix.sro.RelationshipObject;
import io.digitalstate.stix.validation.contraints.relationship.RelationshipLimit;
import io.digitalstate.stix.validation.contraints.relationship.RelationshipTypeLimit;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.RelationshipTypes;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Value.Immutable
@Value.Style(typeImmutable = "Relationship", validationMethod = Value.Style.ValidationMethod.NONE)
@RelationshipTypeLimit(source = AttackPatternSdo.class, relationshipTypes = {"targets", "uses"})
@RelationshipTypeLimit(source = CampaignSdo.class, relationshipTypes = {"attributed-to", "targets", "uses"})
@RelationshipTypeLimit(source = CourseOfActionSdo.class, relationshipTypes = {"mitigates"})
@RelationshipTypeLimit(source = IdentitySdo.class, relationshipTypes = {"targets", "attributed-to", "impersonates"})
@RelationshipTypeLimit(source = IndicatorSdo.class, relationshipTypes = {"indicates"})
@RelationshipTypeLimit(source = IntrusionSetSdo.class, relationshipTypes = {"attributed-to", "targets", "uses"})
@RelationshipTypeLimit(source = MalwareSdo.class, relationshipTypes = {"targets", "uses", "variant-of"})
@RelationshipTypeLimit(source = ThreatActorSdo.class, relationshipTypes = {"attributed-to", "impersonates", "targets", "uses"})
@RelationshipTypeLimit(source = ToolSdo.class, relationshipTypes = {"targets"})
@RelationshipLimit(source = DomainObject.class, relationshipType = "derived-from", target = {DomainObject.class}, classEquality = true)
@RelationshipLimit(source = DomainObject.class, relationshipType = "duplicate-of", target = {DomainObject.class}, classEquality = true)
@RelationshipLimit(source = DomainObject.class, relationshipType = "related-to", target = {DomainObject.class})
@RelationshipLimit(source = AttackPatternSdo.class, relationshipType = "targets", target = {IdentitySdo.class, VulnerabilitySdo.class})
@RelationshipLimit(source = AttackPatternSdo.class, relationshipType = "uses", target = {MalwareSdo.class, ToolSdo.class})
@RelationshipLimit(source = CampaignSdo.class, relationshipType = "attributed-to", target = {IntrusionSetSdo.class, ThreatActorSdo.class})
@RelationshipLimit(source = CampaignSdo.class, relationshipType = "targets", target = {IdentitySdo.class, VulnerabilitySdo.class})
@RelationshipLimit(source = CampaignSdo.class, relationshipType = "uses", target = {AttackPatternSdo.class, MalwareSdo.class, ToolSdo.class})
@RelationshipLimit(source = CourseOfActionSdo.class, relationshipType = "mitigates", target = {AttackPatternSdo.class, MalwareSdo.class, ToolSdo.class, VulnerabilitySdo.class})
@RelationshipLimit(source = IndicatorSdo.class, relationshipType = "indicates", target = {AttackPatternSdo.class, CampaignSdo.class, IntrusionSetSdo.class, MalwareSdo.class, ThreatActorSdo.class, ToolSdo.class})
@RelationshipLimit(source = IntrusionSetSdo.class, relationshipType = "attributed-to", target = {ThreatActorSdo.class})
@RelationshipLimit(source = IntrusionSetSdo.class, relationshipType = "targets", target = {IdentitySdo.class, VulnerabilitySdo.class})
@RelationshipLimit(source = IntrusionSetSdo.class, relationshipType = "uses", target = {AttackPatternSdo.class, MalwareSdo.class, ToolSdo.class})
@RelationshipLimit(source = MalwareSdo.class, relationshipType = "targets", target = {IdentitySdo.class, VulnerabilitySdo.class})
@RelationshipLimit(source = MalwareSdo.class, relationshipType = "uses", target = {ToolSdo.class})
@RelationshipLimit(source = MalwareSdo.class, relationshipType = "variant-of", target = {MalwareSdo.class})
@RelationshipLimit(source = ThreatActorSdo.class, relationshipType = "attributed-to", target = {IdentitySdo.class})
@RelationshipLimit(source = ThreatActorSdo.class, relationshipType = "impersonates", target = {IdentitySdo.class})
@RelationshipLimit(source = ThreatActorSdo.class, relationshipType = "targets", target = {IdentitySdo.class, VulnerabilitySdo.class})
@RelationshipLimit(source = ThreatActorSdo.class, relationshipType = "uses", target = {AttackPatternSdo.class, MalwareSdo.class, ToolSdo.class})
@RelationshipLimit(source = ToolSdo.class, relationshipType = "targets", target = {IdentitySdo.class, VulnerabilitySdo.class})
public interface RelationshipSro extends RelationshipObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "relationship";
    }

    @NotBlank
    @Vocab(RelationshipTypes.class)
    @JsonProperty("relationship_type")
    String getRelationshipType();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("source_ref")
    DomainObject getSourceRef();

    @NotNull
    @JsonProperty("target_ref")
    DomainObject getTargetRef();

}
