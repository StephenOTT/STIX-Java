package io.digitalstate.stix.sdo.impl.types;

import java.util.List;

public class ExternalReference {
    String sourceName;
    String description = null;
    String url = null;
    List<Hashes> hashes = null;
    String externalId = null;
}
