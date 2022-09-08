package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.WebsiteBrowsingDataCollector;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.WebsiteBrowsingData;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class WebsiteBrowsingDataController extends CORSController{


    @PostMapping("/website/record/add")
    public void receiveWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        System.out.println("add:"+json);
        WebsiteBrowsingDataCollector.getInstance().addBrowsingData(new URL(json));
        for(EnvironmentData data: WebsiteBrowsingDataCollector.getInstance().getInformationReceiver().getDatum())
            System.out.println(((WebsiteBrowsingData)data).getUrl());
    }

    @PostMapping("/website/record/remove")
    public void removeWebsiteBrowsingData(@RequestBody String json) throws MalformedURLException {
        System.out.println("remove:"+json);
        WebsiteBrowsingDataCollector.getInstance().removeBrowsingData(new URL(json));
        for(EnvironmentData data: WebsiteBrowsingDataCollector.getInstance().getInformationReceiver().getDatum())
            System.out.println(((WebsiteBrowsingData)data).getUrl());

    }
}
