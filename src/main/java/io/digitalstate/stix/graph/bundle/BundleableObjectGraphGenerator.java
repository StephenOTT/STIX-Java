package io.digitalstate.stix.graph.bundle;

import io.digitalstate.stix.bundle.BundleableObject;
import io.digitalstate.stix.graph.sdo.DomainObjectGraphGenerator;
import io.digitalstate.stix.graph.GraphGenerator;
import io.digitalstate.stix.graph.sro.RelationshipSroGraphGenerator;
import io.digitalstate.stix.graph.sro.SightingSroGraphGenerator;
import io.digitalstate.stix.graph.elements.GraphElement;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.sro.objects.RelationshipSro;
import io.digitalstate.stix.sro.objects.SightingSro;

import java.util.HashSet;
import java.util.Set;

public class BundleableObjectGraphGenerator implements GraphGenerator {

    private BundleableObject object;

    public BundleableObjectGraphGenerator(BundleableObject object) {
        this.object = object;
    }

    public BundleableObject getObject() {
        return object;
    }

    public Set<GraphElement> process(){
        Set<GraphElement> items = new HashSet<>();

        Class<? extends BundleableObject> objectClass = object.getClass();

        if (DomainObject.class.isAssignableFrom(objectClass)){
            items.addAll(new DomainObjectGraphGenerator((DomainObject)object).process());

        } else if (RelationshipSro.class.isAssignableFrom(objectClass)){
            items.addAll(new RelationshipSroGraphGenerator((RelationshipSro)object).process());

        } else if (SightingSro.class.isAssignableFrom(objectClass)){
            items.addAll(
                    new SightingSroGraphGenerator((SightingSro) object).process()
            );
        }

        return items;
    }

}
