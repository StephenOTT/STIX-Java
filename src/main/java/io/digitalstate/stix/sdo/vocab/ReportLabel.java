package io.digitalstate.stix.sdo.vocab;

/**
 * report-label-ov
 */
public enum ReportLabel {
    THREAT_REPORT {
        @Override
        public String toString() {
            return "threat-report";
        }
    },
    ATTACK_PATTERN {
        @Override
        public String toString() {
            return "attack-pattern";
        }
    },
    CAMPAIGN {
        @Override
        public String toString() {
            return "campaign";
        }
    },
    IDENTITY {
        @Override
        public String toString() {
            return "identity";
        }
    },
    INDICATOR {
        @Override
        public String toString() {
            return "indicator";
        }
    },
    INTRUSION_SET {
        @Override
        public String toString() {
            return "intrusion-set";
        }
    },
    MALWARE {
        @Override
        public String toString() {
            return "malware";
        }
    },
    OBSERVED_DATA {
        @Override
        public String toString() {
            return "observed-data";
        }
    },
    THEAT_ACTOR {
        @Override
        public String toString() {
            return "threat-actor";
        }
    },
    TOOL {
        @Override
        public String toString() {
            return "tool";
        }
    },
    VULNERABILITY {
        @Override
        public String toString() {
            return "vulnerability";
        }
    }
}
