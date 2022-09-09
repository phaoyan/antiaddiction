package pers.juumii.antiaddiction.model.pattern.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;

@Service
public class OverallHandler {

    @Autowired
    PatternList patternList;

    public void handle(){
        for(BehaviorPattern pattern: patternList.getPatterns())
            if(pattern.getHandler() != null)
                pattern.getHandler().handle();
    }
}
