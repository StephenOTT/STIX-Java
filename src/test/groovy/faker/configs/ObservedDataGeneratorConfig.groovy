package faker.configs

import faker.configs.sdo.observeddata.ObservedData_ArtifactCooConfig
import faker.configs.sdo.observeddata.ObservedData_AutonomousSystemCooConfig
import faker.configs.sdo.observeddata.ObservedData_DirectoryCooConfig
import faker.configs.sdo.observeddata.ObservedData_DomainNameCooConfig
import faker.configs.sdo.observeddata.ObservedData_EmailAddressCooConfig
import faker.configs.sdo.observeddata.ObservedData_EmailMessageCooConfig
import faker.configs.sdo.observeddata.ObservedData_ExternalReferencesConfig
import faker.configs.sdo.observeddata.ObservedData_FileCooConfig
import faker.configs.sdo.observeddata.ObservedData_GranularMarkingConfig
import faker.configs.sdo.observeddata.ObservedData_Ipv4AddressCooConfig
import faker.configs.sdo.observeddata.ObservedData_Ipv6AddressCooConfig
import faker.configs.sdo.observeddata.ObservedData_MacAddressCooConfig
import faker.configs.sdo.observeddata.ObservedData_MutexCooConfig
import faker.configs.sdo.observeddata.ObservedData_NetworkTrafficCooConfig
import faker.configs.sdo.observeddata.ObservedData_ObjectMarkingsConfig
import faker.configs.sdo.observeddata.ObservedData_ProcessCooConfig
import faker.configs.sdo.observeddata.ObservedData_SoftwareCooConfig
import faker.configs.sdo.observeddata.ObservedData_UrlCooConfig
import faker.configs.sdo.observeddata.ObservedData_UserAccountCooConfig
import faker.configs.sdo.observeddata.ObservedData_WindowsRegistryKeyCooConfig
import faker.configs.sdo.observeddata.ObservedData_X509CertificateCooConfig

import java.time.Instant
import java.time.LocalDate

class ObservedDataGeneratorConfig {

    private Instant DEFAULT_LOWER_DATE = Instant.ofEpochMilli(LocalDate.of(2000, 1, 1).toEpochDay())
    private Instant DEFAULT_UPPER_DATE = Instant.now()
    private int DEFAULT_DATE_SUBSECOND_PRECISION = 3

    int propCreatedProbability = 50
    Instant propCreatedLowerDate = DEFAULT_LOWER_DATE
    Instant propCreatedUpperDate = DEFAULT_UPPER_DATE
    int propCreatedDateSubsecondPrecision = DEFAULT_DATE_SUBSECOND_PRECISION

    int propModifiedProbability = 50
    Instant propModifiedUpperDate = DEFAULT_UPPER_DATE
    int propModifiedSubsecondPrecision = DEFAULT_DATE_SUBSECOND_PRECISION

    int propRevokedProbability = 50

    int propCreatedByRefProbability = 50

    int propCustomPropsProbability = 50

    Instant propFirstObservedLowerDate = DEFAULT_LOWER_DATE
    Instant propFirstObservedUpperDate = DEFAULT_UPPER_DATE
    int propFirstObservedSubsecondPrecision = DEFAULT_DATE_SUBSECOND_PRECISION

    Instant propLastObservedUpperDate = DEFAULT_UPPER_DATE
    int propLastObservedSubsecondPrecision = DEFAULT_DATE_SUBSECOND_PRECISION

    int propNumberObservedUpperCount = 999999999
    int propNumberObservedLowerCount = 1

    ObservedData_ArtifactCooConfig artifactCoo = new ObservedData_ArtifactCooConfig()
    ObservedData_AutonomousSystemCooConfig autonomousSystemCoo = new ObservedData_AutonomousSystemCooConfig()
    ObservedData_DirectoryCooConfig directoryCoo = new ObservedData_DirectoryCooConfig()
    ObservedData_DomainNameCooConfig domainNameCoo = new ObservedData_DomainNameCooConfig()
    ObservedData_EmailAddressCooConfig emailAddressCoo = new ObservedData_EmailAddressCooConfig()
    ObservedData_EmailMessageCooConfig emailMessageCoo = new ObservedData_EmailMessageCooConfig()
    ObservedData_FileCooConfig fileCoo = new ObservedData_FileCooConfig()
    ObservedData_Ipv4AddressCooConfig ipv4AddressCoo = new ObservedData_Ipv4AddressCooConfig()
    ObservedData_Ipv6AddressCooConfig ipv6AddressCoo = new ObservedData_Ipv6AddressCooConfig()
    ObservedData_MacAddressCooConfig macAddressCoo = new ObservedData_MacAddressCooConfig()
    ObservedData_MutexCooConfig mutexCoo = new ObservedData_MutexCooConfig()
    ObservedData_NetworkTrafficCooConfig networkTrafficCoo = new ObservedData_NetworkTrafficCooConfig()
    ObservedData_ProcessCooConfig processCoo = new ObservedData_ProcessCooConfig()
    ObservedData_SoftwareCooConfig softwareCoo = new ObservedData_SoftwareCooConfig()
    ObservedData_UrlCooConfig urlCoo = new ObservedData_UrlCooConfig()
    ObservedData_UserAccountCooConfig userAccountCoo = new ObservedData_UserAccountCooConfig()
    ObservedData_WindowsRegistryKeyCooConfig windowsRegisteryKeyCoo = new ObservedData_WindowsRegistryKeyCooConfig()
    ObservedData_X509CertificateCooConfig x509CertificateCoo = new ObservedData_X509CertificateCooConfig()


    ObservedData_ExternalReferencesConfig externalReferences = new ObservedData_ExternalReferencesConfig()

    ObservedData_GranularMarkingConfig granularMarkings = new ObservedData_GranularMarkingConfig()

    ObservedData_ObjectMarkingsConfig objectMarkings = new ObservedData_ObjectMarkingsConfig()

    int labelsOccurrenceProbability = 30

}
