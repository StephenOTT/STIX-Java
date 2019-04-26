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
import io.digitalstate.stix.coo.extension.types.*;
import io.digitalstate.stix.coo.objects.*;
import io.digitalstate.stix.coo.objects.Process;
import io.digitalstate.stix.datamarkings.MarkingDefinition;
import io.digitalstate.stix.datamarkings.objects.Statement;
import io.digitalstate.stix.datamarkings.objects.Tlp;
import io.digitalstate.stix.sdo.objects.*;
import io.digitalstate.stix.sro.objects.Relationship;
import io.digitalstate.stix.sro.objects.Sighting;

import javax.validation.ValidationException;
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
        //@TODO Add config to only serialize/deserialize that have @JsonProperty() annotation
        if (withSubTypeMappings) {
            return registerBundleMapperSubTypes(additionalNamedTypes);
        } else {
            return jsonMapper;
        }
    }

    /**
     * Override for setting a custom configured ObjectMapper
     *
     * @param objectMapper
     */
    public static void setJsonMapper(ObjectMapper objectMapper) {
        jsonMapper = objectMapper;
    }

    public static ObjectMapper registerBundleMapperSubTypes(NamedType... additionalNamedTypes) {
        Class<?>[] sdoClasses = {AttackPattern.class, Campaign.class, CourseOfAction.class,
                Identity.class, Indicator.class, IntrusionSet.class, Malware.class, ObservedData.class,
                Report.class, ThreatActor.class, Tool.class, Vulnerability.class};

        Class<?>[] sroClasses = {Relationship.class, Sighting.class};

        Class<?>[] dataMarkingClasses = {MarkingDefinition.class, Statement.class, Tlp.class};

        Class<?>[] bundleClasses = {Bundle.class};

        Class<?>[] cyberObservableClasses = {Artifact.class, AutonomousSystem.class, Directory.class,
                DomainName.class, EmailAddress.class, EmailMessage.class, File.class, Ipv4Address.class, Ipv6Address.class,
                MacAddress.class, Mutex.class, NetworkTraffic.class, Process.class, Software.class, Url.class,
                UserAccount.class, WindowsRegistryKey.class, X509Certificate.class};

        Class<?>[] cyberObservableExtensionClasses = {ArchiveFileExtension.class, HttpRequestExtension.class, IcmpExtension.class,
                NetworkSocketExtension.class, NtfsFileExtenstion.class, PdfFileExtension.class, RasterImageFileExtension.class,
                TcpExtension.class, UnixAccountExtension.class, WindowsPeBinaryFileExtension.class, WindowsProcessExtension.class,
                WindowsServiceExtension.class};


        jsonMapper.registerSubtypes(sdoClasses);
        jsonMapper.registerSubtypes(sroClasses);
        jsonMapper.registerSubtypes(dataMarkingClasses);
        jsonMapper.registerSubtypes(bundleClasses);
        jsonMapper.registerSubtypes(cyberObservableClasses);
        jsonMapper.registerSubtypes(cyberObservableExtensionClasses);
        jsonMapper.registerSubtypes(additionalNamedTypes);

        return jsonMapper;
    }

    public static BundleObject parseBundle(String bundleJsonString) throws IOException, StixParserValidationException {
       try {
           return getJsonMapper(true).readValue(bundleJsonString, BundleObject.class);
       } catch (IOException ex) {
        if (ValidationException.class.isAssignableFrom(ex.getCause().getClass())) {
            throw new StixParserValidationException((ValidationException) ex.getCause());
        } else {
            throw ex;
        }
    }
    }

    public static BundleableObject parseObject(String objectJsonString) throws IOException, StixParserValidationException {
        try {
            return getJsonMapper(true).readValue(objectJsonString, BundleableObject.class);
        } catch (IOException ex) {
            if (ValidationException.class.isAssignableFrom(ex.getCause().getClass())) {
                throw new StixParserValidationException((ValidationException) ex.getCause());
            } else {
                throw ex;
            }
        }
    }

//    public static SimpleModule generateStixInstantModule(){
//        SimpleModule module = new SimpleModule();
//        module.
//    }
}
