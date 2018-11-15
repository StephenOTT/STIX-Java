package io.digitalstate.stix.sdo;

import java.time.LocalDateTime;
import java.util.List;

public interface Campaign extends CommonProperties {
    public String getName();
    public void setName(String name);

    public String getDescription();
    public void setDescription(String description);

    public List<String> getAliases();
    public void setAliases(List<String> aliases);

    public LocalDateTime getFirstSeen();
    public void setFirstSeen(LocalDateTime firstSeen);

    public LocalDateTime getLastSeen();
    public void setLastSeen(LocalDateTime lastSeen);

    public String getObjective();
    public void setObjective(String objective);
}
