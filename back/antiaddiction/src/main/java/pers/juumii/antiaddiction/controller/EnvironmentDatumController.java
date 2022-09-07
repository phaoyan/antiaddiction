package pers.juumii.antiaddiction.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;

import java.util.ArrayList;


@RestController
public class EnvironmentDatumController extends CORSController{

    @GetMapping("/environment")
    public ArrayList<EnvironmentData> getDatum(){
        return DataListCollector.getInstance().getEnvironment().getDatum();
    }
}
