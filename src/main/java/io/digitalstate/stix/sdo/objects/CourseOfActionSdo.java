package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.sdo.DomainObject;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(typeImmutable = "CourseOfAction", validationMethod = Value.Style.ValidationMethod.NONE)
public interface CourseOfActionSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "course-of-action";
    }

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("action")
    Set<String> getAction();

    @NotNull
    @JsonIgnore
    Set<String> getMitigates();


}
