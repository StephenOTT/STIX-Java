package io.digitalstate.stix.cyberobservableobjects;

import io.digitalstate.stix.cyberobservableobjects.properties.ArtifactProperties;

public class Artifact extends ArtifactProperties implements CyberObservableObject {

    private static final String TYPE = "artifact";

    public Artifact(){
        setType(TYPE);
    }

}
