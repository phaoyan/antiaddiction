package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;

import java.net.URL;
import java.util.ArrayList;

public class WebsiteBrowsingDataCollector implements DataCollector{

    private static final WebsiteBrowsingDataCollector INSTANCE = new WebsiteBrowsingDataCollector();

    private ComputerEnvironment informationReceiver = new ComputerEnvironment();

    public ComputerEnvironment getInformationReceiver() {
        return informationReceiver;
    }

    public void setInformationReceiver(ComputerEnvironment informationReceiver) {
        this.informationReceiver = informationReceiver;
    }

    public void addBrowsingData(URL url){
        informationReceiver.getDatum().add(new WebsiteBrowsingData(url));
    }

    public void removeBrowsingData(URL url){
        informationReceiver.getDatum().removeIf(data->((WebsiteBrowsingData)data).getUrl().equals(url));
    }

    @Override
    public Environment collect() {
        if(informationReceiver == null)
            return null;

        ComputerEnvironment res = new ComputerEnvironment();
        res.construct(informationReceiver);
        return res;
    }

    public static WebsiteBrowsingDataCollector getInstance(){
        return INSTANCE;
    }
}
