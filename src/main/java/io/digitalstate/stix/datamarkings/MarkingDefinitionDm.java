package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.common.StixCommonProperties;
import io.digitalstate.stix.common.StixCustomProperties;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonTypeName("marking-definition")
@DefaultTypeValue(value = "marking-definition", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Dm", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = MarkingDefinition.class) @JsonDeserialize(builder = MarkingDefinition.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "external_references", "object_marking_refs", "granular_markings", "definition_type",
        "definition"})
public interface MarkingDefinitionDm extends StixCommonProperties, StixCustomProperties {

    @NotNull
    @JsonProperty("definition_type")
    String getDefinitionType();

    @NotNull
    @JsonProperty("definition")
    StixMarkingObject getDefinition();

}
