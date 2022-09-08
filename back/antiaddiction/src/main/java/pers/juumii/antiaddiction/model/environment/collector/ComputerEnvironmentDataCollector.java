package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;

import java.util.ArrayList;
import java.util.Collections;

public class ComputerEnvironmentDataCollector implements DataCollector{

    private static final ComputerEnvironmentDataCollector INSTANCE = new ComputerEnvironmentDataCollector();

    private ArrayList<ComputerDataCollector> collectors;

    public ArrayList<ComputerDataCollector> getCollectors() {
        return collectors;
    }

    public void setCollectors(ArrayList<ComputerDataCollector> collectors) {
        this.collectors = collectors;
    }

    public void addCollector(ComputerDataCollector collector){
        collectors.add(collector);
    }
    @Override
    public Environment collect(){ //用于收集computer environment data，任务委派给下层完成
        ComputerEnvironment res = new ComputerEnvironment();

        for(ComputerDataCollector collector: collectors)
            res.construct(collector.collect());

        res.getDatum().removeAll(Collections.singleton(null));

        return res;
    }

    public static ComputerEnvironmentDataCollector getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(ComputerDataCollector.class.getPackage().getName());
    }
}
