package pers.juumii.antiaddiction.model.pattern.handler.impl;

import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import java.util.List;

public class GuidanceImpl implements ImplUnit{

    private final String guidingBehaviorName;
    private final String guidedBehaviorName;
    private final String timing;
    private final String form;

    public GuidanceImpl(String guidingBehaviorName, String guidedBehaviorName, String timing, String form) {
        this.guidingBehaviorName = guidingBehaviorName;
        this.guidedBehaviorName = guidedBehaviorName;
        this.timing = timing;
        this.form = form;
    }

    @Override
    public void handle() {
        if(activated())
            new AlertBehaviorImpl(guidedBehaviorName, form).handle();
    }

    private boolean activated() {
        BehaviorHistory history = SpringUtils.getBean(BehaviorHistory.class);
        List<TimedBehavior> timedBehaviors = history.getHistory();
        if(timedBehaviors.size() == 0)
            return false;
        if(timing.equals("beginning"))
            return
//                    timedBehaviors.get(timedBehaviors.size()-1).getDetails().size() == 1 &&
                    timedBehaviors.get(timedBehaviors.size()-1).getDetails().get(0).equals(new MomentaryBehavior(guidingBehaviorName));
        if(timing.equals("ending"))
            return timedBehaviors.size() != 1 && timedBehaviors.get(timedBehaviors.size() - 2).getDetails().get(0).equals(new MomentaryBehavior(guidingBehaviorName));
        return false;
    }
}
