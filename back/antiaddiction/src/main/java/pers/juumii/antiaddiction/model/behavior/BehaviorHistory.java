package pers.juumii.antiaddiction.model.behavior;

import com.google.gson.*;
import pers.juumii.antiaddiction.model.util.FileRelated;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BehaviorHistory{
    private ArrayList<TimedBehavior> history;
    private LocalDateTime startTime, endTime;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setHistory(ArrayList<TimedBehavior> history) {
        this.history = history;
    }

    public ArrayList<TimedBehavior> getHistory() {
        return history;
    }

    public void append(TimedBehavior behavior){
        if(history == null){
            history = new ArrayList<>();
        }
        if(behavior == null)
            return;
        if(history.size() != 0 && behavior.getStartTime().equals(history.get(history.size()-1).getStartTime()))
            history.remove(history.size()-1);
        history.add(behavior);
        update();
    }

    private void updateTime(){
        setStartTime(history.get(0).getStartTime());
        setEndTime(history.get(history.size() - 1).getEndTime());
    }

    public void update(){
        updateTime();
        FileRelated.toJsonFile(
                new File(Paths.HISTORY_PATH + "/" + startTime.toLocalDate() + ".json"),
                this);
    }
}
