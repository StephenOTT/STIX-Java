package io.digitalstate.stix.common;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.datamarkings.GranularMarkingDm;
import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.json.StixParsers;
import io.digitalstate.stix.json.converters.dehydrated.DomainObjectOptionalConverter;
import io.digitalstate.stix.json.converters.dehydrated.MarkingDefinitionSetConverter;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.objects.IdentitySdo;
import io.digitalstate.stix.sdo.types.ExternalReferenceType;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import io.digitalstate.stix.validation.groups.ValidateIdOnly;

/**
 * Base interface used by Immutable STIX Domain Objects
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixCommonProperties extends SdoDefaultValidator, BundleableObject {

    /**
     * Dictates if the object is hydrated.
     * Hydration is defined as if the Object has only a "ID" or has been properly
     * hydrated with the expected required fields
     * @return boolean
     */
    @NotNull
    @Value.Default
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    default boolean getHydrated(){
        return true;
    }

    @JsonProperty("type")
    @JsonPropertyDescription("The type property identifies the type of STIX Object (SDO, Relationship Object, etc). The value of the type field MUST be one of the types defined by a STIX Object (e.g., indicator).")
    @Pattern(regexp = "^\\-?[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\\-?$")
    @Size(min = 3, max = 250)
//    NotBlank is only enforced when using Default Validator group
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Type is required")
    @NotBlank
    String getType();

    @JsonProperty("id")
    @JsonPropertyDescription("Represents identifiers across the CTI specifications. The format consists of the name of the top-level object being identified, followed by two dashes (--), followed by a UUIDv4.")
    @Pattern(regexp = "^[a-z][a-z-]+[a-z]--[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
//    NotBlank is only enforced when using Default Validator group
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Id is required")
    String getId();

    @JsonProperty("created_by_ref") @JsonInclude(value = NON_EMPTY, content = NON_EMPTY)
    @JsonPropertyDescription("Represents identifiers across the CTI specifications. The format consists of the name of the top-level object being identified, followed by two dashes (--), followed by a UUIDv4.")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DomainObjectOptionalConverter.class)
    @Redactable(useMask = true, redactionMask = "identity--__REDACTED__")
    Optional<IdentitySdo> getCreatedByRef();

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @JsonProperty("created")
    @JsonPropertyDescription("The created property represents the time at which the first version of this object was created. The timstamp value MUST be precise to the nearest millisecond.")
    @Value.Default
    @Redactable(useMask = true)
    default Instant getCreated(){
        return Instant.now();
    }

    @NotNull
    @JsonProperty("external_references") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("A list of external references which refers to non-STIX information.")
    @Redactable
    Set<ExternalReferenceType> getExternalReferences();

    @NotNull
    @JsonProperty("object_marking_refs") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The list of marking-definition objects to be applied to this object.")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = MarkingDefinitionSetConverter.class)
    @Redactable
    Set<MarkingDefinitionDm> getObjectMarkingRefs();

    @NotNull
    @JsonProperty("granular_markings") @JsonInclude(NON_EMPTY)
    @JsonPropertyDescription("The set of granular markings that apply to this object.")
    @Redactable
    Set<GranularMarkingDm> getGranularMarkings();

    @JsonIgnore
    @Value.Lazy
    @Value.Auxiliary
    default String toJsonString() {
        try {
            String jsonString = StixParsers.getJsonMapper(true).writeValueAsString(this);
//            return BundleableObjectRedactionProcessor.processObject(this, jsonString, new HashSet<>(Arrays.asList()));
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot process JSON");
        }
    }

    @Value.Check
    default void checkHydrationValidation() throws ConstraintViolationException {
        if (getHydrated()){
            this.validate();
        } else {
            this.validateOnlyId();
        }
    }

    /**
     * Helper attribute to track the STIX Spec Version that was used for this object.
     * @return String of STIX Spec Version, example: "2.0"
     */
    @NotBlank
    @JsonIgnore
    @Value.Lazy
    default String getSpecVersion(){
        return StixSpecVersion.SPECVERSION;
    }

}
