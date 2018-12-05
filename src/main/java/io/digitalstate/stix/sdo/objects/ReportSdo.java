package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.vocabularies.ReportLabels;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Value.Immutable
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface ReportSdo extends DomainObject {

    @Override
    @NotBlank
    default String typeValue(){
        return "report";
    }

    @Override
    @NotNull
    @Vocab(ReportLabels.class)
    Set<@Size(min = 1) String> getLabels();

    @NotBlank
    @JsonProperty("name")
    String getName();

    @JsonProperty("description")
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("published")
    Instant getPublished();

    @NotNull @Size(min = 1, message = "Must have at least one Report object reference")
    @JsonProperty("object_refs")
    Set<BundleObject> getObjectRefs();

}
