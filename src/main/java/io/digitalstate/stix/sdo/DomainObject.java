package io.digitalstate.stix.sdo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.digitalstate.stix.common.*;
import io.digitalstate.stix.sro.objects.RelationshipSro;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Base interface used by Immutable STIX Domain Objects
 */
public interface DomainObject extends
        StixCommonProperties,
        StixCustomProperties,
        StixLabels,
        StixModified,
        StixRevoked{

    @NotNull @Valid
    @JsonIgnore
    Set<RelationshipSro> getRelationships();

}
