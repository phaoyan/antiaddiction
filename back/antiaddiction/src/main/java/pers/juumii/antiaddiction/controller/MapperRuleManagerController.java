package pers.juumii.antiaddiction.controller;

import com.google.gson.*;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.translator.mapperrule.ManualSetRule;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.SpringUtils;


@CrossOrigin
@RestController
public class MapperRuleManagerController {
    @PostMapping("/behavior/registered")
    public void postRegisteredBehaviorList(@RequestBody String jsonString){
        ManualSetRule manualSetRule = SpringUtils.getBean(ManualSetRule.class);
        manualSetRule.setMapperBehaviorList(new Gson().fromJson(jsonString, JsonArray.class));
    }
    @GetMapping("/behavior/registered")
    public String getMapperBehaviorList(){
        ManualSetRule manualSetRule = SpringUtils.getBean(ManualSetRule.class);
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(manualSetRule.getMapperBehaviorList());
    }

}
