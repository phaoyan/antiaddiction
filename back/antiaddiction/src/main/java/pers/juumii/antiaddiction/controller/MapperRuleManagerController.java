package pers.juumii.antiaddiction.controller;

import com.google.gson.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.translator.mapperrule.ManualSetRule;


@RestController
public class MapperRuleManagerController extends CORSController{

    @PostMapping("/registeredBehaviors")
    public void postRegisteredBehaviorList(@RequestBody String jsonString){
        ManualSetRule.getInstance().setMapperBehaviorList(new Gson().fromJson(jsonString, JsonArray.class));
    }

    @GetMapping("/registeredBehaviors")
    public String getMapperBehaviorList(){
        return ManualSetRule.getInstance().getJson().toString();
    }

}
