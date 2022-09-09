package pers.juumii.antiaddiction.model.behavior.collector;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;

@Service
public class TimedBehaviorCollector{

    @Autowired
    private MomentaryBehaviorCollector collector;
    private TimedBehavior currentBehavior;

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

}
