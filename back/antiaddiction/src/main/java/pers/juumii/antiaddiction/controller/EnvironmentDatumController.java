package pers.juumii.antiaddiction.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.SpringUtils;



@CrossOrigin
@RestController
public class EnvironmentDatumController {

    @GetMapping("/environment")
    public String getDatum(){
        DataListCollector dataListCollector = SpringUtils.getBean(DataListCollector.class);
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(dataListCollector.getEnvironment().getDatum());
    }
}
