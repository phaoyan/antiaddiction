package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.PatternList;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

@RestController
public class AssignHandlerController extends CORSController{

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
        PatternList.getInstance().setHandler(index, handler);

    }

}
