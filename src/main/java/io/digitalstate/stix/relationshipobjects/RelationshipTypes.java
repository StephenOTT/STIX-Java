package io.digitalstate.stix.relationshipobjects;

public enum RelationshipTypes {
    DUPLICATE_OF{
        @Override
        public String toString() {
            return "duplicate-of";
        }
    },
    RELATED_TO{
        @Override
        public String toString() {
            return "related-to";
        }
    },
    DERIVED_FROM{
        @Override
        public String toString() {
            return "derived-from";
        }
    }
}
