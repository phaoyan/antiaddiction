package pers.juumii.antiaddiction.model.pattern.collector;


import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;

import java.util.ArrayList;

public class BehaviorPatternCollector{

    private static final  BehaviorPatternCollector INSTANCE = new BehaviorPatternCollector();

    private BehaviorHistory history;
    private PatternList patternList;
    private UntimedBehaviorSequenceCollector untimedSequenceCollector;
    private TimedBehaviorSequenceCollector timedSequenceCollector;
    private ArrayList<BehaviorPattern> collection;
    private long interval;
    private boolean flag;
    public PatternList getPatternList() {
        return patternList;
    }
    public void setPatternList(PatternList patternList) {
        this.patternList = patternList;
    }
    public UntimedBehaviorSequenceCollector getUntimedSequenceCollector() {
        return untimedSequenceCollector;
    }
    public void setUntimedSequenceCollector(UntimedBehaviorSequenceCollector untimedSequenceCollector) {
        this.untimedSequenceCollector = untimedSequenceCollector;
    }
    public TimedBehaviorSequenceCollector getTimedSequenceCollector() {
        return timedSequenceCollector;
    }
    public void setTimedSequenceCollector(TimedBehaviorSequenceCollector timedSequenceCollector) {
        this.timedSequenceCollector = timedSequenceCollector;
    }

    public BehaviorHistory getHistory() {
        return history;
    }
    public void setHistory(BehaviorHistory history) {
        this.history = history;
    }
    public long getInterval() {
        return interval;
    }
    public void setInterval(long interval) {
        this.interval = interval;
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
        collection.addAll(untimedSequenceCollector.collect());
        collection.addAll(timedSequenceCollector.collect());
    }


    public static BehaviorPatternCollector getInstance(){
        return INSTANCE;
    }
}
