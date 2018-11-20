package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.digitalstate.stix.datamarkings.DataMarkingsAppliable;
import io.digitalstate.stix.datamarkings.definitions.MarkingDefinition;
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
        // Stream was not used because it was causing NullPointerExceptions on the collect step.  Further review required.
//        List<MarkingDefinition> collect = objects1.stream()
//                .filter(o1 -> o1 instanceof DataMarkingsAppliable)
//                .map(o2 -> ((DataMarkingsAppliable) o2).getObjectMarkingRefs())
//                .flatMap(LinkedHashSet::stream)
//                .collect(Collectors.toList());

        LinkedHashSet<MarkingDefinition> definitions = new LinkedHashSet<>();
        this.getObjects().forEach(o->{
            if (o instanceof DataMarkingsAppliable){
                LinkedHashSet<MarkingDefinition> markings = ((DataMarkingsAppliable) o).getObjectMarkingRefs();
                if (markings != null && !markings.isEmpty()){
                    definitions.addAll(markings);
                }
            }
        });
        this.getObjects().addAll(definitions);
    }

}
