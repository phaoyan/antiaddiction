package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@CrossOrigin
@RestController
public class AssignHandlerController {
    private static AssignHandlerController first;
    public static boolean isFirst(AssignHandlerController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(AssignHandlerController target){
        target.patternList = first.patternList;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private PatternList patternList;
    @GetMapping("/handler/original")
    public String getHandlerOriginal(@RequestParam String simplifiedName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections(BehaviorHandler.class.getPackage().getName());
        for(Class<? extends BehaviorHandler> sub: reflections.getSubTypesOf(BehaviorHandler.class)){
            try {
                String simplifiedNameTemp = (String)sub.getDeclaredField("simplifiedName").get(sub.getConstructor().newInstance());
                if(simplifiedNameTemp.equals(simplifiedName)){
                    return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(sub.getConstructor().newInstance());
                }
            } catch (NoSuchFieldException ignored) {}
        }
        return null;
    }

    @PostMapping("/handler/assign")
    public void assignHandler(@RequestBody String jsonString){
        System.out.println(jsonString);
        //json中传一个数字索引和一个handler的信息，数字索引通过PatternList对应behavior pattern
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        JsonObject json = gson.fromJson(jsonString, JsonObject.class);
        int index = json.get("index").getAsInt();
        BehaviorHandler handler = gson.fromJson(json.get("handler").getAsJsonObject(), BehaviorHandler.class);
        patternList.setHandler(index, handler);

    }

}
