package io.digitalstate.stix.helpers;

import java.util.HashSet;
import java.util.Set;

/**
 * STIX Custom properties configuration
 */
public class StixCustomPropertiesConfig {

    /**
     * Default Custom Property Prefix: {@code x_ }.
     */
    public static final String DEFAULT_CUSTOM_PROPERTY_PREFIX = "x_";

    private static Set<String> additionalPropertyPrefixes = new HashSet<>();

    /**
     * Get Additional STIX Custom Property prefixes.
     */
    public static Set<String> getAdditionalPropertyPrefixes() {
        return additionalPropertyPrefixes;
    }

    /**
     * Set Additional STIX Custom Property prefixes.
     */
    public static void setAdditionalPropertyPrefixes(Set<String> additionalPropertyPrefixes) {
        StixCustomPropertiesConfig.additionalPropertyPrefixes = additionalPropertyPrefixes;
    }

    /**
     * Returns a aggregate of The Default Custom Property Prefix and any defined additional customer property prefixes.
     * @return Set<String> of allowed custom property prefixes
     */
    public static Set<String> getAllCustomPropertyPrefixes(){
        Set<String> allPrefixes = new HashSet<>(getAdditionalPropertyPrefixes());
        allPrefixes.add(DEFAULT_CUSTOM_PROPERTY_PREFIX);
        return allPrefixes;
    }
}
