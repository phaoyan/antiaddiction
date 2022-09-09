package pers.juumii.antiaddiction.model.behavior.collector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.collector.OverallEnvironmentDataCollector;
import pers.juumii.antiaddiction.model.translator.EnvironmentToBehaviorTranslator;

@Service
public class MomentaryBehaviorCollector{
    @Autowired
    private EnvironmentToBehaviorTranslator translator;
    @Autowired
    private OverallEnvironmentDataCollector collector;

    public MomentaryBehavior collect(){
        return translator.translate(collector.collect());
    }
}
