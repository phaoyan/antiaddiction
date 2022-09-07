package pers.juumii.antiaddiction.model.environment.collector;


import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;

public class WebsiteBrowsingDataCollector implements DataCollector{

    private static final WebsiteBrowsingDataCollector INSTANCE = new WebsiteBrowsingDataCollector();

    private WebsiteBrowsingData informationReceiver = new WebsiteBrowsingData();

    public WebsiteBrowsingData getInformationReceiver() {
        return informationReceiver;
    }

    public void setInformationReceiver(WebsiteBrowsingData informationReceiver) {
        this.informationReceiver = informationReceiver;
    }

    @Override
    public Environment collect() {
        ComputerEnvironment res = new ComputerEnvironment();
        res.mount(informationReceiver);
        return res;
    }

    public static WebsiteBrowsingDataCollector getInstance(){
        return INSTANCE;
    }
}
