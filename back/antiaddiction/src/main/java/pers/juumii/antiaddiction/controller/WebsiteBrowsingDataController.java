package pers.juumii.antiaddiction.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.WebsiteBrowsingDataCollector;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class WebsiteBrowsingDataController extends CORSController{


    @PostMapping("/website/record/add")
    public void receiveWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        WebsiteBrowsingDataCollector.getInstance().addBrowsingData(new URL(json));
    }

    @PostMapping("/website/record/remove")
    public void removeWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        WebsiteBrowsingDataCollector.getInstance().removeBrowsingData(new URL(json));
    }
}
