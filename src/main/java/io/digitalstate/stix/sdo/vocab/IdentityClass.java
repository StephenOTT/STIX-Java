package io.digitalstate.stix.sdo.vocab;

/**
 * identity-class-ov
 */
public enum IdentityClass {
    INDIVIDUAL {
        @Override
        public String toString() {
            return "individual";
        }
    },
    GROUP {
        @Override
        public String toString() {
            return "group";
        }
    },
    ORGANIZATION {
        @Override
        public String toString() {
            return "organization";
        }
    },
    CLASS {
        @Override
        public String toString() {
            return "class";
        }
    },
    UNKNOWN {
        @Override
        public String toString() {
            return "unknown";
        }
    }
}
