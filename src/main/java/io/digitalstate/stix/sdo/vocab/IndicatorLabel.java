package io.digitalstate.stix.sdo.vocab;

/**
 * indicator-label-ov
 */
public enum IndicatorLabel {
    ANOMALOUS_ACTIVITY {
        @Override
        public String toString() {
            return "anomalous-activity";
        }
    },
    ANONYMIZATION {
        @Override
        public String toString() {
            return "anonymization";
        }
    },
    BENIGN {
        @Override
        public String toString() {
            return "benign";
        }
    },
    COMPROMISED {
        @Override
        public String toString() {
            return "compromised";
        }
    },
    MALICIOUS_ACTIVITY {
        @Override
        public String toString() {
            return "malicious-activity";
        }
    },
    ATTRIBUTION {
        @Override
        public String toString() {
            return "attribution";
        }
    }
}
