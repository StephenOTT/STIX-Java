package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.helpers.JsonConvertable;

import java.util.LinkedHashSet;

public interface StixDomainObject extends CommonProperties, BundleObject, JsonConvertable {

    LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects();

}
