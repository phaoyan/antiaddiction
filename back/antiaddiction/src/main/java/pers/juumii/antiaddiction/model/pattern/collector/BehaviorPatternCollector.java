package pers.juumii.antiaddiction.model.pattern.collector;


import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.util.IOCContainer;

import java.util.ArrayList;

public class BehaviorPatternCollector{

    private static final  BehaviorPatternCollector INSTANCE = new BehaviorPatternCollector();

    private BehaviorHistory history;
    private PatternList patternList;
    private ArrayList<BehaviorPattern> collection;
    private ArrayList<PatternCollector> collectors;
    private boolean flag;
    public PatternList getPatternList() {
        return patternList;
    }
    public void setPatternList(PatternList patternList) {
        this.patternList = patternList;
    }

    public ArrayList<PatternCollector> getCollectors() {
        return collectors;
    }

    public void setCollectors(ArrayList<PatternCollector> collectors) {
        this.collectors = collectors;
    }
    public void addCollector(PatternCollector collector){
        collectors.add(collector);
    }
    public BehaviorHistory getHistory() {
        return history;
    }
    public void setHistory(BehaviorHistory history) {
        this.history = history;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ArrayList<BehaviorPattern> getCollection() {
        return collection;
    }
    public void setCollection(ArrayList<BehaviorPattern> collection) {
        this.collection = collection;
    }

    public void collect(){
        collection.clear();

        for(PatternCollector collector: collectors)
            collection.addAll(collector.collect());


    }


    public static BehaviorPatternCollector getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        IOCContainer.initialize();
        BehaviorPatternCollector.getInstance().collect();
        BehaviorPatternCollector.getInstance().collection.forEach((pattern)-> System.out.println(pattern.getPatternType()));

    }
}
