package pers.juumii.antiaddiction.model.pattern.pattern;



import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;

import java.util.ArrayList;

public class BehaviorSet implements BehaviorPattern{


    @Override
    public String getPatternType() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public void setBehaviorList(ArrayList<TimedBehavior> behaviorList) {

    }

    @Override
    public ArrayList<TimedBehavior> getBehaviorList() {
        return null;
    }

    @Override
    public boolean equivalent(BehaviorPattern other) {
        return false;
    }

    @Override
    public void setHandler(BehaviorHandler handler) {

    }

    @Override
    public BehaviorHandler getHandler() {
        return null;
    }


}
