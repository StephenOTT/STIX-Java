package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "AttackPattern", validationMethod = Value.Style.ValidationMethod.NONE)
public interface AttackPatternSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "attack-pattern";
    }

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("kill_chain_phases")
    Set<KillChainPhaseType> getKillChainPhases();

}
