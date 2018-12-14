package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;

@Value.Immutable
@Value.Style(typeImmutable = "Statement", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = Statement.class) @JsonDeserialize(builder = Statement.Builder.class)
public interface StatementMarkingObject extends GenericValidation, StixMarkingObject {

    @NotBlank
    @JsonProperty("statement")
    @Length(min = 1) String getStatement();

}
