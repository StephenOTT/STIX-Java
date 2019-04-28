package stix.validation

import io.digitalstate.stix.bundle.BundleableObject
import io.digitalstate.stix.json.StixParserValidationException
import io.digitalstate.stix.json.StixParsers
import spock.lang.Specification

class BundleValidationSpec extends Specification {

    def "Validate invalid Bundle JSON"() {
        when: "Parsing invalid Bundle JSON "
        String json = '''
            {
              "type": "bundle",
              "id": "bundle--830d3023-6c2d-4d91-b1dc-b594959b8d48",
              "spec_version": "2.0",
              "x_indolently": 232355.30549115842,
              "x_signora": "girts",
              "x_cementitious": "teetotally expansionism impudently loams solonchak hooligans awry yale snappingly blamed refutably natation schoolgirlish reshapes bought jots protectively waur whimsically pailfuls reddle deprivation quite bituminised thin falls mailman amie hugely crousely summarises saintliest faithfully gap flam eighth perdie choicely phut pears bifilar impetrative specialises yeah vainly religieuse pistole flown flail unplaced spicate repossesses trindles fidge cattishly limbed sural frits generousness hoppings palpitating stultifies topes intercity mat now mews slay bistouries instigating secretively rebuttable legitimate unslung compatibly rayless summarily reassure fresh canny shiftily inarm vernalizing crimp repopulates landscaping fumier abnegations hippophiles chastely bight unbrotherly coldly archaizes",
              "x_simular": 940778.1190796582,
              "x_orchestrates": 800790,
              "x_else": true,
              "x_scrumptiously": 189963.7126364522,
              "x_entraps": true,
              "x_pentathlons": "illustrious coequally forte seditiously topee geotropic undress bumpily dinars admeasure sham pruriently antiquations grate unsayable playable fey courteously lineally highlands stains barbarous euphonized intonate quickly insulators adumbrated wield telly girt acromion remorselessly discrete perigee truculently ducks subzero umpire sapindaceous lieve treats germane snecked clot uncordial lint mow gaiety perplexity starchily polyploidy stopless ablins flongs tongued weans chotts",
              "x_ghastfully": 947223,
              "x_repurifies": 348826,
              "x_lengthways": "precess",
              "x_altruistic": "scabious dissipating transmutably malefactors scatophagy ban disimpassioned ought negotiates deign vividly puddocks upstair equalising lampoonist monogenic acrogens nightmarishly dominances fishily lubricous pronely unknightly sass sport frogged clause backwater glumes suffumigates scag nomadic plaguy casket proud connectedly umbonate forbidder derestrict yare outtelling yodelling logicises poulterers breakaways prod fricassees divinizing days devitalize burglarises chomps lalangs entwining medalling interspacing xerography lochia outdoors sighs obnoxious moochers deceptively leadless revocably salving larch hairiest stomatitis outdares indiscreet tenth doomwatchers decretive socialistic manifold counterpoises cornier flaunts ninth confessedly recountal fruitions savorous toothed gorgonise keyless commensurate loads instructor",
              "x_tairas": "icier",
              "x_mumms": 410175,
              "x_marigold": 471065.5268547859,
              "x_annex": "overlive publicize sepulcher days kerchiefed objectivist quire whisk posh unsurfaced brooms chalk thrice lysing trichologist mair interplants transmigrated catacumbal when multipurpose bloated days clues strung reinstated bucktooth soppiest reinterring rimming picked charged strop licht dupes why pedicellate detribalized molto constructs obumbrates eradicate ruinable retail vivo throngs myall unnoticed concussive ever sugarless bleeds diffusedly hithermost cleverish crummier whiles fun monadelphous embowelling buoyancy linoleum alway arranges fasciate unbalances malingerers feal deputation federally lamellibranch reconquer beefeaters shill swith expressively mickles rhodic disport cauterized adhibit coves pencils dern likely inflaming neatly",
              "objects": [
                {
                  "id": "vulnerability--f5c62801-f9b0-42a7-983e-78ed03560b2b",
                  "type": "vulnerability",
                  "created_by_ref": "identity--44fda38a-2010-4510-bd69-3e176224abe3",
                  "created": "2019-03-21T20:51:39.872Z",
                  "modified": "2019-03-21T20:51:39.872Z",
                  "revoked": false,
                  "labels": [
                    "amphibrachic",
                    "longly",
                    "lode",
                    "forehanded",
                    "daglock",
                    "yes",
                    "unimportuned",
                    "garotting",
                    "loaf",
                    "ferroconcrete",
                    "downstairs"
                  ],
                  "granular_markings": [
                    {
                      "marking_ref": "marking-definition--2ad5b13d-c9c7-4bea-9dfa-5b1e5a2194b7",
                      "selectors": [
                        "perisarc",
                        "landslide",
                        "thorps",
                        "sonnets",
                        "dryer",
                        "skimmings"
                      ],
                      "x_wifeless": "befitting prescriptivism slums lucubrated paynims mattresses thwacks welts quattrocento videodisk fraudful runed chugs moodier splodges psts swerve slink abloom incages filthily there overrates buss unaccounted promotive sisterliness alleviates wit bloodily smiles influent smut farawayness interbreedings massacres what geophagous scrimps sized inlets pocks melilots verifiers chirper docs inveigh forsooth quadrifid hybridizes doctoral aught whiles stoop around tidily worst choppy stridulous smash heap unsustaining untouchable doggone phut mischarging spasms attributing gratifiers interchanges militating invincibly dytiscid overselling prompt windmills slanderously counsellable"
                    },
                    {
                      "marking_ref": "marking-definition--893f95c0-ea68-4c58-ba83-84c99e6a6eb3",
                      "selectors": [
                        "trailingly",
                        "redesigns",
                        "amounts",
                        "hipped",
                        "metaphrase",
                        "meanwhile",
                        "deeply"
                      ],
                      "x_squirrelly": true,
                      "x_nominated": true,
                      "x_toothed": "loads goads quells wipes vagarious lampads sometimes proems flatter distastefully dotard monochromes brief luculent blithely basidial wastes",
                      "x_cloying": 325664,
                      "x_loath": "eructations",
                      "x_lambkin": false,
                      "x_pauperized": "sordid",
                      "x_romantics": 831690,
                      "x_conciliar": "yesterevening brought atomises",
                      "x_doabs": "consistently",
                      "x_tierced": false
                    },
                    {
                      "marking_ref": "marking-definition--3697dbf5-c4d0-4c99-ba83-797dcb183387",
                      "selectors": [
                        "vivacities"
                      ],
                      "x_dully": 509046,
                      "x_fends": "byzants retting tight slap thig golfs robustly setts dentexes grout snide tight epos ways unhorsing ayahs microtones miters explorations chalkpits cuttles tight unchastely systematized surely restructure vixen uptearing clearly overplied floodlighted riverlike worse skirts unconstant unbespoken dent respirable",
                      "x_bilk": "jargonises",
                      "x_spang": 533218.6108306068,
                      "x_smoulders": 193127.38563754444,
                      "x_otherwhere": 500157,
                      "x_compactedly": true,
                      "x_reactively": "distinguish hues flops here maims undiminished condemnable casually engrossments unhusk loads copulating heliograph citruses succulently clam communizing cheesing weighs candelabrum cauld embedments",
                      "x_explicitly": "swearings maliciously insolubly roaringly laggingly blamed griping undeserving aecidium sorobans transnational assai produces depresses tenderized fumed dutiable dismally carburizing course lest aestivate sump killikinick quintan clomb tinkling thin redips caracol tracklessness worst pitifully demagnetize yet crack unreproved pegh draftiness crawfishes romaines overcloud desiccate caustically pluckily thick rehearsed dyeable regulate entresol flawier wittingly imperium mistiest stannaries lucubrate ditto scorched patting incurs inward cholent stingingly expects screams mercilessly havocs ruffians bead pitched promises gurge hafts glumaceous chartists tressed since seriatim halest gainless imprisons between cognitions",
                      "x_secretly": true,
                      "x_banderilla": "pepino",
                      "x_rewash": 315436.90735046,
                      "x_convulsively": false,
                      "x_daftly": "preannounce",
                      "x_far": 444089.75319646124,
                      "x_trichinized": true,
                      "x_counteract": false,
                      "x_mighty": 404750,
                      "x_mauve": 123931
                    }
                  ],
                  "namee": "megaliths",
                  "description": "towered left away symphysial cronk artier pocks unobtained tsarist could endarch revengingly nocuously could disquietly"
                },
                {
                  "id": "vulnerability--f36853bb-7745-403f-af15-b3c324c057c7",
                  "type": "vulnerability",
                  "created_by_ref": "identity--c39f93f0-072f-4c97-a2e4-ec9358ecb093",
                  "created": "2019-03-21T20:51:39.878Z",
                  "modified": "2019-03-21T20:51:39.878Z",
                  "revoked": true,
                  "external_references": [
                    {
                      "source_name": "cognizes",
                      "description": "smash barbeques shoehorn spang jade",
                      "url": "http://www.chayscaprice.org",
                      "hashes": {
                        "SHA-512": "db39af5838979105222d43fbbd08f7ae8ee064e103c9a9523d8819f395bb2cdd56ecb0af54b4db3569497868556f8642277129280faf031e2dba369bbd5d950c"
                      },
                      "external_id": "1c5c3cb8-62b5-4a9b-a920-e3405235cb9a"
                    },
                    {
                      "source_name": "vocative",
                      "hashes": {
                        "SHA-256": "e0ccc55e8669f4c6470de2f36553d377096136cebb0752566a4e644c1b79d3ad"
                      }
                    }
                  ],
                  "object_marking_refs": [
                    "marking-definition--c631a64f-df15-4be2-ad76-4350a4adb4e7"
                  ],
                  "granular_markings": [
                    {
                      "marking_ref": "marking-definition--fe9ff6b4-8548-41d4-b6c5-9c304658581c",
                      "selectors": [
                        "discourages",
                        "quaveringly",
                        "goad",
                        "evaded",
                        "scores",
                        "wrest",
                        "lest",
                        "historicism"
                      ]
                    },
                    {
                      "marking_ref": "marking-definition--60900ddb-a0e2-4508-8d3d-f1122f644856",
                      "selectors": [
                        "boot",
                        "newsier"
                      ]
                    },
                    {
                      "marking_ref": "marking-definition--f941ff40-a304-4f8b-93c0-7cc7bf708540",
                      "selectors": [
                        "unovercome",
                        "pewter",
                        "cheesing",
                        "cruelly"
                      ],
                      "x_obtrusively": false,
                      "x_motorises": true
                    }
                  ],
                  "namee": "beefcakes",
                  "description": "sober fossilize hypostyle proprietors miscellanists deviceful keloidal fowls mesenteric scotomatous suburbanize nights undiscording inverter pinfolds dang redeems avail underdrawing reallotted segments arytenoid evaded lowering resiliently hereto excessively theologised demonstrative quads seaplanes lushy quirt equipoised typing reground dryer shoreward"
                }
              ]
            }
                    '''

        then: "Should have 1 error"
        try {
            StixParsers.parseBundle(json)
        } catch (StixParserValidationException ex) {
            assert ex.getConstraintValidations().size() == 2
//            ex.getConstraintValidations().each { x ->
//                println "------"
//                println "Type: ${x.getRootBean().getClass().getSimpleName()}"
//                println "Object Id: ${x.getRootBean().asType(BundleableObject).getId()}"
//                println "Message: ${x.getMessage()}"
//                println "path: ${x.getPropertyPath()}"
//                println "invalid_value: ${x.getInvalidValue().toString()}"
//                println "------"
//            }
        }
    }

}
