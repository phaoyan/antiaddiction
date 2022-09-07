package pers.juumii.antiaddiction.model.pattern.collector;

import org.springframework.lang.NonNull;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.pattern.pattern.UntimedBehaviorSequence;

import java.util.ArrayList;

public class UntimedBehaviorSequenceCollector {
    private static final UntimedBehaviorSequenceCollector INSTANCE = new UntimedBehaviorSequenceCollector();
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
    public ArrayList<UntimedBehaviorSequence> collect() {
        //从history中分析出patternList中包含的内容
        ArrayList<UntimedBehaviorSequence> res = new ArrayList<>();
        for(UntimedBehaviorSequence pattern: select()){
            for(UntimedBehaviorSequence temp: initSequence(getFirst(pattern), pattern.getBehaviorList().size()))
                if(temp.equivalent(pattern))
                    res.add(temp);
        }

        return res;
    }

    private ArrayList<UntimedBehaviorSequence> initSequence(TimedBehavior first, int size) {
        ArrayList<UntimedBehaviorSequence> res = new ArrayList<>();

        ArrayList<TimedBehavior> historyList = history.getHistory();
        for(TimedBehavior behavior: historyList){
            if(behavior.equals(first)){
                ArrayList<TimedBehavior> unit = new ArrayList<>();
                for(int i = historyList.indexOf(behavior); i != -1 && i < historyList.size() && i < historyList.indexOf(behavior) + size; i ++)
                    unit.add(historyList.get(i));
                res.add(new UntimedBehaviorSequence(unit));
            }
        }

        return res;
    }
    private TimedBehavior getFirst(@NonNull UntimedBehaviorSequence pattern) {
        return pattern.getBehaviorList().get(0);
    }
    private ArrayList<UntimedBehaviorSequence> select() {
        ArrayList<UntimedBehaviorSequence> res = new ArrayList<>();

        for(BehaviorPattern e: patternList)
            if(e.getClass().equals(UntimedBehaviorSequence.class))
                res.add((UntimedBehaviorSequence) e);

        return res;
    }

    public static UntimedBehaviorSequenceCollector getInstance(){
        return INSTANCE;
    }


}
