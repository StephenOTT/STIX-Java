package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY )
public interface BundleableObject {

    String getType();
    String getId();

}
