package io.digitalstate.stix.sdo.vocab;

/**
 * tool-label-ov
 */
public enum ToolLabel {
    DENIAL_OF_SERVICE {
        @Override
        public String toString() {
            return "denial-of-service";
        }
    },
    EXPLOITATION {
        @Override
        public String toString() {
            return "exploitation";
        }
    },
    INFORMATION_GATHERING {
        @Override
        public String toString() {
            return "information-gathering";
        }
    },
    NETWORK_CAPTURE {
        @Override
        public String toString() {
            return "network-capture";
        }
    },
    CREDENTIAL_EXPLOITATION {
        @Override
        public String toString() {
            return "credential-exploitation";
        }
    },
    REMOTE_ACCESS {
        @Override
        public String toString() {
            return "remote-access";
        }
    },
    VULNERABILITY_SCANNING {
        @Override
        public String toString() {
            return "vulnerability-scanning";
        }
    }
}
