package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.JsonConvertable;

import java.util.LinkedHashSet;

/**
 * Core contract for a STIX Bundle
 * A Bundle is a collection of arbitrary STIX Objects and Marking Definitions grouped together in a single container.
 * A Bundle does not have any semantic meaning and Objects are not considered related by virtue of being in the same Bundle.
 */
public interface StixBundle extends JsonConvertable {

    String getType();
    void setType(String type);

    String getId();
    void setId(String id);

    String getSpecVersion();
    void setSpecVersion(String specVersion);

    LinkedHashSet<BundleObject> getObjects();
    void setObjects(LinkedHashSet<BundleObject> objects);

}
