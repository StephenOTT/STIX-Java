package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.relationshipobjects.properties.SightingProperties;

import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

/**
 * A sighting relationship is an operational efficiency relationship object for transmitting "sightings", "+1s", "thumps up" type information.
 * It is special in that it can convey a one-armed relationship like "hey I saw your indicator,
 * but I can not tell you what I actually saw or where I saw it, but I saw it".
 * It can also contain multiple edges in the same structure.
 * So instead of having to send multiple relationship objects to say, your indicator of 1,000 bad IPs,
 * I saw these 10 at these 50 different locations.
 * With the Sighting relationship object, you can transmit all of that information in a single JSON blob.
 */
public class Sighting extends SightingProperties implements StixRelationshipObject {

    private static final String TYPE = "sighting";

    public Sighting(StixDomainObject sightingOfRef){
        setType(TYPE);
        setId(generateUuidAsString());
        setSightingOfRef(sightingOfRef);
    }
}
