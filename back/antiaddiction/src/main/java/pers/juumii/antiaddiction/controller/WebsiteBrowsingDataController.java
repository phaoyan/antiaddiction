package pers.juumii.antiaddiction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.WebsiteBrowsingDataCollector;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;

@CrossOrigin
@RestController
public class WebsiteBrowsingDataController {

    private static WebsiteBrowsingDataController first;
    public static boolean isFirst(WebsiteBrowsingDataController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(WebsiteBrowsingDataController target){
        target.websiteBrowsingDataCollector = first.websiteBrowsingDataCollector;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }

    @Autowired
    private WebsiteBrowsingDataCollector websiteBrowsingDataCollector;



    @PostMapping("/website/record/add")
    public void receiveWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        websiteBrowsingDataCollector.addBrowsingData(new URL(json));
        websiteBrowsingDataCollector.collect();
    }
    @PostMapping("/website/record/remove")
    public void removeWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        websiteBrowsingDataCollector.removeBrowsingData(new URL(json));
        websiteBrowsingDataCollector.collect();
    }
}
