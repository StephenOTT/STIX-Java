package io.digitalstate.stix.common;

/**
 * Wrapper for boolean that is used in Stix to track if the boolean value was User provided.
 * Custom class is used instead of a Boolean in order to provide a more robust api
 */
public class StixBoolean {

    private boolean stixBooleanValue;
    private boolean isDefinedValue;

    public StixBoolean(boolean stixBooleanValue) {
        this.stixBooleanValue = stixBooleanValue;
        this.isDefinedValue = true;
    }

    public StixBoolean(boolean stixBooleanValue, boolean isDefinedValue) {
        this.stixBooleanValue = stixBooleanValue;
        this.isDefinedValue = isDefinedValue;
    }

    /**
     * Defaults to StixBoolean value to false.  Sets isDefinedValue to false
     */
    public StixBoolean() {
        this.stixBooleanValue = false;
        isDefinedValue = false;
    }

    public StixBoolean(String booleanString){
        this.stixBooleanValue = Boolean.valueOf(booleanString);
        this.isDefinedValue = true;
    }

    public boolean getStixBooleanValue() {
        return stixBooleanValue;
    }

    /**
     * Indicates that the boolean value was explicitly defined, even if the value was false,
     * and the original object's property defaults to false if no value is provided.
     * @return boolean indicating if the value was defined.
     */
    public boolean isdefinedValue() {
        return isDefinedValue;
    }

    @Override
    public String toString() {
        return String.valueOf(getStixBooleanValue());
    }

}
