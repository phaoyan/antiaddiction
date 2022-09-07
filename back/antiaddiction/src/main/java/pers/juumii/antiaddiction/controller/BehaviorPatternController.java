package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.juumii.antiaddiction.model.pattern.pattern.UntimedBehaviorSequence;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import java.util.Objects;

@RestController
public class BehaviorPatternController extends CORSController{

    @GetMapping("/patternOriginal")
    public String getPatternOriginal(@RequestParam String patternType){
        String resJson = null;
        Gson gson = AdaptedGsonProvider.getGsonWithSerializeAdapter();

        if(patternType.equals("untimedSequence"))
            resJson = gson.toJson(new UntimedBehaviorSequence());

        return resJson;
    }



}
