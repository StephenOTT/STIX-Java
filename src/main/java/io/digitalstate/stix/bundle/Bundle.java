package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
import io.digitalstate.stix.datamarkings.granular.GranularMarking;
import io.digitalstate.stix.helpers.StixSpecVersion;

import java.util.*;

import static io.digitalstate.stix.helpers.IdGeneration.*;

public class Bundle extends BundleProperties {

    private final String TYPE = "bundle";

    public Bundle(LinkedHashSet<BundleObjects> objects){
        setType(TYPE);
        setId(generateUuidAsString());
        setSpecVersion(StixSpecVersion.SPECVERSION);
        setObjects(objects);
    }
    public Bundle(BundleObjects... objects){
        this(new LinkedHashSet<>(Arrays.asList(objects)));
    }

    @JsonIgnore
    public void autoAddDataMarkingsToBundle(){

        LinkedHashSet<MarkingDefinition> definitions = new LinkedHashSet<>();

        this.getObjects().forEach(object -> {
            if (object instanceof DataMarkingsAppliable){
                // Get the Object Marking Definitions
                LinkedHashSet<MarkingDefinition> objectMarkings = ((DataMarkingsAppliable) object).getObjectMarkingRefs();
                if (objectMarkings != null && !objectMarkings.isEmpty()){
                    definitions.addAll(objectMarkings);
                }

                // Get the Granular Markings and for Each Granular get the related Marking Definition
                LinkedHashSet<GranularMarking> granularMarkings = ((DataMarkingsAppliable) object).getGranularMarkings();
                if (granularMarkings != null && !granularMarkings.isEmpty()){
                    granularMarkings.forEach(gm -> definitions.add(gm.getMarkingRef()));
                }
            }
        });

        // Merge all of the gathered definitions into the objects property
        this.getObjects().addAll(definitions);
    }
}
