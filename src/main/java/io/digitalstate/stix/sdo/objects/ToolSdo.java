package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.ToolLabels;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "Tool", validationMethod = Value.Style.ValidationMethod.NONE)
public interface ToolSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "tool";
    }

    @Override
    @NotNull
    @Vocab(ToolLabels.class)
    Set<@Size(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("kill_chain_phases")
    Set<KillChainPhaseType> getKillChainPhases();

    @JsonProperty("tool_version")
    Optional<String> getToolVersion();

    @NotNull
    @JsonIgnore
    Set<DomainObject> getTargets();
}
