package pers.juumii.antiaddiction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;
import pers.juumii.antiaddiction.model.environment.collector.IgnoreList;
import pers.juumii.antiaddiction.model.environment.collector.OverallEnvironmentDataCollector;

import javax.annotation.PostConstruct;

@CrossOrigin
@RestController
public class IgnoreListController {
    @Autowired
    private IgnoreList ignoreList;
    @Autowired
    private OverallEnvironmentDataCollector overallEnvironmentDataCollector;
    @Autowired
    private DataListCollector dataListCollector;

    @PostMapping(value="/ignoreList",produces = "application/json;charset=UTF-8")
    public void postIgnore(@RequestBody String itemWithQuotes){
        String item = itemWithQuotes.split("\"")[1];
        ignoreList.addIgnore(item);
        dataListCollector.updateDataList();
    }
}
