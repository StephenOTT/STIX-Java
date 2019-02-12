package stix.sdo

import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.objects.Identity
import io.digitalstate.stix.sdo.types.ExternalReference
import io.digitalstate.stix.sdo.types.KillChainPhase
import io.digitalstate.stix.vocabularies.IdentityClasses
import io.digitalstate.stix.vocabularies.IndustrySectors
import net.andreinc.mockneat.MockNeat

trait StixMockDataGenerator {

    // MockNeat object
    MockNeat mock = MockNeat.threadLocal()

    //
    // Mocks and Generators:
    //

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
            builder.putHashe("MD5", mock.hashes().md5().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHashe("SHA-256", mock.hashes().sha256().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHashe("SHA-512", mock.hashes().sha512().get())
        }

        if (mock.bools().probability(20).get()) {
            builder.putHashe("SHA-1", mock.hashes().sha1().get())
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
            builder.addKillChainPhase(mockKillChainPhase())
        }

        if (mock.bools().probability(50).get()) {
            builder.addKillChainPhase(mockKillChainPhase())
        }

        if (mock.bools().probability(50).get()) {
            builder.addKillChainPhases(mockKillChainPhase(), mockKillChainPhase())
        }

        if (mock.bools().probability(50).get()) {
            builder.addLabel(mock.words().get())
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
            mock.ints().range(0, 5).get().times { i->
                builder.addSector(industrySectors.get(i))
            }
        }

        if (mock.bools().probability(50).get()) {
            builder.contactInformation("${mock.emails().get()} ${mock.departments().get()}")
        }

        if (mock.bools().probability(50).get()) {
            builder.addLabel(mock.words().get())
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


}