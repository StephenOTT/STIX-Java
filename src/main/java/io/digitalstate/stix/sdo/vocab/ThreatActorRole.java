package io.digitalstate.stix.sdo.vocab;

/**
 * threat-actor-role-ov
 */
public enum ThreatActorRole {
    AGENT {
        @Override
        public String toString() {
            return "agent";
        }
    },
    DIRECTOR {
        @Override
        public String toString() {
            return "director";
        }
    },
    INDEPENDENT {
        @Override
        public String toString() {
            return "independent";
        }
    },
    INFRASTRUCTURE_ARCHITECT {
        @Override
        public String toString() {
            return "infrastructure-architect";
        }
    },
    INFRASTRUCTURE_OPERATOR {
        @Override
        public String toString() {
            return "infrastructure-operator";
        }
    },
    MALWARE_AUTHOR {
        @Override
        public String toString() {
            return "malware-author";
        }
    },
    SPONSOR {
        @Override
        public String toString() {
            return "sponsor";
        }
    }
}
