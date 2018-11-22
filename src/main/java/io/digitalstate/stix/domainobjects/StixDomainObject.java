package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.bundle.BundleObject;

import java.util.LinkedHashSet;

public interface StixDomainObject extends CommonProperties, BundleObject {

    LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects();

}
