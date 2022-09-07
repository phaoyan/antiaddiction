package pers.juumii.antiaddiction.model.pattern.collector;


import org.springframework.lang.NonNull;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.pattern.pattern.TimedBehaviorSequence;

import java.util.ArrayList;

public class TimedBehaviorSequenceCollector {
    private static final TimedBehaviorSequenceCollector INSTANCE = new TimedBehaviorSequenceCollector();

    private BehaviorHistory history;
    private ArrayList<BehaviorPattern> patternList;
    public void setHistory(BehaviorHistory history) {
        this.history = history;
    }
    public void setPatternList(ArrayList<BehaviorPattern> patternList) {
        this.patternList = patternList;
    }
    public BehaviorHistory getHistory() {
        return history;
    }
    public ArrayList<BehaviorPattern> getPatternList() {
        return patternList;
    }

    public ArrayList<TimedBehaviorSequence> collect() {
        //从history中分析出patternList中包含的内容
        ArrayList<TimedBehaviorSequence> res = new ArrayList<>();

        for(TimedBehaviorSequence pattern: select()){
            for(TimedBehaviorSequence temp: initSequence(getFirst(pattern), pattern.getBehaviorList().size()))
                if(temp.equivalent(pattern))
                    res.add(temp);
        }

        return res;
    }

    private ArrayList<TimedBehaviorSequence> initSequence(TimedBehavior first, int size) {
        ArrayList<TimedBehaviorSequence> res = new ArrayList<>();

        ArrayList<TimedBehavior> historyList = history.getHistory();
        for(TimedBehavior behavior: historyList){
            if(behavior.equals(first)){
                ArrayList<TimedBehavior> unit = new ArrayList<>();
                for(int i = historyList.indexOf(behavior); i != -1 && i < historyList.size() && i < historyList.indexOf(behavior) + size; i ++)
                    unit.add(historyList.get(i));
                TimedBehaviorSequence sequence = new TimedBehaviorSequence();
                sequence.setBehaviorList(unit);
                sequence.registerAll();
                res.add(sequence);
            }
        }

        return res;
    }

    private TimedBehavior getFirst(@NonNull TimedBehaviorSequence pattern) {
        return pattern.getBehaviorList().get(0);
    }
    private ArrayList<TimedBehaviorSequence> select() {
        ArrayList<TimedBehaviorSequence> res = new ArrayList<>();

        for(BehaviorPattern e: patternList)
            if(e.getClass().equals(TimedBehaviorSequence.class))
                res.add((TimedBehaviorSequence) e);

        return res;
    }

    public static TimedBehaviorSequenceCollector getInstance(){
        return INSTANCE;
    }

}
