package pers.juumii.antiaddiction.model.translator;


import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

public class EnvironmentToBehaviorTranslator {

    private static final EnvironmentToBehaviorTranslator INSTANCE = new EnvironmentToBehaviorTranslator();
    private MapperRuleManager rule;
    private  PriorityList priorityList;

    public MapperRuleManager getRule() {
        return rule;
    }

    public void setRule(MapperRuleManager rule) {
        this.rule = rule;
    }

    public PriorityList getPriorityList() {
        return priorityList;
    }

    public void setPriorityList(PriorityList priorityList) {
        this.priorityList = priorityList;
    }

    public MomentaryBehavior translate(OverallEnvironment overallEnvironment){
        return priorityList.pick(rule.map(overallEnvironment));
    }

    public static EnvironmentToBehaviorTranslator getInstance(){
        return INSTANCE;
    }
}
