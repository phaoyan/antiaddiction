package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.SpringUtils;


@CrossOrigin
@RestController
public class PatternListController {
    @PostMapping("/pattern/list")
    public void postPatternList(@RequestBody String json){
        PatternList patternList = SpringUtils.getBean(PatternList.class);
        try {
            if(json.getBytes()[0]=='[')
                patternList.setPatterns(new Gson().fromJson(json, JsonArray.class));
            if(json.getBytes()[0]=='{')
                patternList.addJsonPattern(new Gson().fromJson(json, JsonElement.class));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/pattern/list")
    public String getPatternList(){
        PatternList patternList = SpringUtils.getBean(PatternList.class);
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(patternList.getPatterns());
    }
}
