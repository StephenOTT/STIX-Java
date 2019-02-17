package io.digitalstate.stix.coo.types;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.hashingvocab.HashingVocab;
import io.digitalstate.stix.vocabulary.vocabularies.HashingAlgorithms;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * The PE Section type specifies metadata about a PE file section.
 *
 */
@Value.Immutable @Serial.Version(1L)
//@DefaultTypeValue(value = "windows-pe-section-type", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Obj", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = WindowsPeSection.class) @JsonDeserialize(builder = WindowsPeSection.Builder.class)
@JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
@JsonPropertyOrder({ "name", "size", "entropy", "hashes" })
//@JsonTypeName("windows-pe-section-type")
public interface WindowsPeSectionObj extends GenericValidation {

    @JsonProperty("name")
    @JsonPropertyDescription("Specifies the name of the section.")
    @NotNull
    Optional<String> getName();

    @JsonProperty("size")
    @JsonPropertyDescription("Specifies the size of the section, in bytes.")
    @PositiveOrZero
    Optional<Integer> getSize();

    @JsonProperty("entropy")
    @JsonPropertyDescription("Specifies the calculated entropy for the section, as calculated using the Shannon algorithm.")
    Optional<Float> getEntropy();

    @JsonProperty("hashes")
    @JsonPropertyDescription("Specifies any hashes computed over the section.")
    Map<@Length(min = 3, max = 256) @HashingVocab(HashingAlgorithms.class) String, String> getHashes();

}
