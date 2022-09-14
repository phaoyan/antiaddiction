package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;

@CrossOrigin
@RestController
public class PatternListController {

    private static PatternListController first;
    public static boolean isFirst(PatternListController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(PatternListController target){
        target.patternList = first.patternList;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private PatternList patternList;
    @PostMapping("/pattern/list")
    public void postPatternList(@RequestBody String json){
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
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(patternList.getPatterns());
    }
}
