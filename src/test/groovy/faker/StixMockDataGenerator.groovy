package faker

import faker.configs.ObservedDataGeneratorConfig
import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.common.StixBoolean
import io.digitalstate.stix.common.StixInstant
import io.digitalstate.stix.coo.extension.types.*
import io.digitalstate.stix.coo.objects.*
import io.digitalstate.stix.coo.types.*
import io.digitalstate.stix.datamarkings.GranularMarking
import io.digitalstate.stix.datamarkings.MarkingDefinition
import io.digitalstate.stix.datamarkings.objects.Statement
import io.digitalstate.stix.datamarkings.objects.Tlp
import io.digitalstate.stix.sdo.DomainObject
import io.digitalstate.stix.sdo.objects.*
import io.digitalstate.stix.sdo.types.*
import io.digitalstate.stix.sro.objects.*
import io.digitalstate.stix.vocabulary.vocabularies.*
import net.andreinc.mockneat.MockNeat

import java.time.Instant
import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom

public class StixMockDataGenerator {

    // MockNeat object
    MockNeat mock = MockNeat.threadLocal()


    Instant commonLowerDate = Instant.ofEpochMilli(LocalDate.of(2000, 1, 1).toEpochDay())

    Instant generateRandomDate(Instant lower, Instant upper){
        return Instant.ofEpochMilli(ThreadLocalRandom.current()
                .longs(lower.toEpochMilli(), upper.toEpochMilli())
                .findAny()
                .getAsLong())
    }

    //
    // Mocks and Generators:
    //

    /**
     * Generates a random set of single word labels
     * @return
     */
    Set<String> generateRandomLabels() {
        Set<String> labels = new HashSet<>()

        mock.ints().range(1, 20).get().times {
            labels.add(mock.words().get())
        }
        return labels
    }

    /**
     * Generate a random Map of Custom Properties
     * @return
     */
    Map<String, Object> generateCustomProperties() {

        Map<String, Object> customProperties = new HashMap<>()

        mock.ints().range(0, 20).get().times {
            String key = mock.words().prepend("x_").get()
            switch (mock.ints().range(0, 5).get()) {
                case 0:
                    customProperties.put(key, mock.words().get())
                    break
                case 1:
                    customProperties.put(key, mock.words().accumulate(mock.ints().range(1, 100).get(), " ").get())
                    break
                case 2:
                    customProperties.put(key, mock.ints().range(0, 999999).get())
                    break
                case 3:
                    customProperties.put(key, mock.doubles().range(0.0, 999999.0).get())
                    break
                case 4:
                    customProperties.put(key, mock.bools().probability(50).get())
                    break
                case 5:
                    HashMap<String, String> map1 = new HashMap<>()
                    mock.ints().range(1, 30).get().times {
                        map1.put(mock.words().get(), mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
                    }
                    customProperties.put(key, map1)
                    break
            }
        }
        return customProperties
    }


    /**
     * Generate a random KillChain Phase
     * @return
     */
    KillChainPhase mockKillChainPhase() {
        return KillChainPhase.builder()
                .killChainName(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
                .phaseName(mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get())
                .build()
    }

    /**
     * Generate a random External Reference
     * @return
     */
    ExternalReference mockExternalReference() {
        ExternalReference.Builder builder = ExternalReference.builder()
                .sourceName(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 100).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.url(mock.urls().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHash("MD5", mock.hashes().md5().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHash("SHA-256", mock.hashes().sha256().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHash("SHA-512", mock.hashes().sha512().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHash("SHA-1", mock.hashes().sha1().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.externalId(mock.uuids().get())
        }

        return builder.build()
    }

    /**
     * Generate a random Attack pattern
     * @return
     */
    AttackPattern mockAttackPattern() {
        AttackPattern.Builder builder = AttackPattern.builder()
                .name(mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get())

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        builder.created(new StixInstant(objectCreated))

        if (mock.bools().probability(50).get()) {
            builder.modified(new StixInstant(generateRandomDate(commonLowerDate, objectCreated)))
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 15).get().times {
                builder.addKillChainPhase(mockKillChainPhase())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.addAllLabels(generateRandomLabels())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    /**
     * Generate a random Campaign
     * @return
     */
    Campaign mockCampaign() {
        Campaign.Builder builder = Campaign.builder()
                .name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 5).get().times {
                builder.addAliase(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
            }
        }

        Instant firstSeen = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.firstSeen(new StixInstant(firstSeen))
        }

        if (mock.bools().probability(50).get()) {
            builder.lastSeen(new StixInstant(generateRandomDate(firstSeen, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.objective(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.addAllLabels(generateRandomLabels())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    /**
     * Generate a random Course of Action
     * @return
     */
    CourseOfAction mockCourseOfAction() {
        CourseOfAction.Builder builder = CourseOfAction.builder()
                .name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addAction(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.addAllLabels(generateRandomLabels())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    /**
     * Generate a random Identity
     * @return
     */
    Identity mockIdentity() {
        Identity.Builder builder = Identity.builder()
                .name(mock.names().full(33.33).get())

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        builder.identityClass(mock.fromStrings(new IdentityClasses().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addSector(mock.fromStrings(new IndustrySectors().getAllTerms().toList()).get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.contactInformation("${mock.emails().get()} ${mock.departments().get()}")
        }

        if (mock.bools().probability(50).get()) {
            builder.addAllLabels(generateRandomLabels())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        //Note does not test against .createdByRef(<IdentitySDO>) to prevent possible recursion

        return builder.build()
    }

    Indicator mockIndicator() {
        Indicator.Builder builder = Indicator.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        builder.addLabel(mock.fromStrings(new IndicatorLabels().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        builder.pattern("SOME PATTERN GOES HERE")

        Instant validFrom = generateRandomDate(commonLowerDate, Instant.now())
        builder.validFrom(new StixInstant(validFrom))

        if (mock.bools().probability(50).get()) {
            builder.validUntil(new StixInstant(generateRandomDate(validFrom, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 15).get().times {
                builder.addKillChainPhase(mockKillChainPhase())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    IntrusionSet mockIntrusionSet() {
        IntrusionSet.Builder builder = IntrusionSet.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 5).get().times {
                builder.addAliase(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
            }
        }

        Instant firstSeen = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.firstSeen(new StixInstant(firstSeen))
        }

        if (mock.bools().probability(50).get()) {
            builder.lastSeen(new StixInstant(generateRandomDate(firstSeen, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 15).get().times {
                builder.addGoal(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.resourceLevel(mock.fromStrings(new AttackResourceLevels().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.primaryMotivation(mock.fromStrings(new AttackMotivations().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addSecondaryMotivation(mock.fromStrings(new AttackMotivations().getAllTerms().toList()).get())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    Malware mockMalware() {
        Malware.Builder builder = Malware.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        builder.addLabel(mock.fromStrings(new MalwareLabels().getAllTerms().toList()).get())

        builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 15).get().times {
                builder.addKillChainPhase(mockKillChainPhase())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    ObservedData mockObservedData(ObservedDataGeneratorConfig config = new ObservedDataGeneratorConfig()) {
        ObservedData.Builder builder = ObservedData.builder()

        Instant objectCreated = generateRandomDate(config.propCreatedLowerDate, config.propCreatedUpperDate)
        if (mock.bools().probability(config.propCreatedProbability).get()) {
            builder.created(new StixInstant(objectCreated, config.propCreatedDateSubsecondPrecision))
            if (mock.bools().probability(config.propModifiedProbability).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, config.propModifiedUpperDate), config.propModifiedSubsecondPrecision))
            }
        }

        Instant firstObserved = generateRandomDate(config.propFirstObservedLowerDate, config.propFirstObservedUpperDate)
        builder.firstObserved(new StixInstant(firstObserved, config.propFirstObservedSubsecondPrecision))

        builder.lastObserved(new StixInstant(generateRandomDate(firstObserved, config.propLastObservedUpperDate), config.propLastObservedSubsecondPrecision))

        builder.numberObserved(mock.ints().range(config.propNumberObservedLowerCount, config.propNumberObservedUpperCount).get())


        if (mock.bools().probability(config.artifactCoo.occurrence_probability).get()) {
            mock.ints().range(config.artifactCoo.occurs_count_lower, config.artifactCoo.occurs_count_upper).get().times {
                builder.addObject(mockArtifactCoo())
            }
        }

        if (mock.bools().probability(config.autonomousSystemCoo.occurrence_probability).get()) {
            mock.ints().range(config.autonomousSystemCoo.occurs_count_lower, config.autonomousSystemCoo.occurs_count_upper).get().times {
                builder.addObject(mockAutonomousSystemCoo())
            }
        }

        if (mock.bools().probability(config.directoryCoo.occurrence_probability).get()) {
            mock.ints().range(config.directoryCoo.occurs_count_lower, config.directoryCoo.occurs_count_upper).get().times {
                builder.addObject(mockDirectoryCoo())
            }
        }

        if (mock.bools().probability(config.domainNameCoo.occurrence_probability).get()) {
            mock.ints().range(config.domainNameCoo.occurs_count_lower, config.domainNameCoo.occurs_count_upper).get().times {
                builder.addObject(mockDomainNameCoo())
            }
        }

        if (mock.bools().probability(config.emailAddressCoo.occurrence_probability).get()) {
            mock.ints().range(config.emailAddressCoo.occurs_count_lower, config.emailAddressCoo.occurs_count_upper).get().times {
                builder.addObject(mockEmailAddressCoo())
            }
        }

        //@TODO Refactor to pass in Email address objects and artifacts
        if (mock.bools().probability(config.emailMessageCoo.occurrence_probability).get()) {
            mock.ints().range(config.emailMessageCoo.occurs_count_lower, config.emailMessageCoo.occurs_count_upper).get().times {
                builder.addObject(mockEmailMessageCoo())
            }
        }

        if (mock.bools().probability(config.fileCoo.occurrence_probability).get()) {
            mock.ints().range(config.fileCoo.occurs_count_lower, config.fileCoo.occurs_count_upper).get().times {
                builder.addObject(mockFileCoo())
            }
        }

        if (mock.bools().probability(config.ipv4AddressCoo.occurrence_probability).get()) {
            mock.ints().range(config.ipv4AddressCoo.occurs_count_lower, config.ipv4AddressCoo.occurs_count_upper).get().times {
                builder.addObject(mockIpv4AddressCoo())
            }
        }

        if (mock.bools().probability(config.ipv6AddressCoo.occurrence_probability).get()) {
            mock.ints().range(config.ipv6AddressCoo.occurs_count_lower, config.ipv6AddressCoo.occurs_count_upper).get().times {
                builder.addObject(mockIpv6AddressCoo())
            }
        }

        if (mock.bools().probability(config.macAddressCoo.occurrence_probability).get()) {
            mock.ints().range(config.macAddressCoo.occurs_count_lower, config.macAddressCoo.occurs_count_upper).get().times {
                builder.addObject(mockMacAddress())
            }
        }

        if (mock.bools().probability(config.mutexCoo.occurrence_probability).get()) {
            mock.ints().range(config.mutexCoo.occurs_count_lower, config.mutexCoo.occurs_count_upper).get().times {
                builder.addObject(mockMutexCoo())
            }
        }

        if (mock.bools().probability(config.networkTrafficCoo.occurrence_probability).get()) {
            mock.ints().range(config.networkTrafficCoo.occurs_count_lower, config.networkTrafficCoo.occurs_count_upper).get().times {
                builder.addObject(mockNetworkTrafficCoo())
            }
        }

        if (mock.bools().probability(config.processCoo.occurrence_probability).get()) {
            mock.ints().range(config.processCoo.occurs_count_lower, config.processCoo.occurs_count_upper).get().times {
                builder.addObject(mockProcessCoo())
            }
        }

        if (mock.bools().probability(config.softwareCoo.occurrence_probability).get()) {
            mock.ints().range(config.softwareCoo.occurs_count_lower, config.softwareCoo.occurs_count_upper).get().times {
                builder.addObject(mockSoftwareCoo())
            }
        }

        if (mock.bools().probability(config.urlCoo.occurrence_probability).get()) {
            mock.ints().range(config.urlCoo.occurs_count_lower, config.urlCoo.occurs_count_upper).get().times {
                builder.addObject(mockUrlCoo())
            }
        }

        if (mock.bools().probability(config.userAccountCoo.occurrence_probability).get()) {
            mock.ints().range(config.userAccountCoo.occurs_count_lower, config.userAccountCoo.occurs_count_upper).get().times {
                builder.addObject(mockUserAccountCoo())
            }
        }

        if (mock.bools().probability(config.windowsRegisteryKeyCoo.occurrence_probability).get()) {
            mock.ints().range(config.windowsRegisteryKeyCoo.occurs_count_lower, config.windowsRegisteryKeyCoo.occurs_count_upper).get().times {
                builder.addObject(mockWindowsRegistryKeyCoo())
            }
        }

        if (mock.bools().probability(config.x509CertificateCoo.occurrence_probability).get()) {
            mock.ints().range(config.x509CertificateCoo.occurs_count_lower, config.x509CertificateCoo.occurs_count_upper).get().times {
                builder.addObject(mockX509CertificateCoo())
            }
        }


        if (mock.bools().probability(config.externalReferences.occurrence_probability).get()) {
            mock.ints().range(config.externalReferences.occurs_count_lower, config.externalReferences.occurs_count_upper).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(config.propRevokedProbability).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(config.propCustomPropsProbability).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(config.propCreatedByRefProbability).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(config.objectMarkings.occurrence_probability).get()) {
            mock.ints().range(config.objectMarkings.occurs_count_lower, config.objectMarkings.occurs_count_upper).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(config.granularMarkings.occurrence_probability).get()) {
            mock.ints().range(config.granularMarkings.occurs_count_lower, config.granularMarkings.occurs_count_upper).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        if (mock.bools().probability(config.labelsOccurrenceProbability).get()) {
            builder.labels(generateRandomLabels())
        }

        return builder.build()
    }

    // Cyber Observables

    Artifact mockArtifactCoo() {
        Artifact.Builder builder = Artifact.builder()

        if (mock.bools().probability(50).get()) {
            builder.mimeType(mock.mimes().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.payloadBin(mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get())
        } else {
            builder.url(mock.urls().get())
            builder.putHash("SHA-256", mock.hashes().sha256().get())

            if (mock.bools().probability(33.33).get()) {
                builder.putHash("SHA-1", mock.hashes().sha1().get())
            }

            if (mock.bools().probability(33.33).get()) {
                builder.putHash("MD5", mock.hashes().md5().get())
            }
        }

        return builder.build()
    }

    AutonomousSystem mockAutonomousSystemCoo() {
        AutonomousSystem.Builder builder = AutonomousSystem.builder()

        builder.number(mock.longs().get())

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.rir(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        return builder.build()
    }

    Directory mockDirectoryCoo() {
        Directory.Builder builder = Directory.builder()

        builder.path(mock.words().accumulate(mock.ints().range(1, 5).get(), "/").get())

        if (mock.bools().probability(50).get()) {
            builder.pathEnc("csASCII")
        }

        Instant created = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(created))
        }

        Instant modified = generateRandomDate(created, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.modified(new StixInstant(modified))
        }

        if (mock.bools().probability(50).get()) {
            builder.accessed(new StixInstant(generateRandomDate(created, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                if (mock.bools().probability(50).get()) {
                    builder.addContainsRef(mockFileCoo().getObservableObjectKey())
                } else {
                    //@TODO add support for contains_refs with directories
                    // This will require that Creating a Directory COO has a param to
                    // set where other Contains refs are generated so a endless cannot happen.
                }
            }
        }

        return builder.build()
    }

    DomainName mockDomainNameCoo() {
        DomainName.Builder builder = DomainName.builder()

        builder.value(mock.domains().get())

        //@TODO Add resolves_to_refs mocking

        return builder.build()
    }

    EmailAddress mockEmailAddressCoo() {
        EmailAddress.Builder builder = EmailAddress.builder()

        builder.value(mock.emails().get())

        if (mock.bools().probability(50).get()) {
            builder.displayName(mock.names().full(33.33).get())
        }

        //@TODO Add belongs_to_ref mocking

        return builder.build()
    }

    EmailMessage mockEmailMessageCoo() {
        EmailMessage.Builder builder = EmailMessage.builder()

        if (mock.bools().probability(50).get()) {
            builder.isMultipart(true)
            mock.ints().range(1, 5).get().times {
                builder.addBodyMultipart(mockMimePartTypeCooType())
            }
        } else {
            builder.isMultipart(false)
            builder.body(mock.words().accumulate(mock.ints().range(1, 100).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.date(new StixInstant(generateRandomDate(commonLowerDate, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.contentType(mock.mimes().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.fromRef(mockEmailAddressCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            builder.senderRef(mockEmailAddressCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addToRef(mockEmailAddressCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addCcRef(mockEmailAddressCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addBccRef(mockEmailAddressCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.subject(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addReceivedLine(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.rawEmailRef(mockArtifactCoo().getObservableObjectKey())
        }

        return builder.build()
    }

    MimePartType mockMimePartTypeCooType() {
        MimePartType.Builder builder = MimePartType.builder()

        if (mock.bools().probability(50).get()) {
            builder.body(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        } else {
            if (mock.bools().probability(50).get()) {
                builder.bodyRawRef(mockArtifactCoo().getObservableObjectKey())
            } else {
                builder.bodyRawRef(mockFileCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.contentType(mock.mimes().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.contentDisposition(mock.words().get())
        }

        return builder.build()
    }

    File mockFileCoo() {
        File.Builder builder = File.builder()

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockNtfsFileExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockRasterImageFileExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockPdfFileExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockArchiveFileExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockWindowsPeBinaryFileExtensionCooExt())
                }
            }
        }

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(20).get()) {
                builder.putHash("MD5", mock.hashes().md5().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-256", mock.hashes().sha256().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-512", mock.hashes().sha512().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-1", mock.hashes().sha1().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.size(mock.longs().range(0, 999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.nameEnc(mock.words().get())
        }

        //@TODO hardcoded the hex value until a proper generator can be built.
        if (mock.bools().probability(50).get()) {
            builder.magicNumberHex("F9")
        }

        if (mock.bools().probability(50).get()) {
            builder.mimeType(mock.mimes().get())
        }

        Instant created = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(created))
        }

        if (mock.bools().probability(50).get()) {
            builder.modified(new StixInstant(generateRandomDate(created, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.accessed(new StixInstant(generateRandomDate(created, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.parentDirectoryRef(mockDirectoryCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            builder.isEncrypted(true)

            if (mock.bools().probability(50).get()) {
                builder.encryptionAlgorithm(mock.fromStrings(new EncryptionAlgorithms().getAllTerms().toList()).get())
            }

            if (mock.bools().probability(50).get()) {
                builder.decryptionKey(mock.words().accumulate(mock.ints().range(1, 20).get(), "").get())
            }
        }

        //@TODO come up with a simple use case that does not cause recursion for the "contains_refs" field

        return builder.build()
    }

    Ipv4Address mockIpv4AddressCoo() {
        Ipv4Address.Builder builder = Ipv4Address.builder()

        builder.value(mock.ipv4s().get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addResolvesToRef(mockMacAddress().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addBelongsToRef(mockAutonomousSystemCoo().getObservableObjectKey())
            }
        }

        return builder.build()
    }

    Ipv6Address mockIpv6AddressCoo() {
        Ipv6Address.Builder builder = Ipv6Address.builder()

        builder.value(mock.iPv6s().get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addResolvesToRef(mockMacAddress().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addBelongsToRef(mockAutonomousSystemCoo().getObservableObjectKey())
            }
        }

        return builder.build()
    }

    MacAddress mockMacAddress() {
        MacAddress.Builder builder = MacAddress.builder()

        builder.value(mock.macs().get())

        return builder.build()
    }

    Mutex mockMutexCoo() {
        Mutex.Builder builder = Mutex.builder()

        builder.name(mock.words().get())

        return builder.build()
    }

    NetworkTraffic mockNetworkTrafficCoo() {
        NetworkTraffic.Builder builder = NetworkTraffic.builder()

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockHttpRequestExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockTcpExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockIcmpExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockNetworkSocketExtensionCooExt())
                }
            }
        }

        Instant start = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.start(new StixInstant(start))
        }

        if (mock.bools().probability(50).get()) {
            builder.end(new StixInstant(generateRandomDate(start, Instant.now())))
        }

        // 33% true, 33% false, 33% never set / null:
        if (mock.bools().probability(33).get()) {
            builder.isActive(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isActive(false)
        }

        if (mock.bools().probability(50).get()) {
            switch (mock.ints().range(0, 3).get()) {
                case 0:
                    builder.srcRef(mockIpv4AddressCoo().getObservableObjectKey())
                    break
                case 1:
                    builder.srcRef(mockIpv6AddressCoo().getObservableObjectKey())
                    break
                case 2:
                    builder.srcRef(mockMacAddress().getObservableObjectKey())
                    break
                case 3:
                    builder.srcRef(mockDomainNameCoo().getObservableObjectKey())
                    break
            }
        }

        if (mock.bools().probability(50).get()) {
            switch (mock.ints().range(0, 3).get()) {
                case 0:
                    builder.dstRef(mockIpv4AddressCoo().getObservableObjectKey())
                    break
                case 1:
                    builder.dstRef(mockIpv6AddressCoo().getObservableObjectKey())
                    break
                case 2:
                    builder.dstRef(mockMacAddress().getObservableObjectKey())
                    break
                case 3:
                    builder.dstRef(mockDomainNameCoo().getObservableObjectKey())
                    break
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.srcPort(mock.ints().range(0, 65535).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.dstPort(mock.ints().range(0, 65535).get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addProtocol(mock.fromStrings("ipv4", "tcp", "http", "udp", "ipv6", "ssl", "https").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.srcByteCount(mock.longs().range(0, 999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.dstByteCount(mock.longs().range(0, 999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.srcPackets(mock.longs().range(0, 999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                String key = mock.uuids().get()
                if (mock.bools().probability(50).get()) {
                    builder.putIpFix(key, mock.words().get())
                } else {
                    builder.putIpFix(key, mock.ints().range(0, 999999999).get())
                }
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.srcPayloadRef(mockArtifactCoo().getObservableObjectKey())
        }

        //@TODO encapsulates_refs (List)

        //@TODO encapsulated_by_ref

        return builder.build()
    }

    Process mockProcessCoo() {
        Process.Builder builder = Process.builder()

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockWindowsProcessExtensionCooExt())
                }
            }

            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockWindowsServiceExtensionCooExt())
                }
            }
        }

        if (mock.bools().probability(33).get()) {
            builder.isHidden(true)
        } else {
            builder.isHidden(false)
        }

        if (mock.bools().probability(50).get()) {
            builder.pid(mock.longs().range(0, 999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(generateRandomDate(commonLowerDate, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.cwd(mock.words().accumulate(mock.ints().range(1, 5).get(), "/").prepend("/").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addArgument(mock.uuids().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.commandLine("${mock.words().get()} ${mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get()}")
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 20).get().times {
                builder.putEnvironmentVariable(mock.uuids().get(), mock.words().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addOpenedConnectionRef(mockNetworkTrafficCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.creatorUserRef(mockUserAccountCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            builder.binaryRef(mockFileCoo().getObservableObjectKey())
        }


        //@TODO parent_ref

        //@TODO child_refs


        return builder.build()
    }

    Software mockSoftwareCoo() {
        Software.Builder builder = Software.builder()

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.cpe("cpe:2.3:${mock.words().get()}")
        }

        //@TODO add better language dictionary support
        if (mock.bools().probability(50).get()) {
            builder.addLanguage("eng")
            if (mock.bools().probability(50).get()) {
                builder.addLanguage("fre")
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.vendor(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.version("${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}")
        }

        return builder.build()
    }

    Url mockUrlCoo() {
        Url.Builder builder = Url.builder()

        builder.value(mock.urls().get())

        return builder.build()
    }

    UserAccount mockUserAccountCoo() {
        UserAccount.Builder builder = UserAccount.builder()

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(50).get()) {
                mock.ints().range(1, 5).get().times {
                    builder.addExtension(mockUnixAccountExtensionCooExt())
                }
            }
        }

        builder.userId(mock.uuids().get())

        if (mock.bools().probability(50).get()) {
            builder.accountLogin(mock.names().last().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.accountType(mock.fromStrings(new AccountTypes().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.displayName(mock.words().get())
        }

        if (mock.bools().probability(33).get()) {
            builder.isServiceAccount(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isServiceAccount(false)
        }

        if (mock.bools().probability(33).get()) {
            builder.isPrivileged(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isPrivileged(false)
        }

        if (mock.bools().probability(33).get()) {
            builder.isCanEscalatePrivs(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isCanEscalatePrivs(false)
        }

        if (mock.bools().probability(33).get()) {
            builder.isDisabled(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isDisabled(false)
        }

        Instant accountCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.accountCreated(new StixInstant(accountCreated))
        }

        if (mock.bools().probability(50).get()) {
            builder.accountExpires(new StixInstant(generateRandomDate(accountCreated, Instant.now().plusMillis(1000000))))
        }

        if (mock.bools().probability(50).get()) {
            builder.passwordLastChanged(new StixInstant(generateRandomDate(accountCreated, Instant.now())))
        }

        Instant firstLogin = generateRandomDate(accountCreated, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.accountFirstLogin(new StixInstant(firstLogin))
        }

        if (mock.bools().probability(50).get()) {
            builder.accountLastLogin(new StixInstant(generateRandomDate(firstLogin, Instant.now())))
        }

        return builder.build()
    }

    WindowsRegistryKey mockWindowsRegistryKeyCoo() {
        WindowsRegistryKey.Builder builder = WindowsRegistryKey.builder()

        builder.key(mock.fromStrings("HKEY_LOCAL_MACHINE", "hkey_local_machine", "HKEY_CURRENT_USER", "hkey_current_user", "HKEY_CLASSES_ROOT", "hkey_classes_root", "HKEY_CURRENT_CONFIG", "hkey_current_config", "HKEY_PERFORMANCE_DATA", "hkey_performance_data", "HKEY_USERS", "hkey_users", "HKEY_DYN_DATA").get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addValue(mockWindowsRegistryValue())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.modified(new StixInstant(generateRandomDate(commonLowerDate, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.creatorUserRef(mockUserAccountCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            builder.numberOfSubkeys(mock.longs().range(0, 999999999).get())
        }

        return builder.build()
    }

    WindowsRegistryValue mockWindowsRegistryValue() {
        WindowsRegistryValue.Builder builder = WindowsRegistryValue.builder()

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.data(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.dataType(mock.fromStrings(new WindowsRegistryValueDataTypes().getAllTerms().toList()).get())
        }

        return builder.build()
    }

    X509Certificate mockX509CertificateCoo() {
        X509Certificate.Builder builder = X509Certificate.builder()

        if (mock.bools().probability(33).get()) {
            builder.isSelfSigned(true)
        } else {
            builder.isSelfSigned(false)
        }

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(20).get()) {
                builder.putHash("MD5", mock.hashes().md5().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-256", mock.hashes().sha256().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-512", mock.hashes().sha512().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-1", mock.hashes().sha1().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.version("${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}")
        }

        if (mock.bools().probability(50).get()) {
            builder.serialNumber(mock.ints().range(1, 9999999).get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.signatureAlgorithm(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.issuer(mock.words().get())
        }

        Instant validityNotBefore = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.validityNotBefore(new StixInstant(validityNotBefore))
        }

        if (mock.bools().probability(50).get()) {
            builder.validityNotAfter(new StixInstant(generateRandomDate(validityNotBefore, Instant.now().plusMillis(1000000))))
        }

        if (mock.bools().probability(50).get()) {
            builder.subject(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.subjectPublicKeyAlgorithm(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.subjectPublicKeyModulus(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.subjectPublicKeyExponent(mock.longs().get())
        }

        //@TODO x509_v3_extensions property

        return builder.build()
    }

    Report mockReport() {
        Report.Builder builder = Report.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        mock.ints().range(1, 5).get().times {
            builder.addLabel(mock.fromStrings(new ReportLabels().getAllTerms().toList()).get())
        }

        builder.name(mock.words().accumulate(mock.ints().range(1, 8).get(), " ").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        builder.published(new StixInstant(generateRandomDate(commonLowerDate, Instant.now())))

        mock.ints().range(1, 50).get().times {
            switch (mock.ints().range(1, 14).get()) {
                case 1:
                    builder.addObjectRef(mockAttackPattern())
                    break
                case 2:
                    builder.addObjectRef(mockCampaign())
                    break
                case 3:
                    builder.addObjectRef(mockCourseOfAction())
                    break
                case 4:
                    builder.addObjectRef(mockIdentity())
                    break
                case 5:
                    builder.addObjectRef(mockIndicator())
                    break
                case 6:
                    builder.addObjectRef(mockIntrusionSet())
                    break
                case 7:
                    builder.addObjectRef(mockMalware())
                    break
                case 8:
                    builder.addObjectRef(mockObservedData())
                    break
                case 9:
                    builder.addObjectRef(mockThreatActor())
                    break
                case 10:
                    builder.addObjectRef(mockTool())
                    break
                case 11:
                    builder.addObjectRef(mockVulnerability())
                    break
                case 12:
                    builder.addObjectRef(mockMarkingDefinition())
                    break
                case 13:
                    builder.addObjectRef(mockRelationship())
                    break
                case 14:
                    builder.addObjectRef(mockSighting())
                    break
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    ThreatActor mockThreatActor() {
        ThreatActor.Builder builder = ThreatActor.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        mock.ints().range(1, 5).get().times {
            builder.addLabel(mock.fromStrings(new ThreatActorLabels().getAllTerms().toList()).get())
        }

        builder.name(mock.names().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addAlias(mock.names().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addRole(mock.fromStrings(new ThreatActorRoles().getAllTerms().toList()).get())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGoal(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.sophistication(mock.fromStrings(new ThreatActorSophistication().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.resourceLevel(mock.fromStrings(new AttackResourceLevels().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.primaryMotivation(mock.fromStrings(new AttackMotivations().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addSecondaryMotivation(mock.fromStrings(new AttackMotivations().getAllTerms().toList()).get())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addPersonalMotivation(mock.fromStrings(new AttackMotivations().getAllTerms().toList()).get())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    Tool mockTool() {
        Tool.Builder builder = Tool.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addLabel(mock.fromStrings(new ToolLabels().getAllTerms().toList()).get())
            }
        }

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 15).get().times {
                builder.addKillChainPhase(mockKillChainPhase())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.toolVersion("${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}")
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    Vulnerability mockVulnerability() {
        Vulnerability.Builder builder = Vulnerability.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.labels(generateRandomLabels())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    Bundle mockBundle() {
        Bundle.Builder builder = Bundle.builder()

        mock.ints().range(1, 100).get().times {
            switch (mock.ints().range(1, 14).get()) {
                case 1:
                    builder.addObject(mockAttackPattern())
                    break
                case 2:
                    builder.addObject(mockCampaign())
                    break
                case 3:
                    builder.addObject(mockCourseOfAction())
                    break
                case 4:
                    builder.addObject(mockIdentity())
                    break
                case 5:
                    builder.addObject(mockIndicator())
                    break
                case 6:
                    builder.addObject(mockIntrusionSet())
                    break
                case 7:
                    builder.addObject(mockMalware())
                    break
                case 8:
                    builder.addObject(mockObservedData())
                    break
                case 9:
                    builder.addObject(mockThreatActor())
                    break
                case 10:
                    builder.addObject(mockTool())
                    break
                case 11:
                    builder.addObject(mockVulnerability())
                    break
                case 12:
                    builder.addObject(mockMarkingDefinition())
                    break
                case 13:
                    builder.addObject(mockRelationship())
                    break
                case 14:
                    builder.addObject(mockSighting())
                    break
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    MarkingDefinition mockMarkingDefinition() {
        MarkingDefinition.Builder builder = MarkingDefinition.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
        }

        String type = mock.fromStrings("tlp", "statement").get()

        builder.definitionType(type)

        switch (type) {
            case "tlp":
                builder.definition(mockTlpMakingObject())
                break
            case "statement":
                builder.definition(mockStatementMarkingObject())
                break
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        builder.build()
    }

    Statement mockStatementMarkingObject() {
        Statement.Builder builder = Statement.builder()

        builder.statement(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    Tlp mockTlpMakingObject() {
        Tlp.Builder builder = Tlp.builder()

        builder.tlp(mock.fromStrings(new TlpLevels().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    GranularMarking mockGranularMarking() {
        GranularMarking.Builder builder = GranularMarking.builder()

        builder.markingRef(mockMarkingDefinition())

        mock.ints().range(1, 10).get().times {
            builder.addSelector(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    Relationship mockRelationship() {
        Relationship.Builder builder = Relationship.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        switch (mock.ints().range(1, 11).get()) {
            case 1:
                builder.sourceRef(mockAttackPattern())

                switch (mock.ints().range(1, 2).get()) {
                    case 1:
                        builder.relationshipType("targets")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockIdentity())
                        } else {
                            builder.targetRef(mockVulnerability())
                        }
                        break
                    case 2:
                        builder.relationshipType("uses")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockMalware())
                        } else {
                            builder.targetRef(mockTool())
                        }
                        break
                }

                break

            case 2:
                builder.sourceRef(mockCampaign())

                switch (mock.ints().range(1, 3).get()) {
                    case 1:
                        builder.relationshipType("attributed-to")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockIntrusionSet())
                        } else {
                            builder.targetRef(mockThreatActor())
                        }
                        break
                    case 2:
                        builder.relationshipType("targets")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockIdentity())
                        } else {
                            builder.targetRef(mockVulnerability())
                        }
                        break
                    case 3:
                        builder.relationshipType("uses")
                        switch (mock.ints().range(1, 3).get()) {
                            case 1:
                                builder.targetRef(mockAttackPattern())
                                break
                            case 2:
                                builder.targetRef(mockMalware())
                                break
                            case 3:
                                builder.targetRef(mockTool())
                                break
                        }
                        break
                }
                break

            case 3:
                builder.sourceRef(mockCourseOfAction())
                builder.relationshipType("mitigates")

                switch (mock.ints().range(1, 4).get()) {
                    case 1:
                        builder.targetRef(mockAttackPattern())
                        break
                    case 2:
                        builder.targetRef(mockMalware())
                        break
                    case 3:
                        builder.targetRef(mockTool())
                        break
                    case 4:
                        builder.targetRef(mockVulnerability())
                        break
                }
                break

            case 4:
                builder.sourceRef(mockIndicator())
                builder.relationshipType("indicates")

                switch (mock.ints().range(1, 6).get()) {
                    case 1:
                        builder.targetRef(mockAttackPattern())
                        break
                    case 2:
                        builder.targetRef(mockCampaign())
                        break
                    case 3:
                        builder.targetRef(mockIntrusionSet())
                        break
                    case 4:
                        builder.targetRef(mockMalware())
                        break
                    case 5:
                        builder.targetRef(mockThreatActor())
                        break
                    case 6:
                        builder.targetRef(mockTool())
                        break
                }
                break

            case 5:
                builder.sourceRef(mockIntrusionSet())

                switch (mock.ints().range(1, 3).get()) {
                    case 1:
                        builder.relationshipType("attributed-to")
                        builder.targetRef(mockThreatActor())
                        break
                    case 2:
                        builder.relationshipType("targets")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockIdentity())
                        } else {
                            builder.targetRef(mockVulnerability())
                        }
                        break
                    case 3:
                        builder.relationshipType("uses")
                        switch (mock.ints().range(1, 3).get()) {
                            case 1:
                                builder.targetRef(mockAttackPattern())
                                break
                            case 2:
                                builder.targetRef(mockMalware())
                                break
                            case 3:
                                builder.targetRef(mockTool())
                                break
                        }
                        break
                }
                break

            case 6:
                builder.sourceRef(mockMalware())

                switch (mock.ints().range(1, 3).get()) {
                    case 1:
                        builder.relationshipType("targets")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockIdentity())
                        } else {
                            builder.targetRef(mockVulnerability())
                        }
                        break
                    case 2:
                        builder.relationshipType("uses")
                        builder.targetRef(mockTool())
                        break
                    case 3:
                        builder.relationshipType("variant-of")
                        builder.targetRef(mockMalware())
                        break
                }
                break

            case 7:
                builder.sourceRef(mockThreatActor())

                switch (mock.ints().range(1, 4).get()) {
                    case 1:
                        builder.relationshipType("attributed-to")
                        builder.targetRef(mockIdentity())
                        break
                    case 2:
                        builder.relationshipType("impersonates")
                        builder.targetRef(mockIdentity())
                        break
                    case 3:
                        builder.relationshipType("targets")
                        if (mock.bools().probability(50).get()) {
                            builder.targetRef(mockIdentity())
                        } else {
                            builder.targetRef(mockVulnerability())
                        }
                        break
                    case 4:
                        builder.relationshipType("uses")
                        switch (mock.ints().range(1, 3).get()) {
                            case 1:
                                builder.targetRef(mockAttackPattern())
                                break
                            case 2:
                                builder.targetRef(mockMalware())
                                break
                            case 3:
                                builder.targetRef(mockTool())
                                break
                        }
                        break
                }
                break
            case 8:
                builder.sourceRef(mockTool())

                builder.relationshipType("targets")
                switch (mock.ints().range(1, 2).get()) {
                    case 1:
                        builder.targetRef(mockIdentity())
                        break
                    case 2:
                        builder.targetRef(mockVulnerability())
                        break
                }
                break

            case 9:
                DomainObject object = mockRandomDomainObject()

                builder.sourceRef(object)

                builder.relationshipType("derived-from")

                builder.targetRef(mockRandomDomainObject(object.getType()))

                break

            case 10:
                DomainObject object = mockRandomDomainObject()

                builder.sourceRef(object)

                builder.relationshipType("duplicate-of")

                builder.targetRef(mockRandomDomainObject(object.getType()))

                break

            case 11:
                DomainObject object = mockRandomDomainObject()

                builder.sourceRef(object)

                builder.relationshipType("related-to")

                builder.targetRef(mockRandomDomainObject())

                break
        }

        // Extra details

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.labels(generateRandomLabels())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(new StixBoolean(true))
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        if (mock.bools().probability(50).get()) {
            builder.createdByRef(mockIdentity())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObjectMarkingRef(mockMarkingDefinition())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGranularMarking(mockGranularMarking())
            }
        }

        return builder.build()
    }

    Sighting mockSighting() {
        Sighting.Builder builder = Sighting.builder()

        Instant objectCreated = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.created(new StixInstant(objectCreated))
            if (mock.bools().probability(50).get()) {
                builder.modified(new StixInstant(generateRandomDate(objectCreated, Instant.now())))
            }
        }

        Instant firstSeen = generateRandomDate(commonLowerDate, Instant.now())
        if (mock.bools().probability(50).get()) {
            builder.firstSeen(new StixInstant(firstSeen))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.lastSeen(new StixInstant(generateRandomDate(firstSeen, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.count(mock.ints().range(0, 999999999).get())
        }

        builder.sightingOfRef(mockRandomDomainObject())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 30).get().times {
                builder.addObservedDataRef(mockObservedData())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 30).get().times {
                builder.addWhereSightedRef(mockIdentity())
            }
        }

        if (mock.bools().probability(20).get()) {
            builder.isSummary(new StixBoolean(true))
        }

        return builder.build()
    }

    DomainObject mockRandomDomainObject(String manualType) {

        String[] types = ["attack-pattern", "campaign",
                          "course-of-action", "identity",
                          "indicator", "intrusion-set",
                          "malware", "observed-data",
                          "threat-actor", "tool",
                          "vulnerability"]

        if (manualType == null) {
            manualType = mock.fromStrings(types).get()
        } else {
            if (!types.contains(manualType)) {
                throw new IllegalArgumentException("invalid manualType")
            }
        }

        switch (manualType) {
            case "attack-pattern":
                return mockAttackPattern()
            case "campaign":
                return mockCampaign()
            case "course-of-action":
                return mockCourseOfAction()
            case "identity":
                return mockIdentity()
            case "indicator":
                return mockIndicator()
            case "intrusion-set":
                return mockIntrusionSet()
            case "malware":
                return mockMalware()
            case "observed-data":
                return mockObservedData()
            case "threat-actor":
                return mockThreatActor()
            case "tool":
                return mockTool()
            case "vulnerability":
                return mockVulnerability()
        }
    }

    ArchiveFileExtension mockArchiveFileExtensionCooExt() {
        ArchiveFileExtension.Builder builder = ArchiveFileExtension.builder()

        if (mock.bools().probability(33).get()) {
            mock.ints().range(1, 2).get().times {
                builder.addContainsRef(mockFileCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.version("${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}")
        }

        if (mock.bools().probability(50).get()) {
            builder.comment(mock.words().accumulate(mock.ints().range(1, 20).get(), " ").get())
        }

        return builder.build()
    }

    HttpRequestExtension mockHttpRequestExtensionCooExt() {
        HttpRequestExtension.Builder builder = HttpRequestExtension.builder()

        builder.requestMethod(mock.fromStrings("GET", "PUT", "POST", "PATCH", "DELETE").get())

        builder.requestValue(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get().toString())

        builder.requestVersion(mock.fromStrings("1.0", "1.1", "2.0").get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                String key = mock.words().accumulate(mock.ints().range(2, 3).get(), "").get()
                String value = mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get()
                builder.putRequestHeader(key, value)
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.messageBodyLength(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.messageBodyDataRef(mockArtifactCoo().getObservableObjectKey())
        }

        return builder.build()
    }

    IcmpExtension mockIcmpExtensionCooExt() {
        IcmpExtension.Builder builder = IcmpExtension.builder()

        builder.icmpCodeHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())

        builder.ocmpTypeHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())

        return builder.build()
    }


    NetworkSocketExtension mockNetworkSocketExtensionCooExt() {
        NetworkSocketExtension.Builder builder = NetworkSocketExtension.builder()

        builder.addressFamily(mock.fromStrings(new NetworkSocketAddressFamilies().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            builder.blocking(true)
        }

        if (mock.bools().probability(50).get()) {
            builder.listening(true)
        }

        if (mock.bools().probability(50).get()) {
            builder.protocolFamily(mock.fromStrings(new NetworkSocketProtocolFamilies().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                String key = mock.words().accumulate(mock.ints().range(2, 3).get(), "").get()
                String value = mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get()
                builder.putOption("SO_${key}", value)
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.socketType(mock.fromStrings(new NetworkSocketTypes().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.socketDescriptor(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.socketHandle(mock.longs().range(0, 999999999999).get())
        }

        return builder.build()
    }

    NtfsFileExtenstion mockNtfsFileExtensionCooExt() {
        NtfsFileExtenstion.Builder builder = NtfsFileExtenstion.builder()

        if (mock.bools().probability(50).get()) {
            builder.sid(mock.words().get())
        } else {
            if (mock.bools().probability(50).get()) {
                builder.sid(mock.words().get())
            }
            mock.ints().range(1, 5).get().times {
                builder.addAlternateDataStream(mockNtfsAlternateDataStreamCooExtObj())
            }
        }
        return builder.build()
    }

    NtfsAlternateDataStream mockNtfsAlternateDataStreamCooExtObj() {
        NtfsAlternateDataStream.Builder builder = NtfsAlternateDataStream.builder()

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(20).get()) {
                builder.putHash("MD5", mock.hashes().md5().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-256", mock.hashes().sha256().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-512", mock.hashes().sha512().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-1", mock.hashes().sha1().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.size(mock.longs().range(0, 999999999999).get())
        }

        return builder.build()
    }

    PdfFileExtension mockPdfFileExtensionCooExt() {
        PdfFileExtension.Builder builder = PdfFileExtension.builder()

        if (mock.bools().probability(50).get()) {
            builder.version("${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}.${mock.ints().range(0, 5).get()}")
        }

        builder.isOptimized(mock.bools().probability(50).get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                String key = mock.words().accumulate(mock.ints().range(2, 3).get(), "").get()
                String value = mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get()
                builder.putDocumentInfoDict(key, value)
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.pdfId0(mock.words().accumulate(mock.ints().range(1, 2).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.pdfId1(mock.words().accumulate(mock.ints().range(1, 2).get(), "-").get())
        }

        return builder.build()
    }

    RasterImageFileExtension mockRasterImageFileExtensionCooExt() {
        RasterImageFileExtension.Builder builder = RasterImageFileExtension.builder()

        builder.imageHeight(mock.longs().range(0, 999999999999).get())

        if (mock.bools().probability(50).get()) {
            builder.imageWidth(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.bitsPerPixel(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.imageCompressionAlgorithm(mock.words().accumulate(mock.ints().range(1, 2).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                String key = mock.words().accumulate(mock.ints().range(2, 3).get(), "-").get()
                String value = mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get()
                builder.putExifTag(key, value)
            }
        }

        return builder.build()
    }

    TcpExtension mockTcpExtensionCooExt() {
        TcpExtension.Builder builder = TcpExtension.builder()

        builder.srcFlagsHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())

        builder.dstFlagsHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())

        return builder.build()
    }

    UnixAccountExtension mockUnixAccountExtensionCooExt() {
        UnixAccountExtension.Builder builder = UnixAccountExtension.builder()

        builder.gid(mock.longs().range(0, 999999999999).get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addGroup(mock.words().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.homeDir("./${mock.words().accumulate(mock.ints().range(1, 5).get(), "/").get()}")
        }

        if (mock.bools().probability(50).get()) {
            builder.shell("${mock.words().get()} ${mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get()}")
        }

        return builder.build()
    }

    WindowsPeBinaryFileExtension mockWindowsPeBinaryFileExtensionCooExt() {
        WindowsPeBinaryFileExtension.Builder builder = WindowsPeBinaryFileExtension.builder()

        builder.peType(mock.fromStrings(new WindowsPeBinaryTypes().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            builder.imphash(mock.hashes().md5().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.machineHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.numberOfSections(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.timeDateStamp(new StixInstant(generateRandomDate(commonLowerDate, Instant.now())))
        }

        if (mock.bools().probability(50).get()) {
            builder.pointerToSymbolTableHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.numberOfSymbols(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfOptionalHeader(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.characteristicsHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(20).get()) {
                builder.putFileHeaderHash("MD5", mock.hashes().md5().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putFileHeaderHash("SHA-256", mock.hashes().sha256().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putFileHeaderHash("SHA-512", mock.hashes().sha512().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putFileHeaderHash("SHA-1", mock.hashes().sha1().get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.optionalHeader(mockWindowsPeOptionalHeaderCooExtObj())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addSection(mockWindowsPeSectionCooExtObj())
            }
        }

        return builder.build()
    }

    WindowsPeOptionalHeader mockWindowsPeOptionalHeaderCooExtObj() {
        WindowsPeOptionalHeader.Builder builder = WindowsPeOptionalHeader.builder()

        builder.magicHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())

        if (mock.bools().probability(50).get()) {
            builder.majorLinkerVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.minorLinkerVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfCode(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfInitializedData(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.addressOfEntryPoint(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.baseOfCode(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.baseOfData(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.imageBase(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sectionAlignment(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.fileAlignment(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.majorOsVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.minorOsVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.majorImageVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.minorImageVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.majorSubsystemVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.minorSubsystemVersion(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.win32VersionValueHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfImage(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfHeaders(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.checksumHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.subsystemHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.dllCharacteristicsHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfStackReserve(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfStackCommit(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfHeapReserve(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.sizeOfHeapCommit(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.loaderFlagsHex(mock.chars().hex().get().toString() + mock.chars().hex().get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.numberOfRvaAndSizes(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(20).get()) {
                builder.putHash("MD5", mock.hashes().md5().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-256", mock.hashes().sha256().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-512", mock.hashes().sha512().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-1", mock.hashes().sha1().get())
            }
        }

        return builder.build()
    }

    WindowsPeSection mockWindowsPeSectionCooExtObj() {
        WindowsPeSection.Builder builder = WindowsPeSection.builder()

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.size(mock.longs().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.entropy(mock.floats().range(0, 999999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(20).get()) {
                builder.putHash("MD5", mock.hashes().md5().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-256", mock.hashes().sha256().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-512", mock.hashes().sha512().get())
            }

            if (mock.bools().probability(20).get()) {
                builder.putHash("SHA-1", mock.hashes().sha1().get())
            }
        }

        return builder.build()
    }

    WindowsProcessExtension mockWindowsProcessExtensionCooExt() {
        WindowsProcessExtension.Builder builder = WindowsProcessExtension.builder()

        if (mock.bools().probability(50).get()) {
            builder.isAslrEnabled(true)
        } else {
            builder.isAslrEnabled(false)
            builder.isDepEnabled(mock.bools().probability(50).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.priority(mock.words().accumulate(mock.ints().range(1, 3).get(), "_").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.ownerSid(mock.ints().range(1, 999999).get().toString())
        }

        if (mock.bools().probability(50).get()) {
            builder.windowTitle(mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                String key = mock.words().accumulate(mock.ints().range(2, 3).get(), "-").get()
                String value = mock.words().accumulate(mock.ints().range(1, 5).get(), " ").get()
                builder.putStartupInfo(key, value)
            }
        }

        return builder.build()
    }

    WindowsServiceExtension mockWindowsServiceExtensionCooExt() {
        WindowsServiceExtension.Builder builder = WindowsServiceExtension.builder()

        builder.serviceName(mock.words().accumulate(mock.ints().range(1, 3).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addDescription(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.displayName(mock.words().accumulate(mock.ints().range(1, 2).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.groupName(mock.words().accumulate(mock.ints().range(1, 2).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.serviceStartType(mock.fromStrings(new WindowsServiceStartTypes().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 10).get().times {
                builder.addServiceDllRef(mock.words().accumulate(mock.ints().range(1, 3).get(), "-").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.serviceType(mock.fromStrings(new WindowsServiceTypes().getAllTerms().toList()).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.serviceStatus(mock.fromStrings(new WindowsServiceStatuses().getAllTerms().toList()).get())
        }

        return builder.build()
    }
}