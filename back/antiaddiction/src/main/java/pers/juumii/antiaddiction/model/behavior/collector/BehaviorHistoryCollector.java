package pers.juumii.antiaddiction.model.behavior.collector;


import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;

public class BehaviorHistoryCollector{

    private static final BehaviorHistoryCollector INSTANCE = new BehaviorHistoryCollector();
    private BehaviorHistory history;
    private TimedBehaviorCollector timedBehaviorCollector;

    private BehaviorHistoryCollector(){}

    public void setHistory(BehaviorHistory history) {
        this.history = history;
    }

    public void setTimedBehaviorCollector(TimedBehaviorCollector timedBehaviorCollector) {
        this.timedBehaviorCollector = timedBehaviorCollector;
    }

    public TimedBehaviorCollector getTimedBehaviorCollector() {
        return timedBehaviorCollector;
    }

    public BehaviorHistory getHistory() {
        return history;
    }

    public void collect(){
        if(history == null)
            history = new BehaviorHistory();

        history.append(timedBehaviorCollector.getCurrentBehavior());
    }

    public static BehaviorHistoryCollector getInstance(){
        return INSTANCE;
    }
}
