package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.IntrusionSetProperties;
import org.apache.commons.lang3.StringUtils;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class InstrusionSet extends IntrusionSetProperties implements StixDomainObject{

    private static final String TYPE = "intrusion-set";

    public InstrusionSet(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }
}
