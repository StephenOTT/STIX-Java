package io.digitalstate.stix.datamarkings;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.validation.GenericValidation;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.TlpLevels;
import org.immutables.value.Value;

import javax.validation.constraints.NotNull;

@Value.Immutable
@Value.Style(typeImmutable = "Tlp", validationMethod = Value.Style.ValidationMethod.NONE)
@JsonSerialize(as = Tlp.class) @JsonDeserialize(builder = Tlp.Builder.class)
public interface TlpMarkingObject extends GenericValidation, StixMarkingObject {

    @NotNull
    @JsonProperty("tlp")
    @Vocab(TlpLevels.class)
    String getTlp();

}
