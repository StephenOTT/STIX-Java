package io.digitalstate.stix.sdo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 *
 */
@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)
public interface DomainObjectCommonRelationships {

    @JsonIgnore
    Set<@NotBlank DomainObject> getDuplicateOf();

    @JsonIgnore
    Set<@NotBlank DomainObject> getDerivedFrom();

    @JsonIgnore
    Set<@NotBlank DomainObject> getRelatedTo();
}
