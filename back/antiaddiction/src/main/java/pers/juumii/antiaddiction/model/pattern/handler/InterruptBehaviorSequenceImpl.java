package pers.juumii.antiaddiction.model.pattern.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;

import java.time.Duration;

@Service
public class InterruptBehaviorSequenceImpl implements BehaviorHandler{

    public final String className = getClass().getName();
    public final String simplifiedName = "limit website access";

    @Autowired
    private BehaviorHistory behaviorHistory;
    private BehaviorHandler handler;
    private TimedBehavior former, latter;
    private int interval;

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
    public void handle() {
        if(former == null || latter == null)
            return;

        TimedBehavior last = behaviorHistory.getHistory().get(behaviorHistory.getHistory().size()-1);
        if(last.equals(former))
            former.setEndTime(last.getEndTime());
        else if(last.equals(latter))
            latter.setStartTime(last.getStartTime());

        if(Duration.between(former.getEndTime(),latter.getStartTime()).getNano() < interval)
            handler.handle();
    }


}
