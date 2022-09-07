package pers.juumii.antiaddiction.model.behavior;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimedBehavior{

    private LocalDateTime startTime, endTime;
    private String name;
    private ArrayList<MomentaryBehavior> details;

    public TimedBehavior(){
        details = new ArrayList<>();
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(ArrayList<MomentaryBehavior> details) {
        this.details = details;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public ArrayList<MomentaryBehavior> getDetails() {
        return details;
    }

    public ArrayList<OverallEnvironment> detailEnvironments(){
        ArrayList<OverallEnvironment> res = new ArrayList<>();
        for(MomentaryBehavior behavior: details)
            res.add(behavior.getOverallEnvironment());
        return res;
    }

    public boolean append(MomentaryBehavior behavior){
        boolean res;
        if(this.details.size() == 0){
            this.details.add(behavior);
            this.name = behavior.getName();
            res = true;
        }else if(this.details.get(0).getName().equals(behavior.getName())){
            this.details.add(behavior);
            res = true;
        }else res = false;

        updateTime();
        return res;
    }

    private void updateTime(){
        startTime = details.get(0).getMoment();
        endTime = details.get(details.size() - 1).getMoment();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TimedBehavior && (((TimedBehavior) obj).name.equals(this.name));
    }

}
