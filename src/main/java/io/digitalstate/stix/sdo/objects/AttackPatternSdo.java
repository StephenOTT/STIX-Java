package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.businessrule.BusinessRule;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * attack-pattern
 * <p>
 * Attack Patterns are a type of TTP that describe ways that adversaries attempt to compromise targets. 
 * 
 */
@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "attack-pattern", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonTypeName("attack-pattern")
@JsonSerialize(as = AttackPattern.class) @JsonDeserialize(builder = AttackPattern.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings",
        "name", "description", "kill_chain_phases"})
@Redactable
public interface AttackPatternSdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    @JsonPropertyDescription("The name used to identify the Attack Pattern.")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("A description that provides more details and context about the Attack Pattern, potentially including its purpose and its key characteristics.")
    @Redactable
    Optional<String> getDescription();

    @JsonProperty("kill_chain_phases")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The list of kill chain phases for which this attack pattern is used.")
    @Redactable
    Set<KillChainPhaseType> getKillChainPhases();

}