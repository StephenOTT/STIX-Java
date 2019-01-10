package io.digitalstate.stix.sdo.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable @Serial.Version(1L)
@Value.Style(typeAbstract="*Type", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = ExternalReference.class) @JsonDeserialize(builder = ExternalReference.Builder.class)
public interface ExternalReferenceType extends GenericValidation, Serializable {

    @NotBlank
    @JsonProperty("source_name")
    String getSourceName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getDescription();

    @JsonProperty("url") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getUrl();

    @JsonProperty("hashes") @JsonInclude(NON_EMPTY)
    @Size(min = 1, message = "Must have at least 1 hash value")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

    @JsonProperty("external_id") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getExternalId();

}
