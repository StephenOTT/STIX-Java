package io.digitalstate.stix.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.digitalstate.stix.bundle.Bundle;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.datamarkings.MarkingDefinition;
import io.digitalstate.stix.sdo.objects.*;
import io.digitalstate.stix.sro.objects.Relationship;
import io.digitalstate.stix.sro.objects.Sighting;

import java.io.IOException;

public class StixParsers {

    private static ObjectMapper jsonMapper = new ObjectMapper().registerModule(new ParameterNamesModule())
                                                                .registerModule(new Jdk8Module())
                                                                .registerModule(new JavaTimeModule())
                                                                .registerModule(new GuavaModule());

    public static ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    public static ObjectMapper getJsonMapper(boolean withSubTypeMappings, NamedType... additionalNamedTypes) {
        if (withSubTypeMappings){
            return registerBundleMapperSubTypes(additionalNamedTypes);
        } else {
            return jsonMapper;
        }
    }

    /**
     * Override for setting a custom configured ObjectMapper
     * @param objectMapper
     */
    public static void setJsonMapper(ObjectMapper objectMapper) {
        jsonMapper = objectMapper;
    }

    public static ObjectMapper registerBundleMapperSubTypes(NamedType... additionalNamedTypes){
        Class<?>[] sdoClasses = {AttackPattern.class, Campaign.class, CourseOfAction.class,
                Identity.class, Indicator.class, IntrusionSet.class, Malware.class, ObservedData.class,
                Report.class, ThreatActor.class, Tool.class, Vulnerability.class};

        Class<?>[] sroClasses = {Relationship.class, Sighting.class};

        Class<?>[] dataMarkingClasses = {MarkingDefinition.class};

        Class<?>[] bundleClasses = {Bundle.class};

        jsonMapper.registerSubtypes(sdoClasses);
        jsonMapper.registerSubtypes(sroClasses);
        jsonMapper.registerSubtypes(dataMarkingClasses);
        jsonMapper.registerSubtypes(bundleClasses);
//        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return jsonMapper;
    }


    public static BundleObject parseBundle (String bundleJsonString) throws IOException {
        return getJsonMapper(true).readValue(bundleJsonString, BundleObject.class);
    }

    public static BundleableObject parseObject (String objectJsonString) throws IOException {
        return getJsonMapper(true).readValue(objectJsonString, BundleableObject.class);
    }
}
