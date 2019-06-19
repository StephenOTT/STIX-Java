package io.digitalstate.stix.custom;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.digitalstate.stix.common.*;
import io.digitalstate.stix.validation.contraints.startswith.StartsWith;
import org.hibernate.validator.constraints.Length;

import java.util.Map;

/**
 * Provides a Generic STIX Custom Object to use for Bundleable objects when the object is not included in the mappings.
 * Note: Custom properties (x_) are included in the CustomObjectProperties
 */
public interface StixCustomObject extends
        StixCommonProperties,
        StixLabels,
        StixModified,
        StixRevoked{

    @Override
    @StartsWith("x-")
    String getType();

    @Override
    @StartsWith("x-")
    String getId();

    //@TODO Future enhancement to create a custom deserializer that will support the difference between x_ props and the CustomObjectProperties()
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonUnwrapped @JsonAnyGetter
    Map<@Length(min = 3,
            max = 250,
            message = "STIX Custom Properties must have a min key length of 3 and max of 250")
            String, Object> getCustomObjectProperties();

}
