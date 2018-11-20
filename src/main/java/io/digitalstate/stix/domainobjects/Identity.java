package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.IdentityProperties;
import io.digitalstate.stix.vocabularies.Vocabularies;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Set;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Identity extends IdentityProperties implements StixDomainObject {

    private static final String TYPE = "identity";

    public Identity(String name, String identityClass){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
        setIdentityClass(identityClass);
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }

    @Override
    public void setIdentityClass(String identityClass) {
        Set<String> identityClassVocab = Vocabularies.getIdentityClass();

        if (StringUtils.isNotBlank(identityClass) && identityClassVocab.contains(identityClass)){
            this.identityClass = identityClass;
        } else {
            throw new IllegalArgumentException("Identity Class can't be null or blank, and must match one of the Identity Class's defined in the Identity Class Vocab");
        }
    }

    @Override
    public void setSectors(LinkedHashSet<String> sectors) {
        Set<String> industrySectors = Vocabularies.getIndustrySectors();

        if (sectors != null && !industrySectors.containsAll(sectors)){
            throw new IllegalArgumentException("One or more invalid sectors were provided: valid-sectors: "
                    + industrySectors.toString() + ". sectors-provided: " + sectors.toString());
        } else {
            this.sectors = sectors;
        }
    }
}
