package pers.juumii.antiaddiction.model.translator;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;
import pers.juumii.antiaddiction.model.translator.mapperrule.MapperRule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperRuleManager {

    @Autowired
    private List<MapperRule> mapperRules;
    public ArrayList<MomentaryBehavior> map(OverallEnvironment overallEnvironment){
        ArrayList<MomentaryBehavior> res = new ArrayList<>();
        for(MomentaryBehavior behavior: mapperRules.get(0).getMapperBehaviorList()){
            if(overallEnvironment.getDatum().containsAll(behavior.getOverallEnvironment().getDatum())){
                res.add(new MomentaryBehavior(overallEnvironment, LocalDateTime.now(), behavior.getName(), behavior.getDescription()));
            }
        }
        if(res.size() == 0)
            res.add(new MomentaryBehavior(overallEnvironment, LocalDateTime.now(), "undefined", "null"));

        return res;
    }



}
