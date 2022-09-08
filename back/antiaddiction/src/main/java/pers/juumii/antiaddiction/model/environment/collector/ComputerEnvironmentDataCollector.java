package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironmentData;

import java.util.Collections;

public class ComputerEnvironmentDataCollector implements DataCollector{

    private static final ComputerEnvironmentDataCollector INSTANCE = new ComputerEnvironmentDataCollector();
    private ComputerProcessDataCollector processDataCollector;

    private ComputerScreenDataCollector screenDataCollector;

    private ComputerNetInteractionDataCollector netDataCollector;
    private WebsiteBrowsingDataCollector websiteBrowsingDataCollector;

    public void setProcessDataCollector(ComputerProcessDataCollector processDataCollector) {
        this.processDataCollector = processDataCollector;
    }

    public void setScreenDataCollector(ComputerScreenDataCollector screenDataCollector) {
        this.screenDataCollector = screenDataCollector;
    }

    public void setNetDataCollector(ComputerNetInteractionDataCollector netDataCollector) {
        this.netDataCollector = netDataCollector;
    }

    public void setWebsiteBrowsingDataCollector(WebsiteBrowsingDataCollector websiteBrowsingDataCollector) {
        this.websiteBrowsingDataCollector = websiteBrowsingDataCollector;
    }

    @Override
    public Environment collect(){ //用于收集computer environment data，任务委派给下层完成
        ComputerEnvironment res = new ComputerEnvironment();


        res.construct(processDataCollector.collect());
        res.construct(screenDataCollector.collect());
        res.construct(netDataCollector.collect());
        res.construct(websiteBrowsingDataCollector.collect());


        res.getDatum().removeAll(Collections.singleton(null));

        return res;
    }

    public static ComputerEnvironmentDataCollector getInstance(){
        return INSTANCE;
    }
}
