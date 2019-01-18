package io.digitalstate.stix.coo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.coo.CyberObservableObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.types.KillChainPhaseType;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable @Serial.Version(1L)
@DefaultTypeValue(value = "dictionary", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Coo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonTypeName("dictionary")
@JsonSerialize(as = Dictionary.class) @JsonDeserialize(builder = Dictionary.Builder.class)
@JsonPropertyOrder({"type", "extensions", "path", "path_enc", "created", "modified", "accessed", "contains_refs"})
public interface DictionaryCoo extends CyberObservableObject {

    @NotBlank
    @JsonProperty("path") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable(useMask = true)
    String getPath();

    @JsonProperty("path_enc") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getName();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("created") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<Instant> getCreated();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("modified") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<Instant> getModified();

    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("accessed") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<Instant> getAccessed();

    //@TODO add proper support for contains refs.  Must be Set of File or Directory types
    @NotNull
    @JsonProperty("contains_refs") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<String> getContainsRefs();


}
