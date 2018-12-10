package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sro.objects.RelationshipSro;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.IdentityClasses;
import io.digitalstate.stix.vocabularies.IndustrySectors;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable
@JsonTypeName("identity")
@DefaultTypeValue(value = "identity", groups = {DefaultValuesProcessor.class})
@Value.Style(typeImmutable = "Identity", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Identity.class) @JsonDeserialize(builder = Identity.Builder.class)
public interface IdentitySdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getDescription();

    @NotBlank
    @Vocab(IdentityClasses.class)
    @JsonProperty("identity_class")
    String getIdentityClass();

    @NotNull
    @Vocab(IndustrySectors.class)
    @JsonProperty("sectors") @JsonInclude(NON_EMPTY)
    Set<String> getSectors();

    @JsonProperty("contact_information") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    Optional<String> getContactInformation();

}
