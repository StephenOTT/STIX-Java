package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.CampaignProperties;
import org.apache.commons.lang3.StringUtils;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Campaign extends CampaignProperties implements StixDomainObject {

    private static final String TYPE = "campaign";

    public Campaign(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name can't be null or blank");
        }
    }
}
