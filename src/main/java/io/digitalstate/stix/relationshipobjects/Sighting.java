package io.digitalstate.stix.relationshipobjects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.domainobjects.AttackPattern;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.relationshipobjects.properties.SightingProperties;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

import static io.digitalstate.stix.relationshipobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
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
@JsonDeserialize(using = Sighting.Deserializer.class)
public class Sighting extends SightingProperties implements StixRelationshipObject {

    public static final String TYPE = "sighting";

    public Sighting(StixDomainObject sightingOfRef) {
        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setSightingOfRef(sightingOfRef);
    }


    public static Sighting parse(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        return StixDataFormats.getJsonMapper().readValue(jsonString, Sighting.class);
    }

    /**
     * Used for JSON Deserialization
     */
    private Sighting() {
    }

    public static class Deserializer extends StdDeserializer<Sighting> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Sighting deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            Sighting object = new Sighting();

            validateAllCommonProperties(node, jp, object, TYPE, false);

            Optional<JsonNode> first_seen = Optional.ofNullable(node.get("first_seen"));
            first_seen.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setFirstSeen(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });

            Optional<JsonNode> last_seen = Optional.ofNullable(node.get("last_seen"));
            last_seen.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setLastSeen(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });

            Optional<JsonNode> count = Optional.ofNullable(node.get("count"));
            count.ifPresent(o -> {
                if (o.isInt()) {
                    object.setCount(o.intValue());
                } else {
                    throw new IllegalArgumentException("count must be a integer between 1 and 999,999,999 inclusive");
                }
            });

            Optional<JsonNode> sighting_of_ref = Optional.ofNullable(node.get("sighting_of_ref"));
            sighting_of_ref.ifPresent(o -> {
                Relation<StixDomainObject> relation = new Relation<>(o.asText());
                object.setSightingOfRef(relation);
            });
            sighting_of_ref.orElseThrow(()-> new IllegalArgumentException("sighting_of_ref is required"));


            Optional<JsonNode> where_sighted_refs = Optional.ofNullable(node.get("where_sighted_refs"));
            where_sighted_refs.ifPresent(o -> {
                //@TODO
            });

            Optional<JsonNode> observed_data_refs = Optional.ofNullable(node.get("observed_data_refs"));
            observed_data_refs.ifPresent(o -> {
                //@TODO
            });

            return object;
        }
    }
}
