package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.bundle.BundleObject;

import java.util.LinkedHashSet;

/**
 * Use this interface to create custom relationship classes
 */
public interface CustomStixRelationshipObject extends StixRelationshipObject {

    /**
     * Returns a set of {@link BundleObject}s that will be added to a bundle if auto detection is used.
     * Cannot be null.
     * @return
     */
    LinkedHashSet<BundleObject> getBundleObjects();

}
