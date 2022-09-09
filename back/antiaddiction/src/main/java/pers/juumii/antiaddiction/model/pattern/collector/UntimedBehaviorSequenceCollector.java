package pers.juumii.antiaddiction.model.pattern.collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.behavior.BehaviorHistory;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.pattern.pattern.UntimedBehaviorSequence;

import java.util.ArrayList;

@Service
public class UntimedBehaviorSequenceCollector implements PatternCollector{
    @Autowired
    private BehaviorHistory history;
    @Autowired
    private PatternList patternList;
    @Override
    public ArrayList<BehaviorPattern> collect() {
        //从history中分析出patternList中包含的内容
        ArrayList<BehaviorPattern> res = new ArrayList<>();
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

        for(BehaviorPattern e: patternList.getPatterns())
            if(e.getClass().equals(UntimedBehaviorSequence.class))
                res.add((UntimedBehaviorSequence) e);

        return res;
    }



}
