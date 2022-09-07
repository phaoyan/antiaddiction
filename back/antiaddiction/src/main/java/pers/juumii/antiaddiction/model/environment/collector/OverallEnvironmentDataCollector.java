package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

public class OverallEnvironmentDataCollector implements DataCollector{
    private static final OverallEnvironmentDataCollector INSTANCE = new OverallEnvironmentDataCollector();
    private ComputerEnvironmentDataCollector ceCollector;

    public void setCeCollector(ComputerEnvironmentDataCollector ceCollector) {
        this.ceCollector = ceCollector;
    }

    @Override
    public OverallEnvironment collect() {
        OverallEnvironment res = new OverallEnvironment();
        res.construct(ceCollector.collect());

        return res;
    }

    public static OverallEnvironmentDataCollector getInstance() {
        return INSTANCE;
    }
}
