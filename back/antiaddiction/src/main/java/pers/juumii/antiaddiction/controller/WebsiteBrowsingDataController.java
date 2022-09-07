package pers.juumii.antiaddiction.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.WebsiteBrowsingDataCollector;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class WebsiteBrowsingDataController extends CORSController{


    @PostMapping("/website/record")
    public void receiveWebsiteBrowsingData(@RequestBody String url) throws MalformedURLException {
        WebsiteBrowsingDataCollector.getInstance().setInformationReceiver(new WebsiteBrowsingData(new URL(url)));
    }
}
