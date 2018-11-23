package io.digitalstate.stix.bundle;

import io.digitalstate.stix.datamarkings.StixDataMarking;
import io.digitalstate.stix.domainobjects.StixDomainObject;
import io.digitalstate.stix.helpers.StixSpecVersion;
import io.digitalstate.stix.relationshipobjects.StixRelationshipObject;

import java.util.*;

import static io.digitalstate.stix.helpers.IdGeneration.*;

public class Bundle extends BundleProperties {

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

//    /**
//     * Adds all Data Markings found in the objects of the bundle into the bundle as top level objects.
//     */
//    @JsonIgnore
//    public void autoAddDataMarkingsToBundle(){
//
//        LinkedHashSet<MarkingDefinition> definitions = new LinkedHashSet<>();
//
//        this.getObjects().forEach(object -> {
//            if (object instanceof DataMarkingsAppliable){
//                // Get the Object Marking Definitions
//                LinkedHashSet<MarkingDefinition> objectMarkings = ((DataMarkingsAppliable) object).getObjectMarkingRefs();
//                if (objectMarkings != null && !objectMarkings.isEmpty()){
//                    definitions.addAll(objectMarkings);
//                }
//
//                // Get the Granular Markings and for Each Granular get the related Marking Definition
//                LinkedHashSet<GranularMarking> granularMarkings = ((DataMarkingsAppliable) object).getGranularMarkings();
//                if (granularMarkings != null && !granularMarkings.isEmpty()){
//                    granularMarkings.forEach(gm -> definitions.add(gm.getMarkingRef()));
//                }
//            }
//        });
//
//        // Merge all of the gathered definitions into the objects property
//        this.getObjects().addAll(definitions);
//    }
//
//    /**
//     * Adds all Identities found in the created_by_ref property of domain objects, into the bundle as top level objects.
//     */
//    @JsonIgnore
//    public void autoAddCreatedByRefIdentities(){
//        LinkedHashSet<Identity> identities = new LinkedHashSet<>();
//
//        this.getObjects().forEach(object -> {
//            if (object instanceof StixDomainObject){
//                Identity createdByRef = ((StixDomainObject) object).getCreatedByRef();
//
//                if (createdByRef != null){
//                    identities.add(createdByRef);
//                }
//            } else if (object instanceof StixDataMarking){
//                Identity createdByRef = ((StixDataMarking) object).getCreatedByRef();
//
//                if (createdByRef != null){
//                    identities.add(createdByRef);
//                }
//            }
//        });
//
//        this.objects.addAll(identities);


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
