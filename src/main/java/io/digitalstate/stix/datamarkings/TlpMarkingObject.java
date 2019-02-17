package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.TlpLevels;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;

@Value.Immutable @Serial.Version(1L)
@Value.Style(typeImmutable = "Tlp", additionalJsonAnnotations = {JsonTypeName.class}, validationMethod = Value.Style.ValidationMethod.NONE, depluralize = true)
@JsonSerialize(as = Tlp.class) @JsonDeserialize(builder = Tlp.Builder.class)
@Redactable
@JsonTypeName("tlp")
public interface TlpMarkingObject extends GenericValidation, StixMarkingObject {

    @NotNull
    @JsonProperty("tlp")
    @Vocab(TlpLevels.class)
    String getTlp();

}
