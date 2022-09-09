package pers.juumii.antiaddiction.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


@CrossOrigin
@RestController
public class EnvironmentDatumController {
    private static EnvironmentDatumController first;
    public static boolean isFirst(EnvironmentDatumController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(EnvironmentDatumController target){
        target.dataListCollector = first.dataListCollector;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private DataListCollector dataListCollector;
    @GetMapping("/environment")
    public ArrayList<EnvironmentData> getDatum(){
        return dataListCollector.getEnvironment().getDatum();
    }
}
