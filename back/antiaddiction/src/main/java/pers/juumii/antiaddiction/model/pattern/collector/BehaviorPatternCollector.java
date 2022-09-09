package pers.juumii.antiaddiction.model.pattern.collector;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;

import java.util.ArrayList;
import java.util.List;

@Service
public class BehaviorPatternCollector{


    @Autowired
    private BehaviorHistory history;
    @Autowired
    private PatternList patternList;
    private final List<BehaviorPattern> collection = new ArrayList<>(); //todo
    @Autowired
    private List<PatternCollector> collectors;

    public void collect(){
        collection.clear();
        for(PatternCollector collector: collectors)
            collection.addAll(collector.collect());
    }



}
