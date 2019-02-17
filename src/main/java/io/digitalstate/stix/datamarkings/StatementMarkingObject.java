package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.GenericValidation;
import org.hibernate.validator.constraints.Length;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;

@Value.Immutable @Serial.Version(1L)
@Value.Style(typeImmutable = "Statement", additionalJsonAnnotations = {JsonTypeName.class}, validationMethod = Value.Style.ValidationMethod.NONE, depluralize = true)
@JsonSerialize(as = Statement.class) @JsonDeserialize(builder = Statement.Builder.class)
@Redactable
@JsonTypeName("statement")
public interface StatementMarkingObject extends GenericValidation, StixMarkingObject {

    @NotBlank
    @JsonProperty("statement")
    @Length(min = 1) String getStatement();

}
