package pers.juumii.antiaddiction.model.behavior.collector;


import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.collector.OverallEnvironmentDataCollector;
import pers.juumii.antiaddiction.model.translator.EnvironmentToBehaviorTranslator;

public class MomentaryBehaviorCollector{
    private static final MomentaryBehaviorCollector INSTANCE = new MomentaryBehaviorCollector();
    private EnvironmentToBehaviorTranslator translator;
    private OverallEnvironmentDataCollector collector;

    public void setTranslator(EnvironmentToBehaviorTranslator translator) {
        this.translator = translator;
    }

    public void setCollector(OverallEnvironmentDataCollector collector) {
        this.collector = collector;
    }

    public MomentaryBehavior collect(){
        return translator.translate(collector.collect());
    }
    public static MomentaryBehaviorCollector getInstance(){
        return INSTANCE;
    }
}
