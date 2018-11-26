package io.digitalstate.stix.datamarkings.markingtypes;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class MarkingObjectTypeCommonProperties {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String toJsonString() throws JsonProcessingException {
        return StixDataFormats.getJsonMapper().writeValueAsString(this);
    }

}
