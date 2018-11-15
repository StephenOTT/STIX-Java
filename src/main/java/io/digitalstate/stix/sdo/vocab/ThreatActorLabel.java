package io.digitalstate.stix.sdo.vocab;

/**
 * threat-actor-label-ov
 */
public enum ThreatActorLabel {
    ACTIVIST {
        @Override
        public String toString() {
            return "activist";
        }
    },
    COMPETITOR {
        @Override
        public String toString() {
            return "competitor";
        }
    },
    CRIME_SYNDICATE {
        @Override
        public String toString() {
            return "crime-syndicate";
        }
    },
    CRIMINAL {
        @Override
        public String toString() {
            return "criminal";
        }
    },
    HACKER {
        @Override
        public String toString() {
            return "hacker";
        }
    },
    INSIDER_ACCIDENTAL {
        @Override
        public String toString() {
            return "insider-accidental";
        }
    },
    INSIDER_DISGRUNTLED {
        @Override
        public String toString() {
            return "insider-disgruntled";
        }
    },
    NATION_STATE {
        @Override
        public String toString() {
            return "nation-state";
        }
    },
    SENSATIONALIST {
        @Override
        public String toString() {
            return "sensationalist";
        }
    },
    SPY {
        @Override
        public String toString() {
            return "spy";
        }
    },
    TERRORIST {
        @Override
        public String toString() {
            return "terrorist";
        }
    }
}
