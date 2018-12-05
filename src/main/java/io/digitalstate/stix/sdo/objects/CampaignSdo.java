package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "Campaign", validationMethod = Value.Style.ValidationMethod.NONE)
public interface CampaignSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "campaign";
    }

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases")
    Set<String> getAliases();

    @JsonProperty("first_seen")
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen")
    Optional<Instant> getLastSeen();

    @JsonProperty("objective")
    Optional<String> getObjective();

    @NotNull
    @JsonIgnore
    Set<String> getAttributedTo();

    @NotNull
    @JsonIgnore
    Set<String> getTargets();

    @NotNull
    @JsonIgnore
    Set<String> getUses();

}
