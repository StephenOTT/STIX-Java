package stix.sdo

import io.digitalstate.stix.coo.objects.Artifact
import io.digitalstate.stix.coo.objects.AutonomousSystem
import io.digitalstate.stix.coo.objects.Directory
import io.digitalstate.stix.coo.objects.DomainName
import io.digitalstate.stix.coo.objects.EmailAddress
import io.digitalstate.stix.coo.objects.EmailMessage
import io.digitalstate.stix.coo.types.MimePartType
import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Campaign
import io.digitalstate.stix.sdo.objects.CourseOfAction
import io.digitalstate.stix.sdo.objects.Identity
import io.digitalstate.stix.sdo.objects.Indicator
import io.digitalstate.stix.sdo.objects.IntrusionSet
import io.digitalstate.stix.sdo.objects.Malware
import io.digitalstate.stix.sdo.objects.ObservedData
import io.digitalstate.stix.sdo.types.ExternalReference
import io.digitalstate.stix.sdo.types.KillChainPhase
import io.digitalstate.stix.vocabularies.AttackMotivations
import io.digitalstate.stix.vocabularies.AttackResourceLevels
import io.digitalstate.stix.vocabularies.IdentityClasses
import io.digitalstate.stix.vocabularies.IndicatorLabels
import io.digitalstate.stix.vocabularies.IndustrySectors
import io.digitalstate.stix.vocabularies.MalwareLabels
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

        mock.ints().range(0, 20).get().times {
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
            String key = "x_" + mock.words().get()
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
            builder.description(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
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
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
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
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
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
            builder.revoked(true)
        }

        if (mock.bools().probability(50).get()) {
            builder.customProperties(generateCustomProperties())
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
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
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
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
        }

        List<String> identityClasses = new IdentityClasses().getAllTerms().toList()
        Collections.shuffle(identityClasses)
        builder.identityClass(identityClasses.first())

        if (mock.bools().probability(50).get()) {
            List<String> industrySectors = new IndustrySectors().getAllTerms().toList()
            Collections.shuffle(industrySectors)
            mock.ints().range(0, 5).get().times { i ->
                builder.addSector(industrySectors.get(i))
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

        //Note does not test against .createdByRef(<IdentitySDO>) to prevent possible recursion

        return builder.build()
    }

    Indicator mockIndicator() {
        Indicator.Builder builder = Indicator.builder()

        List<String> indicatorLabels = new IndicatorLabels().getAllTerms().toList()
        Collections.shuffle(indicatorLabels)
        builder.addLabel(indicatorLabels.first())

        if (mock.bools().probability(50).get()) {
            builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
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

        return builder.build()
    }

    IntrusionSet mockIntrusionSet() {
        IntrusionSet.Builder builder = IntrusionSet.builder()

        builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
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
            List<String> attackResourceLevels = new AttackResourceLevels().getAllTerms().toList()
            Collections.shuffle(attackResourceLevels)
            builder.resourceLevel(attackResourceLevels.first())
        }

        if (mock.bools().probability(50).get()) {
            List<String> attackMotivations = new AttackMotivations().getAllTerms().toList()
            Collections.shuffle(attackMotivations)
            builder.primaryMotivation(attackMotivations.first())
        }

        if (mock.bools().probability(50).get()) {
            List<String> attackMotivations = new AttackMotivations().getAllTerms().toList()
            Collections.shuffle(attackMotivations)
            mock.ints().range(1, 5).get().times { i ->
                builder.addSecondaryMotivation(attackMotivations.get(i))
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

        return builder.build()
    }

    Malware mockMalware() {
        Malware.Builder builder = Malware.builder()

        List<String> malwareLabels = new MalwareLabels().getAllTerms().toList()
        Collections.shuffle(malwareLabels)
        builder.addLabel(malwareLabels.first())

        builder.name(mock.words().accumulate(mock.ints().range(1, 5).get(), "-").get())

        if (mock.bools().probability(50).get()) {
            builder.description(mock.words().accumulate(mock.ints().range(1, 30).get(), " ").get())
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

        return builder.build()
    }

    // Cyber Observables

    Artifact mockArtifactCoo() {
        Artifact.Builder builder = Artifact.builder()

        List<String> types = ["application", "audio", "font", "image", "message", "model", "multipart", "text", "video"]
        Collections.shuffle(types)

        if (mock.bools().probability(50).get()) {
            builder.mimeType("${types.first()}/some-file-mime-type")
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

    AutonomousSystem mockAutonomousSystemCoo(){
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

    Directory mockDirectoryCoo(){
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

        //@TODO Add contains_ref mocking

        return builder.build()
    }

    DomainName mockDomainNameCoo(){
        DomainName.Builder builder = DomainName.builder()

        builder.value(mock.domains().get())

        //@TODO Add resolves_to_refs mocking

        return builder.build()
    }

    EmailAddress mockEmailAddressCoo(){
        EmailAddress.Builder builder = EmailAddress.builder()

        builder.value(mock.emails().get())

        if (mock.bools().probability(50).get()) {
            builder.displayName(mock.names().full(33.33).get())
        }

        //@TODO Add belongs_to_ref mocking

        return builder.build()
    }

    EmailMessage mockEmailMessageCoo(){
        EmailMessage.Builder builder = EmailMessage.builder()

        if (mock.bools().probability(50).get()) {
            builder.isMultipart(true)
            mock.ints().range(1,5).get().times {
                builder.addBodyMultipart(mockMimePartTypeCooType())
            }
        } else {
            builder.isMultipart(false)
            builder.body(mock.words().accumulate(mock.ints().range(1, 50).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            builder.date(Instant.from(mock.localDates().get().atStartOfDay().toInstant(ZoneOffset.UTC)))
        }

        if (mock.bools().probability(50).get()) {
            builder.contentType(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.fromRef(mockEmailAddressCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            builder.senderRef(mockEmailAddressCoo().getObservableObjectKey())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addToRef(mockEmailAddressCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addCcRef(mockEmailAddressCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addBccRef(mockEmailAddressCoo().getObservableObjectKey())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.subject(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            mock.ints().range(1,10).get().times {
                builder.addReceivedLine(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.rawEmailRef(mockArtifactCoo().getObservableObjectKey())
        }

        return builder.build()
    }

    MimePartType mockMimePartTypeCooType(){
        MimePartType.Builder builder = MimePartType.builder()

        if (mock.bools().probability(50).get()) {
            builder.body(mock.words().accumulate(mock.ints().range(1, 10).get(), " ").get())
        }

        if (mock.bools().probability(50).get()) {
            if (mock.bools().probability(50).get()) {
                builder.bodyRawRef(mockArtifactCoo().getObservableObjectKey())
            } else {
                //@TODO MOCK FileCoo
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.contentType(mock.words().get())
        }

        if (mock.bools().probability(50).get()) {
            builder.contentDisposition(mock.words().get())
        }

        return builder.build()
    }

}