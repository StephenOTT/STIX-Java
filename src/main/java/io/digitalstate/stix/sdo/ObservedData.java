package io.digitalstate.stix.sdo;

import io.digitalstate.stix.sdo.impl.types.CyberObservableObject;

import java.time.LocalDateTime;
import java.util.List;

public interface ObservedData extends CommonProperties {
    public LocalDateTime getFirstObserved();
    public void setFirstObserved(LocalDateTime firstObserved);

    public LocalDateTime getLastObserved();
    public void setLastObserved(LocalDateTime lastObserved);

    public Integer getNumberObserved();
    public void setNumberObserved(Integer numberObserved);

    public List<CyberObservableObject> getObjects();
    public void setObjects(List<CyberObservableObject> objects);
}

