package pers.juumii.antiaddiction.model.util.external;

import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;

import java.util.List;

@Service
public abstract class Alert {
    protected String behaviorName;
    public void setBehaviorName(String behaviorName){
        this.behaviorName = behaviorName;
    }
    public abstract void addShortcut(List<EnvironmentData> environmentDataList);

    public abstract void open();
}
