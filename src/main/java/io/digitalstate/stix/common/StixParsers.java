package io.digitalstate.stix.common;

import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.io.IOException;

public class StixParsers {

    public static BundleObject parseBundle (String bundleJsonString) throws IOException {
        return StixDataFormats.getJsonMapper(true).readValue(bundleJsonString, BundleObject.class);
    }

    public static BundleableObject parseObject (String objectJsonString) throws IOException {
        return StixDataFormats.getJsonMapper(true).readValue(objectJsonString, BundleableObject.class);
    }
}
