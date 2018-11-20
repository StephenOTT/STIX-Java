package io.digitalstate.stix.cyberobservableobjects;

import io.digitalstate.stix.cyberobservableobjects.properties.AutonomousSystemProperties;

public class AutonomousSystem extends AutonomousSystemProperties implements CyberObservableObject {

    private static final String TYPE = "autonomous-system";

    public AutonomousSystem(int number){
        setType(TYPE);
        setNumber(number);
    }

}
