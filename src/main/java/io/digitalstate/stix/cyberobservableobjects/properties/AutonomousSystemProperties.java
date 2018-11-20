package io.digitalstate.stix.cyberobservableobjects.properties;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class AutonomousSystemProperties extends CyberObservableObjectCommonProperties {

    protected int number;

    @JsonInclude(NON_NULL)
    protected String name = null;

    @JsonInclude(NON_NULL)
    protected String rir = null;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRir() {
        return rir;
    }

    public void setRir(String rir) {
        this.rir = rir;
    }
}
