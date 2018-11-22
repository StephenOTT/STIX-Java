package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.customobjects.StixCustomObject;

/**
 * STIX Custom Object Interface
 * Implement this interface on custom domain objects you want to inject into STIX.
 */
public interface CustomStixDomainObject extends StixDomainObject, StixCustomObject {
}
