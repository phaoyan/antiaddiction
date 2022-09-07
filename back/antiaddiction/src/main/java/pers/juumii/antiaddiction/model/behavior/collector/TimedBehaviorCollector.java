package pers.juumii.antiaddiction.model.behavior.collector;



import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimedBehaviorCollector{

    private static final TimedBehaviorCollector INSTANCE = new TimedBehaviorCollector();
    private MomentaryBehaviorCollector collector;
    private TimedBehavior currentBehavior;
    private long period;

    public void setCollector(MomentaryBehaviorCollector collector) {
        this.collector = collector;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public TimedBehavior getCurrentBehavior() {
        return currentBehavior;
    }

    public void collect(){
        if(currentBehavior == null)
            currentBehavior = new TimedBehavior();

        MomentaryBehavior momentaryBehavior = collector.collect();
        if(!currentBehavior.append(momentaryBehavior)){
            currentBehavior = new TimedBehavior();
            currentBehavior.append(momentaryBehavior);
        }
    }

    public static TimedBehaviorCollector getInstance(){
        return INSTANCE;
    }
}
