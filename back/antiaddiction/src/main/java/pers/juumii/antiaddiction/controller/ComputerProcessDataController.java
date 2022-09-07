package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.environment.collector.ComputerProcessDataCollector;
import pers.juumii.antiaddiction.model.environment.collector.DataListCollector;

@RestController
public class ComputerProcessDataController  extends CORSController{

    @GetMapping("/processData")
    public String getData(){
        return new Gson().toJson(ComputerProcessDataCollector.getInstance().collect());
    }

    @PostMapping(value="/processData/ignoreList",produces = "application/json;charset=UTF-8")
    public void postIgnore(@RequestBody String processNameWithQuotes){
        String processName = processNameWithQuotes.split("\"")[1];

        ComputerProcessDataCollector.getInstance().addToIgnoreList(processName);
        DataListCollector.getInstance().updateDataList();
    }
}
