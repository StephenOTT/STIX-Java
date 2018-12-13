package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@JsonTypeName("campaign")
@DefaultTypeValue(value = "campaign", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Campaign.class) @JsonDeserialize(builder = Campaign.Builder.class)
public interface CampaignSdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases") @JsonInclude(NON_EMPTY)
    Set<String> getAliases();

    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("first_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<Instant> getFirstSeen();

    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("last_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<Instant> getLastSeen();

    @JsonProperty("objective") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getObjective();

}
