package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value.Immutable
@Value.Style(typeImmutable = "Statement", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = Statement.class) @JsonDeserialize(builder = Statement.Builder.class)
public interface StatementMarkingObject extends SdoDefaultValidator, StixMarkingObject {

    @NotBlank
    @JsonProperty("statement")
    String getStatement();

}
