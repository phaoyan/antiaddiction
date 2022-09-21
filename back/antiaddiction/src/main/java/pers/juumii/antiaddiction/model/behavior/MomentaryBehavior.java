package pers.juumii.antiaddiction.model.behavior;

import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

import java.time.LocalDateTime;

public class MomentaryBehavior{

    private OverallEnvironment overallEnvironment;
    private LocalDateTime moment;
    private String name, description;

    public MomentaryBehavior(){}

    public MomentaryBehavior(String name) {
        this.name = name;
    }

    public MomentaryBehavior(OverallEnvironment overallEnvironment, LocalDateTime moment, String name, String description) {
        this.overallEnvironment = overallEnvironment;
        this.moment = moment;
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
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
    @Override
    public boolean equals(Object obj) {
        return obj instanceof MomentaryBehavior && (((MomentaryBehavior) obj).name.equals(this.name));
    }
}
