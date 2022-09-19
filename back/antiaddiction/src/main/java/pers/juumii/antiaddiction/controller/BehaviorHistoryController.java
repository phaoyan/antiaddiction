package pers.juumii.antiaddiction.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.behavior.collector.BehaviorHistoryCollector;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.SpringUtils;


@CrossOrigin
@RestController
public class BehaviorHistoryController {

    @GetMapping("/history")
    public String getHistory(){
        BehaviorHistoryCollector collector = SpringUtils.getBean(BehaviorHistoryCollector.class);
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(collector.getHistory());
    }
}
