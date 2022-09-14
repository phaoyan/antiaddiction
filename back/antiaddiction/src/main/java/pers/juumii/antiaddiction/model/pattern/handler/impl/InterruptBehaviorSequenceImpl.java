package pers.juumii.antiaddiction.model.pattern.handler.impl;


import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import java.time.Duration;

public class InterruptBehaviorSequenceImpl implements BehaviorHandler {

    public final String className = getClass().getName();
    public final String simplifiedName = "interrupt behavior sequence";

    private BehaviorHistory behaviorHistory;
    private BehaviorHandler handler;
    private TimedBehavior former, latter;
    private int interval;

    public InterruptBehaviorSequenceImpl(){}

    public InterruptBehaviorSequenceImpl(JsonElement json){
        init(json);
    }

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

    public BehaviorHandler getHandler() {
        return handler;
    }

    public void setHandler(BehaviorHandler handler) {
        this.handler = handler;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void init(JsonElement json) {

    }

    @Override
    public void handle(Event event) {
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
            handler.handle(event);
    }


}
