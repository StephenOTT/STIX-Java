package io.digitalstate.stix.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StixDataFormats {

    private static ObjectMapper jsonMapper = new ObjectMapper();

    public static final String DATEPATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATETIMEZONE = "Etc/UTC";

    //
    // Getters and Setters
    //

    public static ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    public static void setJsonMapper(ObjectMapper objectMapper) {
        StixDataFormats.jsonMapper = objectMapper;
    }
}
