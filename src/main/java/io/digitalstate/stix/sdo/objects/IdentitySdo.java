package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabulary.vocabularies.IdentityClasses;
import io.digitalstate.stix.vocabulary.vocabularies.IndustrySectors;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * identity
 * <p>
 * Identities can represent actual individuals, organizations, or groups (e.g., ACME, Inc.) as well as classes of individuals, organizations, or groups.
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("identity")
@DefaultTypeValue(value = "identity", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = Identity.class) @JsonDeserialize(builder = Identity.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description",
        "identity_class", "sectors", "contact_information"})
@Redactable
public interface IdentitySdo extends DomainObject {

    // Note for the labels attribute:
    // The list of roles that this Identity performs (e.g., CEO, Domain Administrators, Doctors, Hospital, or Retailer). No open vocabulary is yet defined for this property.

    @NotBlank
    @JsonProperty("name")
    @JsonPropertyDescription("The name of this Identity.")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("A description that provides more details and context about the Identity.")
    @Redactable
    Optional<String> getDescription();

    @NotBlank
    @Vocab(IdentityClasses.class)
    @JsonProperty("identity_class")
    @JsonPropertyDescription("The type of entity that this Identity describes, e.g., an individual or organization. Open Vocab - identity-class-ov")
    @Redactable(useMask = true)
    String getIdentityClass();

    @NotNull
    @Vocab(IndustrySectors.class)
    @JsonProperty("sectors")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The list of sectors that this Identity belongs to. Open Vocab - industry-sector-ov")
    @Redactable
    Set<String> getSectors();

    @JsonProperty("contact_information")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("The contact information (e-mail, phone number, etc.) for this Identity.")
    @Redactable
    Optional<String> getContactInformation();

}
