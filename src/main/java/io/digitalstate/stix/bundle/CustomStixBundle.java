package io.digitalstate.stix.bundle;

/**
 * A interface for Custom STIX Bundles to implement.
 * This interface is mainly used as a way to easily detect custom implementations vs the default implementation.
 * Future work may include additional contracts defined in this interface to be used by Custom implementations of STIX Bundles.
 */
public interface CustomStixBundle extends StixBundle {
}
