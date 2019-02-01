package io.digitalstate.stix.sdo.objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.ToolLabels;

/**
 * tool
 * <p>
 * Tools are legitimate software that can be used by threat actors to perform attacks.
 * This SDO MUST NOT be used to characterize malware. 
 * Further, Tool MUST NOT be used to characterise tools used as part of a course of action in response to an attack.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("tool")
@DefaultTypeValue(value = "tool", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Tool.class) @JsonDeserialize(builder = Tool.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name",
        "description", "kill_chain_phases", "tool_version"})
@Redactable
public interface ToolSdo extends DomainObject {

    @Override
    @NotNull
    @Vocab(ToolLabels.class)
	@JsonPropertyDescription("The kind(s) of tool(s) being described. Open Vocab - tool-label-ov")
    @Redactable(useMask = true)
    Set<@Length(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
	@JsonPropertyDescription("The name used to identify the Tool.")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("Provides more context and details about the Tool object.")
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("kill_chain_phases") @JsonInclude(NON_EMPTY)
	@JsonPropertyDescription("The list of kill chain phases for which this Tool instance can be used.")
    @Redactable
    Set<KillChainPhaseType> getKillChainPhases();

    @JsonProperty("tool_version") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
	@JsonPropertyDescription("The version identifier associated with the tool.")
    @Redactable
    Optional<String> getToolVersion();

}
