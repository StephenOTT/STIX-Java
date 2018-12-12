package io.digitalstate.stix.helpers;

public class StixDataFormats {

    /**
     * Default pattern for deserialization of date/times into a STIX compliant timestamp.
     */
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    /**
     * Default Timezone used for Serialization and Deserialization.
     */
    public static final String TIMEZONE = "UTC";

    /**
     * Default Custom Property Prefix: {@code x_ }.
     */
    public static final String CUSTOM_PROPERTY_PREFIX = "x_";

}
