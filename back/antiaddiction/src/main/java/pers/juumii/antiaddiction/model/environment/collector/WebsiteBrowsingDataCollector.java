package pers.juumii.antiaddiction.model.environment.collector;



import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.environment.environment.Environment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerEnvironment;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;

import javax.annotation.PostConstruct;
import java.net.URL;

@Service
public class WebsiteBrowsingDataCollector implements ComputerDataCollector{
    private final ComputerEnvironment informationReceiver = new ComputerEnvironment();

    @PostConstruct
    public void init(){
        System.out.println(this);
    }
    public void addBrowsingData(URL url){
        informationReceiver.getDatum().add(new WebsiteBrowsingData(url));
        System.out.println(this + ": " + url.toString());

    }
    public synchronized void removeBrowsingData(URL url){
        informationReceiver.getDatum().removeIf(data->((WebsiteBrowsingData)data).getUrl().equals(url));
    }
    @Override
    public Environment collect() {
        ComputerEnvironment res = new ComputerEnvironment();
        res.construct(informationReceiver);
        return res;
    }
}
