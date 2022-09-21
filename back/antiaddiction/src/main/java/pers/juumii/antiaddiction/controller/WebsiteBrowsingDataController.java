package pers.juumii.antiaddiction.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.WebsiteBrowsingDataCollector;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import java.net.MalformedURLException;
import java.net.URL;

@CrossOrigin
@RestController
public class WebsiteBrowsingDataController {

    @PostMapping("/website/record/add")
    public void receiveWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        WebsiteBrowsingDataCollector websiteBrowsingDataCollector = SpringUtils.getBean(WebsiteBrowsingDataCollector.class);
        websiteBrowsingDataCollector.addBrowsingData(new URL(json));
        websiteBrowsingDataCollector.collect();
    }
    @PostMapping("/website/record/remove")
    public void removeWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        WebsiteBrowsingDataCollector websiteBrowsingDataCollector = SpringUtils.getBean(WebsiteBrowsingDataCollector.class);
        websiteBrowsingDataCollector.removeBrowsingData(new URL(json));
        websiteBrowsingDataCollector.collect();
    }
}
