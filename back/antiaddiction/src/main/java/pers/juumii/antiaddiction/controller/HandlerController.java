package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.handler.GlobalListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.pattern.handler.impl.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@CrossOrigin
@RestController
public class HandlerController {
    private static HandlerController first;
    public static boolean isFirst(HandlerController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(HandlerController target){
        target.globalListener = first.globalListener;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }
    @Autowired
    private GlobalListener globalListener;
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

    private void assignHandler(String jsonString, EventType eventType){
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        int index = jsonObject.get("index").getAsInt();
        if(index >= 0){
            //todo
        }
        BehaviorHandler handler = gson.fromJson(jsonObject.get("handler").getAsJsonObject(), BehaviorHandler.class);
        EventListener listener = new EventListener(eventType);
        listener.setHandler(handler);
        globalListener.addListener(listener);
    }

    @PostMapping("/handler/assign/onLoop")
    public void assignHandlerOnLoop(@RequestBody String jsonString){
        assignHandler(jsonString, EventType.LoopEvent);
    }

    @PostMapping("/handler/assign/onCreateAndDelete")
    public void assignHandlerOnCreateAndDelete(@RequestBody String jsonString){
        assignHandler(jsonString, EventType.CreateEvent);
        assignHandler(jsonString, EventType.DeleteEvent);
    }

    @PostMapping("/handler/assign/onStartup")
    public void assignHandlerOnStartup(@RequestBody String jsonString){
        assignHandler(jsonString, EventType.StartupEvent);
    }

    @PostMapping("/handler/delete")
    public void deleteHandler(@RequestBody int index){
        globalListener.removeListener(index);
    }
}
