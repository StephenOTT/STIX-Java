package io.digitalstate.stix.sdo.vocab;

/**
 * attack-motivation-ov
 */
public enum AttackMotivation {
    ACCIDENTAL {
        @Override
        public String toString() {
            return "accidental";
        }
    },
    COERCION {
        @Override
        public String toString() {
            return "coercion";
        }
    },
    DOMINANCE {
        @Override
        public String toString() {
            return "dominance";
        }
    },
    IDEOLOGY {
        @Override
        public String toString() {
            return "ideology";
        }
    },
    NOTORIETY {
        @Override
        public String toString() {
            return "notoriety";
        }
    },
    ORGANIZATIONAL_GAIN {
        @Override
        public String toString() {
            return "organizational-gain";
        }
    },
    PERSONAL_GAIN {
        @Override
        public String toString() {
            return "personal-gain";
        }
    },
    PERSONAL_SATISFACTION {
        @Override
        public String toString() {
            return "personal-satisfaction";
        }
    },
    REVENGE {
        @Override
        public String toString() {
            return "revenge";
        }
    },
    UNPREDICTABLE {
        @Override
        public String toString() {
            return "unpredictable";
        }
    }
}
