package pers.juumii.antiaddiction.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

@RestController
public class BehaviorHistoryController extends CORSController{


    private BehaviorHistoryCollector collector = BehaviorHistoryCollector.getInstance();

    public BehaviorHistoryCollector getCollector() {
        return collector;
    }

    public void setCollector(BehaviorHistoryCollector collector) {
        this.collector = collector;
    }

    @GetMapping("/history")
    public String getHistory(){
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(collector.getHistory());
    }



}
