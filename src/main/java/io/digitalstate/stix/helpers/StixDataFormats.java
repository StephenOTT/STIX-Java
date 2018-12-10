package io.digitalstate.stix.helpers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.digitalstate.stix.bundle.Bundle;
import io.digitalstate.stix.datamarkings.MarkingDefinition;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sdo.objects.*;
import io.digitalstate.stix.sro.objects.Relationship;
import io.digitalstate.stix.sro.objects.Sighting;
import org.checkerframework.common.reflection.qual.GetClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StixDataFormats {

    private static ObjectMapper jsonMapper = new ObjectMapper().registerModule(new ParameterNamesModule())
                                                                .registerModule(new Jdk8Module())
                                                                .registerModule(new JavaTimeModule())
                                                                .registerModule(new GuavaModule());

    public static final String DATEPATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String DATETIMEZONE = "UTC";


    public static ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    public static ObjectMapper getJsonMapper(boolean withSubTypeMappings) {
        if (withSubTypeMappings){
            return registerBundleMapperSubTypes();
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

        return jsonMapper;
    }
}
