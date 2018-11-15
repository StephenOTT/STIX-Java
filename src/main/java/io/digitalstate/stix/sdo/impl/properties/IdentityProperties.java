package io.digitalstate.stix.sdo.impl.properties;

import io.digitalstate.stix.sdo.vocab.IdentityClass;
import io.digitalstate.stix.sdo.vocab.Sectors;

import java.util.List;


public abstract class IdentityProperties extends CommonProperties{
    protected String name;
    protected String description = null;
    protected Enum<IdentityClass> identityClass;
    protected List<Enum<Sectors>> sectors = null;
    protected String contactInformation = null;

}
