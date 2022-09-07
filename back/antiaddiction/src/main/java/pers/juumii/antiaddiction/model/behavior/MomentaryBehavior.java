package pers.juumii.antiaddiction.model.behavior;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

import java.time.LocalDateTime;

public class MomentaryBehavior{

    private OverallEnvironment overallEnvironment;

    private LocalDateTime moment;
    private String name, description;

    public MomentaryBehavior(){}
    public MomentaryBehavior(OverallEnvironment overallEnvironment, LocalDateTime moment, String name, String description) {
        this.overallEnvironment = overallEnvironment;
        this.moment = moment;
        this.name = name;
        this.description = description;
    }

    public OverallEnvironment getOverallEnvironment() {
        return overallEnvironment;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setOverallEnvironment(OverallEnvironment overallEnvironment) {
        this.overallEnvironment = overallEnvironment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MomentaryBehavior && (((MomentaryBehavior) obj).name.equals(this.name));
    }
}
