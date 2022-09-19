package pers.juumii.antiaddiction.model.translator;

import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Repository
public class PriorityList{

    private ArrayList<MomentaryBehavior> priorityList;
    public MomentaryBehavior pick(ArrayList<MomentaryBehavior> behaviorList){
        if(priorityList != null && priorityList.size() != 0)
            behaviorList.sort((o1, o2) -> o1.equals(o2) ? 0 : (priorityList.subList(priorityList.indexOf(o1),priorityList.size()-1).contains(o2) ? -1 : 1));
        return behaviorList.get(0);
    }

}
