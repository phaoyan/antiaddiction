package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;

public class ComputerNetInteractionDataCollector implements DataCollector{
    private static final ComputerNetInteractionDataCollector INSTANCE = new ComputerNetInteractionDataCollector();
    @Override
    public Environment collect() {
        return new ComputerEnvironment();
    }

    public static ComputerNetInteractionDataCollector getInstance(){
        return INSTANCE;
    }
}
