package pers.juumii.antiaddiction.model.pattern.handler.impl;


import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import java.time.Duration;

public class InterruptBehaviorSequenceImpl implements ImplUnit {

    public final String className = getClass().getName();
    public final String simplifiedName = "interrupt behavior sequence";

    private BehaviorHistory behaviorHistory;
    private ImplUnit handler;
    private TimedBehavior former, latter;
    private int interval;

    public InterruptBehaviorSequenceImpl(){}

    public TimedBehavior getFormer() {
        return former;
    }

    public void setFormer(TimedBehavior former) {
        this.former = former;
    }

    public TimedBehavior getLatter() {
        return latter;
    }

    public void setLatter(TimedBehavior latter) {
        this.latter = latter;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public ImplUnit getHandler() {
        return handler;
    }

    public void setHandler(ImplUnit handler) {
        this.handler = handler;
    }


    public String getClassName() {
        return className;
    }


    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void handle() {
        if(former == null || latter == null)
            return;
        if(behaviorHistory == null)
            behaviorHistory = SpringUtils.getBean(BehaviorHistory.class);

        TimedBehavior last = behaviorHistory.getHistory().get(behaviorHistory.getHistory().size()-1);
        if(last.equals(former))
            former.setEndTime(last.getEndTime());
        else if(last.equals(latter))
            latter.setStartTime(last.getStartTime());

        if(Duration.between(former.getEndTime(),latter.getStartTime()).getNano() < interval)
            handler.handle();
    }


}
