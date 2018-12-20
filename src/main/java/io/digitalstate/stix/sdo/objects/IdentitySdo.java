package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
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
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = Identity.class) @JsonDeserialize(builder = Identity.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "identity_class", "sectors", "contact_information"})
@Redactable
public interface IdentitySdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getDescription();

    @NotBlank
    @Vocab(IdentityClasses.class)
    @JsonProperty("identity_class")
    @Redactable(useMask = true)
    String getIdentityClass();

    @NotNull
    @Vocab(IndustrySectors.class)
    @JsonProperty("sectors") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<String> getSectors();

    @JsonProperty("contact_information") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getContactInformation();

}
