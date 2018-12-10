package io.digitalstate.stix.common;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.datamarkings.GranularMarkingDm;
import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;
import io.digitalstate.stix.helpers.DehydratedDomainObjectJsonConverter;
import io.digitalstate.stix.helpers.DehydratedMarkingDefinitionJsonConverter;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.sdo.objects.IdentitySdo;
import io.digitalstate.stix.sdo.types.ExternalReferenceType;
import io.digitalstate.stix.validation.SdoDefaultValidator;
import io.digitalstate.stix.validation.groups.ValidateIdOnly;
import org.immutables.value.Value;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

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
//    NotBlank is only enforced when using Default Validator group
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Type is required")
    @NotBlank
    String getType();

    @JsonProperty("id")
//    NotBlank is only enforced when using Default Validator group
    @NotBlank(groups = {Default.class, ValidateIdOnly.class}, message = "Id is required")
    String getId();

    @JsonProperty("created_by_ref") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DehydratedDomainObjectJsonConverter.class)
    Optional<IdentitySdo> getCreatedByRef();

    @NotNull
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = StixDataFormats.DATEPATTERN, timezone = "UTC")
    @JsonProperty("created")
    @Value.Default
    default Instant getCreated(){
        return Instant.now();
    }

    @NotNull
    @JsonProperty("external_references") @JsonInclude(NON_EMPTY)
    Set<ExternalReferenceType> getExternalReferences();

    @NotNull
    @JsonProperty("object_marking_refs") @JsonInclude(NON_EMPTY)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(converter = DehydratedMarkingDefinitionJsonConverter.class)
    Set<MarkingDefinitionDm> getObjectMarkingRefs();

    @NotNull
    @JsonProperty("granular_markings") @JsonInclude(NON_EMPTY)
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
