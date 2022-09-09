package pers.juumii.antiaddiction.model.behavior.collector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;

@Service
public class BehaviorHistoryCollector{


    @Autowired
    private BehaviorHistory history;
    @Autowired
    private TimedBehaviorCollector timedBehaviorCollector;

    public BehaviorHistory getHistory() {
        return history;
    }

    private BehaviorHistoryCollector(){}

    public void collect(){
        if(history == null)
            history = new BehaviorHistory();

        history.append(timedBehaviorCollector.getCurrentBehavior());
    }

}
