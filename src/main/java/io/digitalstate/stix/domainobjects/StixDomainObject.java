package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.bundle.BundleObject;

import java.util.LinkedHashSet;

public interface StixDomainObject extends CommonProperties, BundleObject {

    LinkedHashSet<BundleObject> getAllObjectSpecificBundleObjects();

    public String toJsonString() throws JsonProcessingException;

}
