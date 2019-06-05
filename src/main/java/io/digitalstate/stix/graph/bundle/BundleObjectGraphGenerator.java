package io.digitalstate.stix.graph.bundle;

import io.digitalstate.stix.bundle.BundleObject;
import io.digitalstate.stix.graph.GraphGenerator;
import io.digitalstate.stix.graph.elements.GraphElement;

import java.util.HashSet;
import java.util.Set;

public class BundleObjectGraphGenerator implements GraphGenerator {

    private BundleObject object;

    public BundleObjectGraphGenerator(BundleObject object) {
        this.object = object;
    }

    public BundleObject getObject() {
        return object;
    }

    public Set<GraphElement> process(){
        Set<GraphElement> items = new HashSet<>();

        object.getObjects().forEach(o->{
            items.addAll(new BundleableObjectGraphGenerator(o).process());
        });
        return items;
    }

}
