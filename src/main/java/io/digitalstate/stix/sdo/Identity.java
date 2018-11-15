package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.vocab.IdentityClass;
import io.digitalstate.stix.sdo.vocab.Sectors;

import java.util.List;

public interface Identity extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public Enum<IdentityClass> getIdentityClass();
    public void setIdentityClass(Enum<IdentityClass> identityClassEnum);

    public List<Enum<Sectors>> getSectors();
    public void setSectors(List<Enum<Sectors>> sectors);

    public String getContactInformation();
    public void setContactInformation(String contactInformation);
}
