package io.digitalstate.stix.domainobjects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.digitalstate.stix.cyberobservableobjects.CyberObservableObject;
import io.digitalstate.stix.domainobjects.properties.ObservedDataProperties;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Optional;

import static io.digitalstate.stix.domainobjects.json.CommonPropertiesDeserializationValidators.validateAllCommonProperties;
import static io.digitalstate.stix.helpers.IdGeneration.generateUuidAsString;

@JsonDeserialize(using = ObservedData.Deserializer.class)
public class ObservedData extends ObservedDataProperties implements StixDomainObject {

    private static final String TYPE = "observed-data";

    public ObservedData(ZonedDateTime firstObserved,
                        ZonedDateTime lastObserved,
                        Integer numberObserved,
                        HashMap<String, CyberObservableObject> objects){

        setType(TYPE);
        setId(TYPE, generateUuidAsString());
        setFirstObserved(firstObserved);
        setLastObserved(lastObserved);
        setNumberObserved(numberObserved);
        setObjects(objects);
    }


    /**
     * Used for JSON Deserialization
     */
    private ObservedData() {
    }

    public static class Deserializer extends StdDeserializer<ObservedData> {

        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public ObservedData deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {

            JsonNode node = jp.getCodec().readTree(jp);

            ObservedData object = new ObservedData();

            validateAllCommonProperties(node, object, TYPE, false);

            Optional<JsonNode> first_observed = Optional.ofNullable(node.get("first_observed"));
            first_observed.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setFirstObserved(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });
            first_observed.orElseThrow(()-> new IllegalArgumentException("first_observed is required"));

            Optional<JsonNode> last_observed = Optional.ofNullable(node.get("last_observed"));
            last_observed.ifPresent(o -> {
                Instant instant = Instant.parse(o.asText());
                object.setLastObserved(instant.atZone(ZoneId.of(StixDataFormats.DATETIMEZONE)));
            });
            last_observed.orElseThrow(()-> new IllegalArgumentException("last_observed is required"));

            Optional<JsonNode> number_observed = Optional.ofNullable(node.get("number_observed"));
            number_observed.ifPresent(o -> {
                if (o.isInt()){
                    object.setNumberObserved(o.intValue());
                } else {
                    throw new IllegalArgumentException("number_observed must be a integer between 1 and 999,999,999 inclusive");
                }
            });
            number_observed.orElseThrow(()-> new IllegalArgumentException("number_observed is required"));

            Optional<JsonNode> cyberObjects = Optional.ofNullable(node.get("objects"));
            cyberObjects.ifPresent(o -> {
                //@TODO
            });
            cyberObjects.orElseThrow(()-> new IllegalArgumentException("objects is required"));


            return object;
        }
    }
}
