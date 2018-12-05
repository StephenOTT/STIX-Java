package io.digitalstate.stix.sdo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.datamarkings.GranularMarkingDm;
import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.validation.groups.ValidateIdOnly;
import io.digitalstate.stix.sdo.objects.IdentitySdo;
import io.digitalstate.stix.sdo.types.ExternalReferenceType;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import org.immutables.value.Value;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Base interface used by Immutable STIX Domain Objects
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface StixCommonProperties extends SdoDefaultValidator, BundleObject {

    /**
     * Helper value that is designed to be overridden by each interface that extends this interface.
     * This is used so the getType() method and its annotations can be managed in a central location
     * @return String of the STIX type
     */
    @NotBlank
    @JsonIgnore
    @Value.Lazy
    String typeValue();

    /**
     * Dictates if the object is hydrated.
     * Hydration is defined as if the Object has only a "ID" or has been properly
     * hydrated with the expected required fields
     * @return boolean
     */
    @NotNull
    @Value.Default
    @JsonIgnore
    default boolean getHydrated(){
        return false;
    }

    // NotBlank is only enforced when using Default Validator group
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Type is required")
    @JsonProperty("type")
    default String getType(){
        return typeValue();
    }

    // NotBlank is only enforced when using Default Validator group
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Id is required")
    @JsonProperty("id")
    @Value.Default
    default String getId(){
     return String.join("--", getType(), UUID.randomUUID().toString());
    }


    @JsonProperty("created_by_ref")
    Optional<IdentitySdo> getCreatedByRef();

    @NotNull
    @JsonProperty("created")
    @Value.Default
    default Instant getCreated(){
        return Instant.now();
    }


    @NotNull
    @JsonProperty("external_references")
    Set<ExternalReferenceType> getExternalReferences();

    // @TODO json property handling
    @NotNull
    @JsonIgnore
    Set<HashMap<String,String>> getCustomProperties();

    @NotNull
    @JsonProperty("object_marking_refs")
    Set<MarkingDefinitionDm> getObjectMarkingRefs();

    @NotNull
    @JsonProperty("granular_markings")
    Set<GranularMarkingDm> getGranularMarkings();

    @JsonIgnore
    @Value.Lazy
    default String toJsonString(){
        return "SOME_JSON GOES HERE";
        //@TODO
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
