package pers.juumii.antiaddiction.model.pattern.pattern;


import pers.juumii.antiaddiction.model.behavior.TimedBehavior;

import java.util.ArrayList;


public interface BehaviorPattern {
    String getPatternType();
    String getClassName();
    void setBehaviorList(ArrayList<TimedBehavior> behaviorList);
    ArrayList<TimedBehavior> getBehaviorList();
    boolean equivalent(BehaviorPattern other);

}
