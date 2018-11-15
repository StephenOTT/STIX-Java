package io.digitalstate.stix.sdo.vocab;

/**
 * threat-actor-sophistication-ov
 */
public enum ThreatActorSophistication {
    NONE {
        @Override
        public String toString() {
            return "none";
        }
    },
    MINIMAL {
        @Override
        public String toString() {
            return "minimal";
        }
    },
    INTERMEDIATE {
        @Override
        public String toString() {
            return "intermediate";
        }
    },
    ADVACNED {
        @Override
        public String toString() {
            return "advanced";
        }
    },
    EXPERT {
        @Override
        public String toString() {
            return "expert";
        }
    },
    INNOVATOR {
        @Override
        public String toString() {
            return "innovator";
        }
    },
    STRATEGIC {
        @Override
        public String toString() {
            return "strategic";
        }
    }
}
