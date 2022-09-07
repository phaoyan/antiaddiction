package pers.juumii.antiaddiction.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.translator.PriorityList;
import pers.juumii.antiaddiction.model.translator.mapperrule.ManualSetRule;

import java.util.ArrayList;

@RestController
public class PriorityListController extends CORSController{
    @PostMapping("/priorityList")
    public void setPriorityList(){
        PriorityList.getInstance().setPriorityList(ManualSetRule.getInstance().getMapperBehaviorList());
    }
}
