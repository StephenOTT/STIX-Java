package io.digitalstate.stix.datamarkings.markingtypes;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class MarkingObjectTypeCommonProperties {

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
