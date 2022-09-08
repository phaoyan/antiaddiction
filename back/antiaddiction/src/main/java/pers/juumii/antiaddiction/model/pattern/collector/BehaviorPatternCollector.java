package pers.juumii.antiaddiction.model.pattern.collector;


import org.reflections.Reflections;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.util.IOCContainer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class BehaviorPatternCollector{

    private static final  BehaviorPatternCollector INSTANCE = new BehaviorPatternCollector();

    private BehaviorHistory history;
    private PatternList patternList;
    private UntimedBehaviorSequenceCollector untimedSequenceCollector;
    private TimedBehaviorSequenceCollector timedSequenceCollector;
    private ArrayList<BehaviorPattern> collection;
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

        Reflections reflections = new Reflections(PatternCollector.class.getPackage().getName());
        Set<Class<? extends PatternCollector>> classes = reflections.getSubTypesOf(PatternCollector.class);
        for(Class<? extends  PatternCollector> cl: classes){
            try {
                collection.addAll((ArrayList<? extends BehaviorPattern>)cl.getMethod("collect").invoke(cl.getMethod("getInstance").invoke(null)));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

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
