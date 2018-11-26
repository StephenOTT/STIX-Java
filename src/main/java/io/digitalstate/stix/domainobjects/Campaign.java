package io.digitalstate.stix.domainobjects;

import io.digitalstate.stix.domainobjects.properties.CampaignProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

public class Campaign extends CampaignProperties implements StixDomainObject {

    private static final String TYPE = "campaign";

    public Campaign(String name){
        setType(TYPE);
        setId(generateUuidAsString());
        setName(name);
    }
}
