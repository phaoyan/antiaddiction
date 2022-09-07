package pers.juumii.antiaddiction.model.pattern.pattern;

import com.google.gson.JsonElement;
import org.apache.commons.math3.util.Pair;
import pers.juumii.antiaddiction.model.behavior.TimedBehavior;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TimedBehaviorSequence implements BehaviorPattern {

    public final String className = getClass().getName();
    public final String patternType = "timedSequence";
    private ArrayList<TimedBehavior> behaviorList;
    private HashMap<TimedBehavior, Pair<Duration, Integer>> behaviorDurationMap; // 1: max; 0: equal; -1: min
    private HashMap<TimedBehavior, Pair<LocalDateTime, Integer>> behaviorStartTimeHashMap; // 1: after; 0:equal; -1:before
    private HashMap<TimedBehavior, Pair<LocalDateTime, Integer>> behaviorEndTimeHashMap; // 1:after; 0:equal; -1:before

    public TimedBehaviorSequence() {
    }
    public TimedBehaviorSequence(JsonElement json){
        //todo
    }

    public void registerDurationLimitation(TimedBehavior behavior, Duration duration, int flag){
        if(behaviorDurationMap == null)
            behaviorDurationMap = new HashMap<>();
        behaviorDurationMap.put(getBehaviorList().get(getBehaviorList().indexOf(behavior)), new Pair<>(duration, flag));
    }
    public HashMap<TimedBehavior, Pair<Duration, Integer>> getBehaviorDurationMap() {
        return behaviorDurationMap;
    }

    public void registerStartTimeLimitation(TimedBehavior behavior, LocalDateTime startTime, int flag){
        if(behaviorStartTimeHashMap == null)
            behaviorStartTimeHashMap = new HashMap<>();
        behaviorStartTimeHashMap.put(getBehaviorList().get(getBehaviorList().indexOf(behavior)), new Pair<>(startTime, flag));
    }
    public HashMap<TimedBehavior, Pair<LocalDateTime, Integer>> getBehaviorStartTimeHashMap() {
        return behaviorStartTimeHashMap;
    }
    public void registerEndTimeLimitation(TimedBehavior behavior, LocalDateTime endTime, int flag){
        if(behaviorEndTimeHashMap == null)
            behaviorEndTimeHashMap = new HashMap<>();
        behaviorEndTimeHashMap.put(getBehaviorList().get(getBehaviorList().indexOf(behavior)),  new Pair<>(endTime, flag));
    }
    public HashMap<TimedBehavior, Pair<LocalDateTime, Integer>> getBehaviorEndTimeHashMap() {
        return behaviorEndTimeHashMap;
    }

    public void registerAll(){
        for(TimedBehavior behavior: behaviorList){
            registerDurationLimitation(behavior, Duration.between(behavior.getEndTime(),behavior.getStartTime()), 0);
            registerStartTimeLimitation(behavior, behavior.getStartTime(), 0);
            registerEndTimeLimitation(behavior, behavior.getEndTime(), 0);
        }
    }
    private boolean timeEquivalent(TimedBehaviorSequence other){ //要求other已经通过sequence的equivalent检查
        for(int i = 0; i < getBehaviorList().size(); i ++){
            TimedBehavior behavior1 = this.getBehaviorList().get(i);
            TimedBehavior behavior2 = other.getBehaviorList().get(i);

            Pair<Duration, Integer>duration1 = this.getBehaviorDurationMap().get(behavior1);
            Pair<Duration, Integer>duration2 = other.getBehaviorDurationMap().get(behavior2);
            if(!(duration1 == null) &&
                    ((duration2 == null) ||
                    (duration1.getSecond() == 1 && duration1.getFirst().toNanos() <= duration2.getFirst().toNanos()) ||
                    (duration1.getSecond() == 0 && duration1.getFirst().toNanos() != duration2.getFirst().toNanos()) ||
                    (duration1.getSecond() == -1 && duration1.getFirst().toNanos() >= duration2.getFirst().toNanos())))
                return false;

            Pair<LocalDateTime, Integer> startTime1 = this.getBehaviorStartTimeHashMap().get(behavior1);
            Pair<LocalDateTime, Integer> startTime2 = other.getBehaviorStartTimeHashMap().get(behavior2);
            if(!(startTime1 == null) &&
                    ((startTime2 == null) ||
                    (startTime1.getSecond() == 1 && !startTime1.getFirst().isAfter(startTime2.getFirst())) ||
                    (startTime1.getSecond() == 0 && !startTime1.getFirst().equals(startTime2.getFirst())) ||
                    (startTime1.getSecond() == -1 && !startTime1.getFirst().isBefore(startTime2.getFirst()))))
                return false;

            Pair<LocalDateTime, Integer> endTime1 = this.getBehaviorEndTimeHashMap().get(behavior1);
            Pair<LocalDateTime, Integer> endTime2 = other.getBehaviorEndTimeHashMap().get(behavior2);
            if(!(endTime1 == null) &&
                    ((endTime2 == null) ||
                    (endTime1.getSecond() == 1 && !endTime1.getFirst().isAfter(endTime2.getFirst())) ||
                    (endTime1.getSecond() == 0 && !endTime1.getFirst().equals(endTime2.getFirst())) ||
                    (endTime1.getSecond() == -1 && !endTime1.getFirst().isBefore(endTime2.getFirst()))))
                return false;
        }

        return true;
    }


    @Override
    public String getPatternType() {
        return patternType;
    }
    @Override
    public String getClassName() {
        return className;
    }

    public void setBehaviorList(ArrayList<TimedBehavior> behaviorList) {
        this.behaviorList = behaviorList;
    }
    @Override
    public ArrayList<TimedBehavior> getBehaviorList() {
        return behaviorList;
    }
    @Override
    public boolean equivalent(BehaviorPattern other) {
        if(!(other instanceof TimedBehaviorSequence))
            return false;

        for(int i = 0; i < getBehaviorList().size(); i ++)
            if(!other.getBehaviorList().get(i).getName().equals(this.getBehaviorList().get(i).getName()))
                return false;

        return timeEquivalent((TimedBehaviorSequence) other);
    }

    @Override
    public void setHandler(BehaviorHandler handler) {

    }

    @Override
    public BehaviorHandler getHandler() {
        return null;
    }

}
