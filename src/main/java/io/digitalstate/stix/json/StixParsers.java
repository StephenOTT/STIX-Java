package io.digitalstate.stix.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.digitalstate.stix.bundle.Bundle;
import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.common.StixBoolean;
import io.digitalstate.stix.common.StixInstant;
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

/**
 * Default JSON Mapper is configured with JsonMapperBase configs + StixSubTypesModule + StixInstantModule
 */
public class StixParsers {

    private static ObjectMapper jsonMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule(new GuavaModule())
            .registerModule(generateStixSubTypesModule())
            .registerModule(generateStixInstantModule())
            .registerModule(generateStixBooleanModule());

    /**
     * Generates a Base Object Mapper with some generic modules.
     * @return
     */
    public static ObjectMapper generateJsonMapperBase() {
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(new GuavaModule());
    }

    public static ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    /**
     * Override for setting a custom configured ObjectMapper
     *
     * @param objectMapper
     */
    public static void setJsonMapper(ObjectMapper objectMapper) {
        jsonMapper = objectMapper;
    }

    /**
     * Generate a Jackson module for all STIX objects (SDOs, SROs, Markings, bundle, observables, and observable extensions)
     * @return
     */
    public static SimpleModule generateStixSubTypesModule() {
        SimpleModule module = new SimpleModule();

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

        module.registerSubtypes(sdoClasses);
        module.registerSubtypes(sroClasses);
        module.registerSubtypes(dataMarkingClasses);
        module.registerSubtypes(bundleClasses);
        module.registerSubtypes(cyberObservableClasses);
        module.registerSubtypes(cyberObservableExtensionClasses);

        return module;
    }

    public static SimpleModule generateStixInstantModule(){
        SimpleModule module = new SimpleModule();
        module.addSerializer(StixInstant.class, new StixInstantSerializer());
        module.addDeserializer(StixInstant.class, new StixInstantDeserializer());
        return module;
    }

    public static SimpleModule generateStixBooleanModule(){
        SimpleModule module = new SimpleModule();
        module.addSerializer(StixBoolean.class, new StixBooleanSerializer());
        module.addDeserializer(StixBoolean.class, new StixBooleanDeserializer());
        return module;
    }

    public static BundleObject parseBundle(String bundleJsonString) throws IOException, StixParserValidationException {
       try {
           return getJsonMapper().readValue(bundleJsonString, BundleObject.class);
       } catch (IOException ex) {
        if (ex.getCause() != null && ValidationException.class.isAssignableFrom(ex.getCause().getClass())) {
            throw new StixParserValidationException((ValidationException) ex.getCause());
        } else {
            throw ex;
        }
    }
    }

    public static BundleableObject parseObject(String objectJsonString) throws IOException, StixParserValidationException {
        try {
            return getJsonMapper().readValue(objectJsonString, BundleableObject.class);
        } catch (IOException ex) {
            if (ex.getCause() != null && ValidationException.class.isAssignableFrom(ex.getCause().getClass())) {
                throw new StixParserValidationException((ValidationException) ex.getCause());
            } else {
                throw ex;
            }
        }
    }
}
