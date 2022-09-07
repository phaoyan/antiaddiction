package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;


@RestController
public class PatternListController extends CORSController{

    @PostMapping("/patternList")
    public void postPatternList(@RequestBody String json){
        try {
            if(json.getBytes()[0]=='[')
                PatternList.getInstance().setPatterns(new Gson().fromJson(json, JsonArray.class));
            if(json.getBytes()[0]=='{')
                PatternList.getInstance().addJsonPattern(new Gson().fromJson(json, JsonElement.class));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/patternList")
    public String getPatternList(){
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(PatternList.getInstance().getPatterns());
    }
}
