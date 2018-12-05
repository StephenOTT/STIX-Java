package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.common.StixCommonProperties;
import io.digitalstate.stix.common.StixCustomProperties;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value.Immutable
@Value.Style(typeImmutable = "MarkingDefinition", validationMethod = Value.Style.ValidationMethod.NONE)
public interface MarkingDefinitionDm extends StixCommonProperties, StixCustomProperties {

    @Override
    @NotBlank
    default String typeValue(){
        return "marking-definition";
    }

    @NotNull
    @JsonProperty("definition_type")
    String getDefinitionType();

    @NotNull
    @JsonProperty("definition")
    StixMarkingObject getDefinition();

}
