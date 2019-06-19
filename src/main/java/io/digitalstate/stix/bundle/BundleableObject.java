package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.digitalstate.stix.common.Stix;
import io.digitalstate.stix.custom.objects.CustomObject;
import io.digitalstate.stix.datamarkings.GranularMarkingDm;
import io.digitalstate.stix.datamarkings.MarkingDefinitionDm;

import java.io.Serializable;
import java.util.Set;

/**
 * This interface is typically inherited by other interfaces that are considered "objects" that are part of a Bundle.
 * Thus the name "BundleableObject".  A Bundleable Object by STIX standard is: SDO, SRO, and Marking Definition.
 * The Type field is used to determine the sub-types as registered in the {@link io.digitalstate.stix.json.StixParsers}
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true, defaultImpl = CustomObject.class)
public interface BundleableObject extends Serializable, Stix {

    String getType();
    String getId();
    Set<MarkingDefinitionDm> getObjectMarkingRefs();
    Set<GranularMarkingDm> getGranularMarkings();
    boolean getHydrated();
    String toJsonString();

}
