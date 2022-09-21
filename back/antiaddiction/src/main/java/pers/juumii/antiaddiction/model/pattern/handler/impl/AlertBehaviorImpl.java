package pers.juumii.antiaddiction.model.pattern.handler.impl;

import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.translator.mapperrule.ManualSetRule;
import pers.juumii.antiaddiction.model.util.SpringUtils;
import pers.juumii.antiaddiction.model.util.external.Alert;

import java.util.List;

public class AlertBehaviorImpl implements ImplUnit{

    private final MomentaryBehavior guidedBehavior;
    private String form;

    public AlertBehaviorImpl(String guidedBehaviorName, String form) {
        ManualSetRule manualSetRule = SpringUtils.getBean(ManualSetRule.class);
        this.guidedBehavior = manualSetRule.getMapperBehaviorByName(guidedBehaviorName);
        this.form = form;
    }



    @Override
    public void handle() {
        List<EnvironmentData> environmentDataList = guidedBehavior.getOverallEnvironment().getDatum();
        alert(environmentDataList);
    }

    private void alert(List<EnvironmentData> environmentDataList) {
        List<Alert> alerts = SpringUtils.getSubclasses(Alert.class);
        Alert alert = alerts.get(0);
        alert.setBehaviorName(guidedBehavior.getName());
        alert.addShortcut(environmentDataList);
        alert.open();
    }

}
