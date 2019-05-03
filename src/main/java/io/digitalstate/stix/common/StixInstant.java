package io.digitalstate.stix.common;

import io.digitalstate.stix.helpers.StixDataFormats;

import java.time.Instant;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores a Java.time Instant and stores the original sub-second precision digital count.
 * SubSecond precision count means that the number of digits (even if all zeros / trailing zeros) are remembered.
 * Use toString() to get the Stix string format for the datetime.
 */
public class StixInstant {

    public static final Pattern REGEX_SUBSECOND = Pattern.compile("(?<fullDate>\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(.(?<subSecond>[0-9]+))?Z)");

    private final Instant instant;
    private final int originalSubSecondPrecisionDigitCount;

    /**
     * Generates a Instant.now()
     */
    public StixInstant() {
        Instant now = Instant.now();
        this.instant = now;
        this.originalSubSecondPrecisionDigitCount = 3;
    }

    public StixInstant(Instant instant, int subSecondPrecision) {
        Objects.requireNonNull(instant);

        this.instant = instant;
        this.originalSubSecondPrecisionDigitCount = subSecondPrecision;
    }

    /**
     * Defaults with parsed Instant's Nano second precision digit count.
     * Note that trailing zeros would have been stripped.
     * If you need to keep exact precision, including the trailing zeros, use {@link StixInstant#StixInstant(Instant, int)}
     * @param instant
     */
    public StixInstant(Instant instant) {
        this(instant, String.valueOf(instant.getNano()).length());
    }

    /**
     * Get the underlying Instant value.  If you need the Stix Date with the Stix Precision then use {@link StixInstant#toString()}
     * getInstant() should only be used in special cases where you need access to perform temporal work.
     * @return
     */
    public Instant getInstant() {
        return instant;
    }

    public int getOriginalSubSecondPrecisionDigitCount() {
        return originalSubSecondPrecisionDigitCount;
    }

    /**
     * Generates a STIX Spec String of DateTime.
     * Uses {@link StixDataFormats#getWriterStixDateTimeFormatter(int)}
     * @return
     */
    @Override
    public String toString() {
        return StixDataFormats.getWriterStixDateTimeFormatter(originalSubSecondPrecisionDigitCount)
                .format(this.instant);
    }

    public String toString(int subSecondPrecision) {
        return StixDataFormats.getWriterStixDateTimeFormatter(subSecondPrecision)
                .format(this.instant);
    }

    public static StixInstant parse(String dateString){
        Instant instant = Instant.from(StixDataFormats.getReaderStixDateTimeFormatter().parse(dateString));
        int subSecondCount = getSubSecondDigitCount(dateString);
        return new StixInstant(instant, subSecondCount);
    }

    private static int getSubSecondDigitCount(String dateString){
        Matcher matcher = REGEX_SUBSECOND.matcher(dateString);
        if (matcher.find()){
            String subSeconds = matcher.group("subSecond");
            // If no sub seconds were provided then return 0 as the precision
            if (subSeconds == null){
                return 0;
            } else {
                return subSeconds.length();
            }

        } else {
            throw new IllegalStateException("Unable to parse date");
        }
    }
}
