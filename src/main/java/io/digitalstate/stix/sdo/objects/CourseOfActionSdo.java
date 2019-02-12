package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * course-of-action
 * <p>
 * A Course of Action is an action taken either to prevent an attack or to respond to an attack that is in progress. 
 * 
 */
@Value.Immutable @Serial.Version(1L)
@JsonTypeName("course-of-action")
@DefaultTypeValue(value = "course-of-action", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class}, depluralize = true)
@JsonSerialize(as = CourseOfAction.class) @JsonDeserialize(builder = CourseOfAction.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name",
        "description", "action"})
@Redactable
public interface CourseOfActionSdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    @JsonPropertyDescription("The name used to identify the Course of Action.")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("A description that provides more details and context about the Course of Action, potentially including its purpose and its key characteristics.")
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("action")
    @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonPropertyDescription("RESERVED – To capture structured/automated courses of action.")
    @Redactable(useMask = true)
    Set<String> getAction();

}
