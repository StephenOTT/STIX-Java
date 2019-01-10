package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixCommonProperties;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.markingdefinitiontype.MarkingDefinitionTypeLimit;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>Builder Required Fields:</p>
 * <ol>
 *     <li>{@link MarkingDefinition#definitionType} - (A helper is in-place for this field that will pre-populate the value based on the specific Marking Object, which makes this field essentially optional).</li>
 *     <li>{@link MarkingDefinition#definition}  - the Marking Object.  Two objects are currently supported: {@link Tlp} and {@link Statement}.</li>
 * </ol>
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("marking-definition")
@DefaultTypeValue(value = "marking-definition", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Dm", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = MarkingDefinition.class) @JsonDeserialize(builder = MarkingDefinition.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "external_references", "object_marking_refs", "granular_markings", "definition_type",
        "definition"})
@MarkingDefinitionTypeLimit(markingObject = TlpMarkingObject.class, markingDefinitionType = "tlp", groups = {DefaultValuesProcessor.class})
@MarkingDefinitionTypeLimit(markingObject = StatementMarkingObject.class, markingDefinitionType = "statement", groups = {DefaultValuesProcessor.class})
@Redactable
public interface MarkingDefinitionDm extends StixCommonProperties, StixCustomProperties {

    @NotBlank
    @JsonProperty("definition_type")
    String getDefinitionType();

    @NotNull
    @JsonProperty("definition")
    StixMarkingObject getDefinition();

}
