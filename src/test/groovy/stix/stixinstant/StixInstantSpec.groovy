package stix.stixinstant

import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Campaign
import spock.lang.Specification
import stix.StixMockDataGenerator

class StixInstantSpec extends Specification implements StixMockDataGenerator {

    def "time test to ensure Instant Precisions are maintained"(){
        when:
        Campaign campaignCreatedNoSubSec = StixParsers.parseObject("{\"type\":\"campaign\",\"id\":\"campaign--854ae5bf-e07b-4853-95f8-0f85027af1ea\",\"created_by_ref\":\"identity--5eb4619f-b1b6-4b7b-9504-bd3b4d05ce8a\",\"created\":\"2019-04-26T21:14:11Z\",\"modified\":\"2019-04-26T21:14:11.851Z\",\"revoked\":false,\"labels\":[\"interspersed\",\"yep\",\"contemners\",\"spurts\",\"aft\",\"aldose\"],\"granular_markings\":[{\"marking_ref\":\"marking-definition--980dffe6-2006-45bc-86c7-9885c986fdaa\",\"selectors\":[\"broch\",\"lipped\"],\"x_delimitates\":\"thick\",\"x_chocolaty\":380412,\"x_disarmingly\":\"cataplexy eyeleted poss belike told reformism neglect grillage purer justiciars boosts about haste compulsive over reinvolve blackly obliterate gamy here suffused triaxial tuatara circlers fianchetto aforementioned viverrine drowsily ties screeds fun larvicidal wuthering condyloma epistolize intwines loins ditto litho bluish straightaway restaged\",\"x_dryer\":327849,\"x_unblushingly\":false},{\"marking_ref\":\"marking-definition--f82c9f27-edb4-496b-9eca-58080db5d16d\",\"selectors\":[\"bootleg\",\"muclucs\",\"eild\",\"vandalises\",\"sardiuses\",\"astray\",\"exhaustively\",\"pyrogallic\"]}],\"name\":\"disastrously\"}")
        Campaign campaignCreated9SubSec = StixParsers.parseObject("{\"type\":\"campaign\",\"id\":\"campaign--854ae5bf-e07b-4853-95f8-0f85027af1ea\",\"created_by_ref\":\"identity--5eb4619f-b1b6-4b7b-9504-bd3b4d05ce8a\",\"created\":\"2019-04-26T21:14:11.999999999Z\",\"modified\":\"2019-04-26T21:14:11.851Z\",\"revoked\":false,\"labels\":[\"interspersed\",\"yep\",\"contemners\",\"spurts\",\"aft\",\"aldose\"],\"granular_markings\":[{\"marking_ref\":\"marking-definition--980dffe6-2006-45bc-86c7-9885c986fdaa\",\"selectors\":[\"broch\",\"lipped\"],\"x_delimitates\":\"thick\",\"x_chocolaty\":380412,\"x_disarmingly\":\"cataplexy eyeleted poss belike told reformism neglect grillage purer justiciars boosts about haste compulsive over reinvolve blackly obliterate gamy here suffused triaxial tuatara circlers fianchetto aforementioned viverrine drowsily ties screeds fun larvicidal wuthering condyloma epistolize intwines loins ditto litho bluish straightaway restaged\",\"x_dryer\":327849,\"x_unblushingly\":false},{\"marking_ref\":\"marking-definition--f82c9f27-edb4-496b-9eca-58080db5d16d\",\"selectors\":[\"bootleg\",\"muclucs\",\"eild\",\"vandalises\",\"sardiuses\",\"astray\",\"exhaustively\",\"pyrogallic\"]}],\"name\":\"disastrously\"}")
        Campaign campaignCreatedZerosSubSec = StixParsers.parseObject("{\"type\":\"campaign\",\"id\":\"campaign--854ae5bf-e07b-4853-95f8-0f85027af1ea\",\"created_by_ref\":\"identity--5eb4619f-b1b6-4b7b-9504-bd3b4d05ce8a\",\"created\":\"2019-04-26T21:14:11.000000000Z\",\"modified\":\"2019-04-26T21:14:11.851Z\",\"revoked\":false,\"labels\":[\"interspersed\",\"yep\",\"contemners\",\"spurts\",\"aft\",\"aldose\"],\"granular_markings\":[{\"marking_ref\":\"marking-definition--980dffe6-2006-45bc-86c7-9885c986fdaa\",\"selectors\":[\"broch\",\"lipped\"],\"x_delimitates\":\"thick\",\"x_chocolaty\":380412,\"x_disarmingly\":\"cataplexy eyeleted poss belike told reformism neglect grillage purer justiciars boosts about haste compulsive over reinvolve blackly obliterate gamy here suffused triaxial tuatara circlers fianchetto aforementioned viverrine drowsily ties screeds fun larvicidal wuthering condyloma epistolize intwines loins ditto litho bluish straightaway restaged\",\"x_dryer\":327849,\"x_unblushingly\":false},{\"marking_ref\":\"marking-definition--f82c9f27-edb4-496b-9eca-58080db5d16d\",\"selectors\":[\"bootleg\",\"muclucs\",\"eild\",\"vandalises\",\"sardiuses\",\"astray\",\"exhaustively\",\"pyrogallic\"]}],\"name\":\"disastrously\"}")
        Campaign campaignCreated3ZerosSubSec = StixParsers.parseObject("{\"type\":\"campaign\",\"id\":\"campaign--854ae5bf-e07b-4853-95f8-0f85027af1ea\",\"created_by_ref\":\"identity--5eb4619f-b1b6-4b7b-9504-bd3b4d05ce8a\",\"created\":\"2019-04-26T21:14:11.000Z\",\"modified\":\"2019-04-26T21:14:11.851Z\",\"revoked\":false,\"labels\":[\"interspersed\",\"yep\",\"contemners\",\"spurts\",\"aft\",\"aldose\"],\"granular_markings\":[{\"marking_ref\":\"marking-definition--980dffe6-2006-45bc-86c7-9885c986fdaa\",\"selectors\":[\"broch\",\"lipped\"],\"x_delimitates\":\"thick\",\"x_chocolaty\":380412,\"x_disarmingly\":\"cataplexy eyeleted poss belike told reformism neglect grillage purer justiciars boosts about haste compulsive over reinvolve blackly obliterate gamy here suffused triaxial tuatara circlers fianchetto aforementioned viverrine drowsily ties screeds fun larvicidal wuthering condyloma epistolize intwines loins ditto litho bluish straightaway restaged\",\"x_dryer\":327849,\"x_unblushingly\":false},{\"marking_ref\":\"marking-definition--f82c9f27-edb4-496b-9eca-58080db5d16d\",\"selectors\":[\"bootleg\",\"muclucs\",\"eild\",\"vandalises\",\"sardiuses\",\"astray\",\"exhaustively\",\"pyrogallic\"]}],\"name\":\"disastrously\"}")
        Campaign campaignCreated3DigitsWithTrailingZerosSubSec = StixParsers.parseObject("{\"type\":\"campaign\",\"id\":\"campaign--854ae5bf-e07b-4853-95f8-0f85027af1ea\",\"created_by_ref\":\"identity--5eb4619f-b1b6-4b7b-9504-bd3b4d05ce8a\",\"created\":\"2019-04-26T21:14:11.111000000Z\",\"modified\":\"2019-04-26T21:14:11.851Z\",\"revoked\":false,\"labels\":[\"interspersed\",\"yep\",\"contemners\",\"spurts\",\"aft\",\"aldose\"],\"granular_markings\":[{\"marking_ref\":\"marking-definition--980dffe6-2006-45bc-86c7-9885c986fdaa\",\"selectors\":[\"broch\",\"lipped\"],\"x_delimitates\":\"thick\",\"x_chocolaty\":380412,\"x_disarmingly\":\"cataplexy eyeleted poss belike told reformism neglect grillage purer justiciars boosts about haste compulsive over reinvolve blackly obliterate gamy here suffused triaxial tuatara circlers fianchetto aforementioned viverrine drowsily ties screeds fun larvicidal wuthering condyloma epistolize intwines loins ditto litho bluish straightaway restaged\",\"x_dryer\":327849,\"x_unblushingly\":false},{\"marking_ref\":\"marking-definition--f82c9f27-edb4-496b-9eca-58080db5d16d\",\"selectors\":[\"bootleg\",\"muclucs\",\"eild\",\"vandalises\",\"sardiuses\",\"astray\",\"exhaustively\",\"pyrogallic\"]}],\"name\":\"disastrously\"}")

        then:
        assert campaignCreatedNoSubSec.getCreated().toString() == "2019-04-26T21:14:11Z"
        assert campaignCreatedNoSubSec.getCreated().getInstant().toString() == "2019-04-26T21:14:11Z"

        then:
        assert campaignCreated9SubSec.getCreated().toString() == "2019-04-26T21:14:11.999999999Z"
        assert campaignCreated9SubSec.getCreated().getInstant().toString() == "2019-04-26T21:14:11.999999999Z"

        then:
        assert campaignCreatedZerosSubSec.getCreated().toString() == "2019-04-26T21:14:11.000000000Z"
        assert campaignCreatedZerosSubSec.getCreated().getInstant().toString() == "2019-04-26T21:14:11Z"

        then:
        assert campaignCreated3ZerosSubSec.getCreated().toString() == "2019-04-26T21:14:11.000Z"
        assert campaignCreated3ZerosSubSec.getCreated().getInstant().toString() == "2019-04-26T21:14:11Z"

        then:
        assert campaignCreated3DigitsWithTrailingZerosSubSec.getCreated().toString() == "2019-04-26T21:14:11.111000000Z"
        assert campaignCreated3DigitsWithTrailingZerosSubSec.getCreated().getInstant().toString() == "2019-04-26T21:14:11.111Z"
    }

}
