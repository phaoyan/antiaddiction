package pers.juumii.antiaddiction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;

@CrossOrigin
@RestController
public class BehaviorHistoryController {

    private static BehaviorHistoryController first;
    public static boolean isFirst(BehaviorHistoryController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(BehaviorHistoryController target){
        target.collector = first.collector;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private BehaviorHistoryCollector collector;
    @GetMapping("/history")
    public String getHistory(){
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(collector.getHistory());
    }



}
