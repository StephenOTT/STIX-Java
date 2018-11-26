package io.digitalstate.stix.bundle;

import io.digitalstate.stix.datamarkings.StixDataMarking;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.util.*;

import static io.digitalstate.stix.helpers.IdGeneration.*;

/**
 * A Implementation of a {@link StixBundle} that follows the spec and provides helpers for auto-Bundle population.
 * The Auto-Bundle population will recursively look through a bundle's objects to find nested objects
 * that should be shown in the parent of the bundle.
 */
public class Bundle extends BundleProperties implements StixBundle {

    private final String TYPE = "bundle";

    public Bundle(LinkedHashSet<BundleObject> objects){
        setType(TYPE);
        setId(generateUuidAsString());
        setSpecVersion(StixSpecVersion.SPECVERSION);
        setObjects(objects);
    }
    public Bundle(BundleObject... objects){
        this(new LinkedHashSet<>(Arrays.asList(objects)));
    }

    public void autoDetectBundleObjects(){
        boolean noNewItems = true;

        while(noNewItems){
            LinkedHashSet<BundleObject> newItems = autoDectectObjectProcessor(getObjects());

            if (!newItems.isEmpty() && !getObjects().containsAll(newItems)){
                getObjects().addAll(newItems);

            } else {
                noNewItems = false;
            }
        }
    }

    private LinkedHashSet<BundleObject> autoDectectObjectProcessor(LinkedHashSet<BundleObject> objects){
        // !!!@TODO !!! Replace all LinkedHashSet Code with Lists
        // The LinkedHashSets have issues with iteration and transformation at same time
        // This has downstream.

        LinkedHashSet<BundleObject> temp = new LinkedHashSet<>();

        Iterator<BundleObject> iter = getObjects().iterator();
        while (iter.hasNext()){
            BundleObject object = iter.next();

            if (object instanceof StixDomainObject){
                temp.addAll(((StixDomainObject) object).getAllCommonPropertiesBundleObjects());
                temp.addAll(((StixDomainObject) object).getAllObjectSpecificBundleObjects());

            } else if (object instanceof StixDataMarking){
                temp.addAll(((StixDataMarking) object).getAllCommonPropertiesBundleObjects());

            } else if (object instanceof StixRelationshipObject){
                temp.addAll(((StixRelationshipObject) object).getAllCommonPropertiesBundleObjects());
                temp.addAll(((StixRelationshipObject) object).getAllObjectSpecificBundleObjects());
            }
        }

        temp.removeIf(Objects::isNull);

        System.out.println("DONE Auto Adding Bundle Objects");
        return temp;
    }
}
