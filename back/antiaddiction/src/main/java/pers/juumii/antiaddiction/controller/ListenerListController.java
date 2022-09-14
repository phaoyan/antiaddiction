package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.handler.GlobalListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ListenerListController {
    private static ListenerListController first;
    public static boolean isFirst(ListenerListController target){
        if(first == null){
            first = target;
            return true;
        }
        return first == target;
    }
    public static void pointToFirst(ListenerListController target){
        target.globalListener = first.globalListener;
    }
    @PostConstruct
    public void init(){
        if(!isFirst(this))
            pointToFirst(this);
    }

    @Autowired
    private GlobalListener globalListener;

    @PostMapping("/listenerList")
    public void postListenerList(@RequestBody String json){
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        JsonArray jsonListeners = gson.fromJson(json, JsonArray.class);
        List<EventListener> listenerList = new ArrayList<>();
        for(JsonElement jsonListener: jsonListeners)
            listenerList.add(gson.fromJson(jsonListener.getAsJsonObject(), EventListener.class));
        globalListener.setListenerList(listenerList);
    }
    @GetMapping("/listenerList")
    public String getListenerList(){
        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(globalListener.getListenerList());
    }
}
