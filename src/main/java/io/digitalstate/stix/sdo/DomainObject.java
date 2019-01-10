package io.digitalstate.stix.sdo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.digitalstate.stix.common.*;
import io.digitalstate.stix.sro.objects.RelationshipSro;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Base interface used by Immutable STIX Domain Objects
 */
public interface DomainObject extends Serializable,
        StixCommonProperties,
        StixCustomProperties,
        StixLabels,
        StixModified,
        StixRevoked{

    @NotNull
    @JsonIgnore
    Set<RelationshipSro> getRelationships();

}
