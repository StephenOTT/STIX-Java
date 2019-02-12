package stix.sdo

import io.digitalstate.stix.sdo.objects.AttackPattern
import io.digitalstate.stix.sdo.types.ExternalReference
import io.digitalstate.stix.sdo.types.KillChainPhase
import net.andreinc.mockneat.MockNeat

trait StixMockDataGenerator {

    MockNeat mock = MockNeat.threadLocal()

    KillChainPhase generateKillChainPhase(){
        return KillChainPhase.builder()
            .killChainName(mock.words().accumulate(mock.ints().range(1,5).get(), "-").get())
            .phaseName(mock.words().accumulate(mock.ints().range(1,5).get(), " ").get())
            .build()
    }

    ExternalReference generateExternalReference(){
        ExternalReference.Builder builder = ExternalReference.builder()
            .sourceName(mock.words().get())

        if (mock.bools().probability(50).get()){
            builder.description(mock.words().accumulate(mock.ints().range(1,10).get(), " ").get())
        }

        if (mock.bools().probability(50).get()){
            builder.url(mock.urls().get())
        }

        if (mock.bools().probability(20).get()){
            builder.putHashe("MD5", mock.hashes().md5().get())
        }

        if (mock.bools().probability(20).get()){
            builder.putHashe("SHA-256", mock.hashes().sha256().get())
        }

        if (mock.bools().probability(20).get()){
            builder.putHashe("SHA-512", mock.hashes().sha512().get())
        }

        if (mock.bools().probability(20).get()){
            builder.putHashe("SHA-1", mock.hashes().sha1().get())
        }

        if (mock.bools().probability(50).get()){
            builder.externalId(mock.uuids().get())
        }

        return builder.build()
    }

    AttackPattern generateAttackPattern(){
        AttackPattern.Builder builder =  AttackPattern.builder()
                .name(mock.words().accumulate(mock.ints().range(1,5).get(), " ").get())

        if (mock.bools().probability(50).get()){
            builder.description(mock.words().accumulate(mock.ints().range(1,30).get(), " ").get())
        }

        if (mock.bools().probability(50).get()){
            builder.addKillChainPhase(generateKillChainPhase())
        }

        if (mock.bools().probability(50).get()){
            builder.addKillChainPhase(generateKillChainPhase())
        }

        if (mock.bools().probability(50).get()){
            builder.addKillChainPhases(generateKillChainPhase(), generateKillChainPhase())
        }

        if (mock.bools().probability(50).get()){
            builder.addLabel(mock.words().get())
        }

        if (mock.bools().probability(50).get()){
            builder.addLabel(mock.words().get())
        }

        if (mock.bools().probability(50).get()){
            mock.ints().range(0,10).get().each {
                builder.addExternalReferences(generateExternalReference())
            }
        }

        if (mock.bools().probability(50).get()){
            builder.revoked(true)
        }

        if (mock.bools().probability(50).get()) {
            builder.putCustomProperty("x_dog", "cat")
        }

        //@TODO Created By SDO

        return builder.build()
    }

}