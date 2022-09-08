package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

import java.util.ArrayList;

public class OverallEnvironmentDataCollector implements DataCollector{
    private static final OverallEnvironmentDataCollector INSTANCE = new OverallEnvironmentDataCollector();
    private ComputerEnvironmentDataCollector ceCollector;
    private ArrayList<String> ignoreList;

    public void setCeCollector(ComputerEnvironmentDataCollector ceCollector) {
        this.ceCollector = ceCollector;
    }

    public ArrayList<String> getIgnoreList() {
        return ignoreList;
    }

    public void setIgnoreList(ArrayList<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

    @Override
    public OverallEnvironment collect() {
        OverallEnvironment res = new OverallEnvironment();
        res.construct(ceCollector.collect());

        ignore(res);

        return res;
    }

    private void ignore(OverallEnvironment env) {
        if(ignoreList == null)
            return;
        for(String s: ignoreList)
            env.getDatum().removeIf(data->data.getIdCode() == s.hashCode());
    }

    public static OverallEnvironmentDataCollector getInstance() {
        return INSTANCE;
    }
}
