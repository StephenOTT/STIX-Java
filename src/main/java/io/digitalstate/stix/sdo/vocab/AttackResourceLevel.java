package io.digitalstate.stix.sdo.vocab;

/**
 * attack-resource-level-ov
 */
public enum AttackResourceLevel {
    INDIVIDUAL {
        @Override
        public String toString() {
            return "individual";
        }
    },
    CLUB {
        @Override
        public String toString() {
            return "club";
        }
    },
    CONTEST {
        @Override
        public String toString() {
            return "contest";
        }
    },
    TEAM {
        @Override
        public String toString() {
            return "team";
        }
    },
    ORGANIZATION {
        @Override
        public String toString() {
            return "organization";
        }
    },
    GOVERNMENT {
        @Override
        public String toString() {
            return "government";
        }
    }
}
