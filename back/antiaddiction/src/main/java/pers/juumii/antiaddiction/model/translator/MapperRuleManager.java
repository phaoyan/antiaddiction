package pers.juumii.antiaddiction.model.translator;



import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MapperRuleManager {

    private static final MapperRuleManager INSTANCE = new MapperRuleManager();
    private ArrayList<MomentaryBehavior> behaviorList;

    public ArrayList<MomentaryBehavior> getBehaviorList() {
        return behaviorList;
    }

    public void setBehaviorList(ArrayList<MomentaryBehavior> behaviorList) {
        this.behaviorList = behaviorList;
    }
    public ArrayList<MomentaryBehavior> map(OverallEnvironment overallEnvironment){
        ArrayList<MomentaryBehavior> res = new ArrayList<>();
        for(MomentaryBehavior behavior: behaviorList){
            if(overallEnvironment.getDatum().containsAll(behavior.getOverallEnvironment().getDatum())){
                res.add(new MomentaryBehavior(overallEnvironment, LocalDateTime.now(), behavior.getName(), behavior.getDescription()));
            }
        }
        if(res.size() == 0)
            res.add(new MomentaryBehavior(overallEnvironment, LocalDateTime.now(), "undefined", "null"));

        return res;
    }

    public static MapperRuleManager getInstance(){
        return INSTANCE;
    }
}
