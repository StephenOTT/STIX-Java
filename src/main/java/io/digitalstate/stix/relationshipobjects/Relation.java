package io.digitalstate.stix.relationshipobjects;

import io.digitalstate.stix.bundle.BundleObject;

import java.util.Objects;

/**
 * A wrapper class to support Relationships with STIX.
 * Relationships can be a ID or the actual Object but not both.
 * If a Object is created, then you can get the ID from within the Object itself.
 *
 * @param <T> A {@link BundleObject}
 */
public class Relation<T extends BundleObject> {
    private final String id;
    private final T object;

    /**
     * Create a relation with a Object
     * @param object
     */
    public Relation(T object){
        this.object = object;
        this.id = null;
    }

    /**
     * Create a relation using a String of a ID
     * @param id
     */
    public Relation(String id){
        this.id = id;
        this.object = null;
    }

    /**
     * Get the Relation Id value
     * @return a {@link String} or null if no String was set during relation creation
     */
    public String getId() {
        return id;
    }

    /**
     * Get the Relation Object value
     * @return a {@link BundleObject} or null if no object was set during relation creation
     */
    public T getObject() {
        return object;
    }

    /**
     * Checks if the Relation has a object
     * @return True if a the object attribute is not null
     */
    public boolean hasObject(){
        return getObject() != null;
    }


    //
    // Overrides for hashcode and equals to support comparison of objects as per the STIX Spec
    //

    @Override
    public int hashCode() {
        if (hasObject()){
            return Objects.hash(this.getObject());
        } else {
            return Objects.hash(this.getId());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Relation)) {
            return false;
        }

        if (this.hasObject() && ((Relation) obj).hasObject()){
            return this.getObject().equals(((Relation) obj).getObject());
        } else if (!this.hasObject() && !((Relation) obj).hasObject()){
            return this.getId().equals(((Relation) obj).getId());
        } else {
            return false;
        }
    }
}
