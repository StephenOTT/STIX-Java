package stix.sdo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.AttackPattern
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import stix.StixMockDataGenerator

class AttackPatternSpec extends Specification implements StixMockDataGenerator {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "Generate Attack Pattern Data: Run: '#i'"() {
        when: "Generating Attack Pattern Data"
            AttackPattern originalAttackPattern = mockAttackPattern()
            println "Original Object: ${originalAttackPattern.toString()}"

        then: "Convert Attack Pattern to Json"
            JsonNode originalJson = mapper.readTree(originalAttackPattern.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Attack Pattern Object"
            AttackPattern parsedAttackPattern = (AttackPattern)StixParsers.parseObject(originalJsonString)
            println "Parsed Object: ${parsedAttackPattern}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalAttackPattern == parsedAttackPattern

        then: "Convert Parsed Attack Pattern Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedAttackPattern.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)

        where:
            i << (1..100)
    }

    def "test no sub-second created timestamp"() {
        when:

        String rawJson = "{\"type\":\"attack-pattern\",\"id\":\"attack-pattern--4cd20885-3c39-4c01-b298-fe2dcca94f22\",\"created\":\"2019-04-12T09:16:58Z\",\"modified\":\"2019-04-12T09:16:58.437Z\",\"revoked\":false,\"labels\":[\"reconciler\",\"slanders\",\"ectopia\",\"underpaid\",\"fanions\"],\"external_references\":[{\"source_name\":\"moneyless\",\"description\":\"insensately objurgated offishly gonad finely acerous shanghaied galliambic some fells effectuate laigh fetial threaps fro refutably hibernating mute contentiously variably soldierly glazed triform draught sternward slap worst thermostats depicture straiten clowns chicly found adrift due pitiably eligibly pappy ethnolinguist jubilantly smug scissel cheat grim yare abnormally fuss salably robotizes gurnard ritenuto spores beside cyanotype disepalous where encodes colossally thuddingly normatively lyrically cuneate troughs briefly matutinal enslaving tribuneships monthly peke caracoled vizor exchangeably sunbow lapidaries cuspidated accompany left peacefully bushellers unlovable amphisbaenic stopped masculinize provocative yore redintegrates meaningful spang subsumable anthologise deploringly allegorise inquiringly kachina cucullate dure bucketful\",\"url\":\"http://www.guardersreynaldo.edu\",\"hashes\":{\"SHA-1\":\"406fc6ef2e8b75ac187bf0c4600c750ee7f41f8d\"},\"external_id\":\"5cb29105-f7a3-4220-b108-8a096e852a13\"},{\"source_name\":\"foul\",\"description\":\"redriven daintily rejects roughcast unblemished bene absolve passably costively effeminised separately plump gazelle mulcts anon worsts middling deafeningly frequentation lumines grips adminicle dichotomized weirs sprig stalls lethargic glossologists puncturing forgettable inquirers ingenerate wordy bung scallop demobilized wreath cruds ugli\",\"hashes\":{\"MD5\":\"e23da6dc5fcf066da2d78aa756e76c1a\"},\"external_id\":\"45e821ac-4e91-40da-bd19-3cb8eb2b7fac\"},{\"source_name\":\"tabu\",\"description\":\"disenfranchised abnegating starred squarrose decolor circumscriptive subentire effectless reversely acoustical minimally bizarrely licht lived swith granolithic gyp peccantly transcriptively handbags decently focuses sectionally choice freshly haruspical extractant bolsters extirpative bucktooth architect symbolics lathlike glyphs err trephine weirdie peristomal prisms connate protolithic fyrd deathly\",\"url\":\"http://www.hoggedwendell.org\",\"hashes\":{\"SHA-1\":\"f48f161e4dcce0673b54a541a51a541957e15bb6\"}},{\"source_name\":\"siderite\",\"hashes\":{\"SHA-512\":\"697d9941b378ff02ee9ca411f5a99f6b5c056de32681dfd6e6e872bdf9bf7cf2bb8d0f21b5eb9fee5ce56bde651c3981d5bd8356b28b2f7498ce17628b9ac503\"},\"external_id\":\"b0f7df80-0103-4960-ae69-fdbf92391c6f\"},{\"source_name\":\"headfirst\",\"description\":\"queenly staid processors stacked wham stigmatized guarantee teeter rungs mentally prill fakes amortizing lickerishly disinfectant generously electrotypes educated\",\"url\":\"http://www.forcedlylunch.io\",\"hashes\":{\"SHA-512\":\"88e6ba3b9e992681b9f08006eec5ca96350d554c9981d3d2b5c66645430efb6feb079d379c148f94b624f11c7a448ccc8aedc4236780656321958f17ead82fba\"},\"external_id\":\"72c59882-dfbe-40ce-86eb-10dbc5ae074f\"},{\"source_name\":\"jarosite\",\"url\":\"http://www.gaumlessmarylee.io\",\"external_id\":\"8456502a-e570-4b0e-9279-afb0666637e2\"},{\"source_name\":\"falconine\",\"description\":\"gey restively toughen permute malingers abroad thin foul defaming spatially literator excusably hoarder foils scampishly votes neolith gruffly flews imaged strays midships knit smitheries seamier faint mercifully hatching stickybeak foursquare obligation worked bayous eft scruff recrystallised alined gladsomely chunderous rouse uncut jives streaming soffit amusedly talismanic snubbingly formant thwart nanism disenfranchise trustless creditably sith vauntingly mesally uncured referring glumly rectify outcrosses hypotonic elsewhither dogfishes yes disseminule logically forjudging egestive procreates smackers what offshore kneecap pronely pale revilings clad worst cursores presidiums communalize seem supplying structuralism thinkingly bestially\",\"hashes\":{\"MD5\":\"2458b416324cb497dff05c3d69e348c5\",\"SHA-256\":\"f2ab3a8cbef7e4d837d545acb1d9a8b9e88a144def3e5c48b4fec4afb24c7231\",\"SHA-1\":\"5feaa621decfde94905796614d6f23eec19984c6\"}}],\"name\":\"laicizing outbraving\",\"description\":\"gapingly illicitness wheys leftward surpassing cumulates mistook autarkic never reorganized driveable earing garnisheeing slap digressions beetleheads incog burned permanencies rutted anes properly onomastic inks polycarpic cognizant gasp overcast derive unbuilt wetly initiate detective stripy forefeels leachy causation foliates giusto\"}"

        then:
        AttackPattern parsedAttackPattern = (AttackPattern) StixParsers.parseObject(rawJson)
        println parsedAttackPattern.toJsonString()
    }

    def "test sub-second created timestamp with 1 digit"() {
        when:

        String rawJson = "{\"type\":\"attack-pattern\",\"id\":\"attack-pattern--4cd20885-3c39-4c01-b298-fe2dcca94f22\",\"created\":\"2019-04-12T09:16:58.1Z\",\"modified\":\"2019-04-12T09:16:58.437Z\",\"revoked\":false,\"labels\":[\"reconciler\",\"slanders\",\"ectopia\",\"underpaid\",\"fanions\"],\"external_references\":[{\"source_name\":\"moneyless\",\"description\":\"insensately objurgated offishly gonad finely acerous shanghaied galliambic some fells effectuate laigh fetial threaps fro refutably hibernating mute contentiously variably soldierly glazed triform draught sternward slap worst thermostats depicture straiten clowns chicly found adrift due pitiably eligibly pappy ethnolinguist jubilantly smug scissel cheat grim yare abnormally fuss salably robotizes gurnard ritenuto spores beside cyanotype disepalous where encodes colossally thuddingly normatively lyrically cuneate troughs briefly matutinal enslaving tribuneships monthly peke caracoled vizor exchangeably sunbow lapidaries cuspidated accompany left peacefully bushellers unlovable amphisbaenic stopped masculinize provocative yore redintegrates meaningful spang subsumable anthologise deploringly allegorise inquiringly kachina cucullate dure bucketful\",\"url\":\"http://www.guardersreynaldo.edu\",\"hashes\":{\"SHA-1\":\"406fc6ef2e8b75ac187bf0c4600c750ee7f41f8d\"},\"external_id\":\"5cb29105-f7a3-4220-b108-8a096e852a13\"},{\"source_name\":\"foul\",\"description\":\"redriven daintily rejects roughcast unblemished bene absolve passably costively effeminised separately plump gazelle mulcts anon worsts middling deafeningly frequentation lumines grips adminicle dichotomized weirs sprig stalls lethargic glossologists puncturing forgettable inquirers ingenerate wordy bung scallop demobilized wreath cruds ugli\",\"hashes\":{\"MD5\":\"e23da6dc5fcf066da2d78aa756e76c1a\"},\"external_id\":\"45e821ac-4e91-40da-bd19-3cb8eb2b7fac\"},{\"source_name\":\"tabu\",\"description\":\"disenfranchised abnegating starred squarrose decolor circumscriptive subentire effectless reversely acoustical minimally bizarrely licht lived swith granolithic gyp peccantly transcriptively handbags decently focuses sectionally choice freshly haruspical extractant bolsters extirpative bucktooth architect symbolics lathlike glyphs err trephine weirdie peristomal prisms connate protolithic fyrd deathly\",\"url\":\"http://www.hoggedwendell.org\",\"hashes\":{\"SHA-1\":\"f48f161e4dcce0673b54a541a51a541957e15bb6\"}},{\"source_name\":\"siderite\",\"hashes\":{\"SHA-512\":\"697d9941b378ff02ee9ca411f5a99f6b5c056de32681dfd6e6e872bdf9bf7cf2bb8d0f21b5eb9fee5ce56bde651c3981d5bd8356b28b2f7498ce17628b9ac503\"},\"external_id\":\"b0f7df80-0103-4960-ae69-fdbf92391c6f\"},{\"source_name\":\"headfirst\",\"description\":\"queenly staid processors stacked wham stigmatized guarantee teeter rungs mentally prill fakes amortizing lickerishly disinfectant generously electrotypes educated\",\"url\":\"http://www.forcedlylunch.io\",\"hashes\":{\"SHA-512\":\"88e6ba3b9e992681b9f08006eec5ca96350d554c9981d3d2b5c66645430efb6feb079d379c148f94b624f11c7a448ccc8aedc4236780656321958f17ead82fba\"},\"external_id\":\"72c59882-dfbe-40ce-86eb-10dbc5ae074f\"},{\"source_name\":\"jarosite\",\"url\":\"http://www.gaumlessmarylee.io\",\"external_id\":\"8456502a-e570-4b0e-9279-afb0666637e2\"},{\"source_name\":\"falconine\",\"description\":\"gey restively toughen permute malingers abroad thin foul defaming spatially literator excusably hoarder foils scampishly votes neolith gruffly flews imaged strays midships knit smitheries seamier faint mercifully hatching stickybeak foursquare obligation worked bayous eft scruff recrystallised alined gladsomely chunderous rouse uncut jives streaming soffit amusedly talismanic snubbingly formant thwart nanism disenfranchise trustless creditably sith vauntingly mesally uncured referring glumly rectify outcrosses hypotonic elsewhither dogfishes yes disseminule logically forjudging egestive procreates smackers what offshore kneecap pronely pale revilings clad worst cursores presidiums communalize seem supplying structuralism thinkingly bestially\",\"hashes\":{\"MD5\":\"2458b416324cb497dff05c3d69e348c5\",\"SHA-256\":\"f2ab3a8cbef7e4d837d545acb1d9a8b9e88a144def3e5c48b4fec4afb24c7231\",\"SHA-1\":\"5feaa621decfde94905796614d6f23eec19984c6\"}}],\"name\":\"laicizing outbraving\",\"description\":\"gapingly illicitness wheys leftward surpassing cumulates mistook autarkic never reorganized driveable earing garnisheeing slap digressions beetleheads incog burned permanencies rutted anes properly onomastic inks polycarpic cognizant gasp overcast derive unbuilt wetly initiate detective stripy forefeels leachy causation foliates giusto\"}"

        then:
        AttackPattern parsedAttackPattern = (AttackPattern) StixParsers.parseObject(rawJson)
        println parsedAttackPattern.toString()
        println parsedAttackPattern.toJsonString()
    }
}
