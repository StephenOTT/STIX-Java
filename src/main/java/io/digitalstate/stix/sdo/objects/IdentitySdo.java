package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.IdentityClasses;
import io.digitalstate.stix.vocabularies.IndustrySectors;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "Identity", validationMethod = Value.Style.ValidationMethod.NONE)
public interface IdentitySdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "identity";
    }

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotBlank
    @Vocab(IdentityClasses.class)
    @JsonProperty("identity_class")
    String getIdentityClass();

    @NotNull
    @Vocab(IndustrySectors.class)
    @JsonProperty("sectors")
    Set<String> getSectors();

    @JsonProperty("contact_information")
    Optional<String> getContactInformation();

    @NotNull
    @JsonIgnore
    Set<String> getTargets();

    @NotNull
    @JsonIgnore
    Set<String> getAttributedTo();

    @NotNull
    @JsonIgnore
    Set<String> getImpersonates();

}
