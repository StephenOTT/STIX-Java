package io.digitalstate.stix.helpers;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class StixDataFormats {

    /**
     * Default pattern for deserialization of date/times into a STIX compliant timestamp.
     */
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]X";

    /**
     * Default Timezone used for Serialization and Deserialization.
     */
    public static final String TIMEZONE = "UTC";

    /**
     * Supports 0-9 digits of Sub-Second precision storage.  (Nano Second Support)
     * @return
     */
    public static DateTimeFormatter getStixDateTimeFormatter() {
        DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                .optionalEnd()
                .appendPattern("X");

        return formatterBuilder.toFormatter().withZone(ZoneId.of("UTC"));
    }

}
