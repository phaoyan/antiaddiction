package pers.juumii.antiaddiction.controller;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.translator.mapperrule.ManualSetRule;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;

@CrossOrigin
@RestController
public class MapperRuleManagerController {
    private static MapperRuleManagerController first;
    public static boolean isFirst(MapperRuleManagerController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(MapperRuleManagerController target){
        target.manualSetRule = first.manualSetRule;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private ManualSetRule manualSetRule;
    @PostMapping("/registeredBehaviors")
    public void postRegisteredBehaviorList(@RequestBody String jsonString){
        manualSetRule.setMapperBehaviorList(new Gson().fromJson(jsonString, JsonArray.class));
    }

    @GetMapping("/registeredBehaviors")
    public String getMapperBehaviorList(){
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(manualSetRule.getMapperBehaviorList());
    }

}
