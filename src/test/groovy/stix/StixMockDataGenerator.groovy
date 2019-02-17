package stix

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.coo.objects.*
import io.digitalstate.stix.coo.types.MimePartType
import io.digitalstate.stix.coo.types.WindowsRegistryValue
import io.digitalstate.stix.datamarkings.GranularMarking
import io.digitalstate.stix.datamarkings.MarkingDefinition
import io.digitalstate.stix.datamarkings.Statement
import io.digitalstate.stix.datamarkings.Tlp
import io.digitalstate.stix.sdo.objects.*
import io.digitalstate.stix.sdo.types.ExternalReference
import io.digitalstate.stix.sdo.types.KillChainPhase
import io.digitalstate.stix.vocabulary.vocabularies.AccountTypes
import io.digitalstate.stix.vocabulary.vocabularies.AttackMotivations
import io.digitalstate.stix.vocabulary.vocabularies.AttackResourceLevels
import io.digitalstate.stix.vocabulary.vocabularies.EncryptionAlgorithms
import io.digitalstate.stix.vocabulary.vocabularies.IdentityClasses
import io.digitalstate.stix.vocabulary.vocabularies.IndicatorLabels
import io.digitalstate.stix.vocabulary.vocabularies.IndustrySectors
import io.digitalstate.stix.vocabulary.vocabularies.MalwareLabels
import io.digitalstate.stix.vocabulary.vocabularies.ReportLabels
import io.digitalstate.stix.vocabulary.vocabularies.ThreatActorLabels
import io.digitalstate.stix.vocabulary.vocabularies.ThreatActorRoles
import io.digitalstate.stix.vocabulary.vocabularies.ThreatActorSophistication
import io.digitalstate.stix.vocabulary.vocabularies.TlpLevels
import io.digitalstate.stix.vocabulary.vocabularies.ToolLabels
import io.digitalstate.stix.vocabulary.vocabularies.WindowsRegistryValueDataTypes
import net.andreinc.mockneat.MockNeat

import java.time.Instant
import java.time.ZoneOffset

trait StixMockDataGenerator {

    // MockNeat object
    MockNeat mock = MockNeat.threadLocal()

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
                    customProperties.put(key, mock.words().accumulate(mock.ints().range(1,100).get(), " ").get())
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
            builder.description(mock.words().accumulate(mock.ints().range(1,100).get(), " ").get())
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

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.revoked(true)
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

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 5).get().times {
                builder.addAliase(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.firstSeen(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.lastSeen(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.objective(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.revoked(true)
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

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.revoked(true)
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

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.revoked(true)
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

        builder.addLabel(mock.fromStrings(new IndicatorLabels().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
        }

        builder.pattern("SOME PATTERN GOES HERE")

        builder.validFrom(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.validUntil(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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
            builder.revoked(true)
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

        builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 5).get().times {
                builder.addAliase(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.firstSeen(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.lastSeen(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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
            builder.revoked(true)
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

        builder.addLabel(mock.fromStrings(new MalwareLabels().getAllTerms().toList()).get())

        builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.revoked(true)
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

    ObservedData mockObservedData() {
        ObservedData.Builder builder = ObservedData.builder()

        builder.firstObserved(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        builder.lastObserved(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))

        builder.numberObserved(mock.ints().range(1, 999999999).get())

        builder.addObject(mockArtifactCoo())

        //@TODO Replace the below with if statements per COO.  Then for each IF, do a range.get.Times{} to add N artifacts.
        // worry about the Refs in a later iteration of tests.
        builder.addObject(mockArtifactCoo())

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockAutonomousSystemCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockDirectoryCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockDomainNameCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockEmailAddressCoo())
            }
        }

        //@TODO Refactor to pass in Email address objects and artifacts
        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockEmailMessageCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockFileCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockIpv4AddressCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockIpv6AddressCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockMacAddress())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockMutexCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockNetworkTrafficCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockProcessCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockSoftwareCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockUrl())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockUserAccount())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockWindowsRegistryKeyCoo())
            }
        }

        if (mock.bools().probability(10).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addObject(mockX509CertificateCoo())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(true)
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

        if (mock.bools().probability(50).get()) {
            builder.created(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.modified(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.accessed(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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
            builder.body(mock.words().accumulate(mock.ints().range(1,100).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.date(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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
                builder.addReceivedLine(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.body(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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

        //@TODO Add Extensions support for `ntfs-ext, raster-image-ext, pdf-ext, archive-ext, windows-pebinary-ext`

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

        if (mock.bools().probability(50).get()) {
            builder.created(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.modified(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.accessed(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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
            mock.ints().range(1,10).get().times {
                builder.addResolvesToRef(mockMacAddress().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addBelongsToRef(mockAutonomousSystemCoo().getObservableObjectKey())
            }
        }

        return builder.build()
    }

    Ipv6Address mockIpv6AddressCoo() {
        Ipv6Address.Builder builder = Ipv6Address.builder()

        builder.value(mock.iPv6s().get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addResolvesToRef(mockMacAddress().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
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
            builder.start(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.end(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        // 33% true, 33% false, 33% never set / null:
        if (mock.bools().probability(33).get()) {
            builder.isActive(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isActive(false)
        }

        if (mock.bools().probability(50).get()) {
            switch (mock.ints().range(0,3).get()){
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
            switch (mock.ints().range(0,3).get()){
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
            mock.ints().range(1,10).get().times {
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

        if (mock.bools().probability(33).get()) {
            builder.isHidden(true)
        }

        if (mock.bools().probability(33).get()) {
            builder.isHidden(false)
        }

        if (mock.bools().probability(50).get()) {
            builder.pid(mock.longs().range(0,999999999).get())
        }

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.created(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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
            builder.commandLine("${mock.words().get()} ${mock.words().accumulate(mock.ints().range(1,5).get(), " ").get()}")
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
            builder.creatorUserRef(mockUserAccount().getObservableObjectKey())
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
            builder.version("${mock.ints().range(0,5).get()}.${mock.ints().range(0,5).get()}.${mock.ints().range(0,5).get()}")
        }

        return builder.build()
    }

    Url mockUrl() {
        Url.Builder builder = Url.builder()

        builder.value(mock.urls().get())

        return builder.build()
    }

    UserAccount mockUserAccount() {
        UserAccount.Builder builder = UserAccount.builder()

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

        if (mock.bools().probability(50).get()) {
            builder.accountCreated(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.accountExpires(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.passwordLastChanged(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.accountFirstLogin(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.accountLastLogin(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        return builder.build()
    }

    WindowsRegistryKey mockWindowsRegistryKeyCoo() {
        WindowsRegistryKey.Builder builder = WindowsRegistryKey.builder()

        builder.key(mock.fromStrings("HKEY_LOCAL_MACHINE","hkey_local_machine","HKEY_CURRENT_USER","hkey_current_user","HKEY_CLASSES_ROOT","hkey_classes_root","HKEY_CURRENT_CONFIG","hkey_current_config","HKEY_PERFORMANCE_DATA","hkey_performance_data","HKEY_USERS","hkey_users","HKEY_DYN_DATA").get())

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addValue(mockWindowsRegistryValue())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.modified(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.creatorUserRef(mockUserAccount().getObservableObjectKey())
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
            builder.version("${mock.ints().range(0,5).get()}.${mock.ints().range(0,5).get()}.${mock.ints().range(0,5).get()}")
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

        if (mock.bools().probability(50).get()) {
            builder.validityNotBefore(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        //@TODO This data will fail tests in the future as it create dates that are BEFORE the firstSeen.  Not currently enforced
        if (mock.bools().probability(50).get()) {
            builder.validityNotAfter(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
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

        mock.ints().range(1, 5).get().times {
            builder.addLabel(mock.fromStrings(new ReportLabels().getAllTerms().toList()).get())
        }

        builder.name(mock.words().accumulate(mock.ints().range(1, 8).get(), " ").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
        }

        builder.published(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))

        mock.ints().range(1, 50).get().times {
            switch (mock.ints().range(0, 11).get()) {
                case 0:
                    builder.addObjectRef(mockAttackPattern())
                    break
                case 1:
                    builder.addObjectRef(mockCampaign())
                    break
                case 2:
                    builder.addObjectRef(mockCourseOfAction())
                    break
                case 3:
                    builder.addObjectRef(mockIdentity())
                    break
                case 4:
                    builder.addObjectRef(mockIndicator())
                    break
                case 5:
                    builder.addObjectRef(mockIntrusionSet())
                    break
                case 6:
                    builder.addObjectRef(mockMalware())
                    break
                case 7:
                    builder.addObjectRef(mockObservedData())
                    break
                case 8:
                    builder.addObjectRef(mockThreatActor())
                    break
                case 9:
                    builder.addObjectRef(mockTool())
                    break
                case 10:
                    builder.addObjectRef(mockVulnerability())
                    break
                case 11:
                    builder.addObjectRef(mockMarkingDefinition())
                    break
                //@TODO add future support for references to other Report SDOs
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(true)
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

        mock.ints().range(1, 5).get().times {
            builder.addLabel(mock.fromStrings(new ThreatActorLabels().getAllTerms().toList()).get())
        }

        builder.name(mock.names().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
                builder.addGoal(mock.words().accumulate(mock.ints().range(1,10).get(), " ").get())
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
            builder.revoked(true)
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

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 5).get().times {
                builder.addLabel(mock.fromStrings(new ToolLabels().getAllTerms().toList()).get())
            }
        }

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1, 15).get().times {
                builder.addKillChainPhase(mockKillChainPhase())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.toolVersion("${mock.ints().range(0,5).get()}.${mock.ints().range(0,5).get()}.${mock.ints().range(0,5).get()}")
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(0, 10).get().times {
                builder.addExternalReferences(mockExternalReference())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.revoked(true)
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

        builder.name(mock.words().get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1,50).get(), " ").get())
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
            builder.revoked(true)
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
            switch (mock.ints().range(0, 11).get()) {
                case 0:
                    builder.addObject(mockAttackPattern())
                    break
                case 1:
                    builder.addObject(mockCampaign())
                    break
                case 2:
                    builder.addObject(mockCourseOfAction())
                    break
                case 3:
                    builder.addObject(mockIdentity())
                    break
                case 4:
                    builder.addObject(mockIndicator())
                    break
                case 5:
                    builder.addObject(mockIntrusionSet())
                    break
                case 6:
                    builder.addObject(mockMalware())
                    break
                case 7:
                    builder.addObject(mockObservedData())
                    break
                case 8:
                    builder.addObject(mockThreatActor())
                    break
                case 9:
                    builder.addObject(mockTool())
                    break
                case 10:
                    builder.addObject(mockVulnerability())
                    break
                case 11:
                    builder.addObjectRef(mockMarkingDefinition())
                    break
            //@TODO Add support for SROs
            //@TODO Add support for Data Markings
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    MarkingDefinition mockMarkingDefinition(){
        MarkingDefinition.Builder builder = MarkingDefinition.builder()

        String type = mock.fromStrings("tlp", "statement").get()

        builder.definitionType(type)

        switch (type){
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

    Statement mockStatementMarkingObject(){
        Statement.Builder builder = Statement.builder()

        builder.statement(mock.words().accumulate(mock.ints().range(1,30).get(), " ").get())

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    Tlp mockTlpMakingObject(){
        Tlp.Builder builder = Tlp.builder()

        builder.tlp(mock.fromStrings(new TlpLevels().getAllTerms().toList()).get())

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

    GranularMarking mockGranularMarking(){
        GranularMarking.Builder builder = GranularMarking.builder()

        builder.markingRef(mockMarkingDefinition())

        mock.ints().range(1,10).get().times {
            builder.addSelector(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
        }

        return builder.build()
    }

}