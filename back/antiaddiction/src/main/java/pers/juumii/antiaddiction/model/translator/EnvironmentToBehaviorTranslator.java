package pers.juumii.antiaddiction.model.translator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

@Service
public class EnvironmentToBehaviorTranslator {

    @Autowired
    private MapperRuleManager rule;
    @Autowired
    private  PriorityList priorityList;
    public MomentaryBehavior translate(OverallEnvironment overallEnvironment){
        return priorityList.pick(rule.map(overallEnvironment));
    }

}
