package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This interface is typically inherited by other interfaces that are considered "objects" that are part of a Bundle.
 * Thus the name "BundleableObject".  A Bundleable Object by STIX standard is: SDO, SRO, and Marking Definition.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY )
public interface BundleableObject {

    String getType();
    String getId();

}
