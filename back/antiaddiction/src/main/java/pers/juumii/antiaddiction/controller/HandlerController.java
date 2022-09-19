package pers.juumii.antiaddiction.controller;

import com.google.gson.JsonArray;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.pattern.handler.event.GlobalBroadcast;
import pers.juumii.antiaddiction.model.pattern.handler.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.handler.handler.HandlerCreator;
import pers.juumii.antiaddiction.model.pattern.handler.handler.HandlerList;
import pers.juumii.antiaddiction.model.util.*;

import javax.swing.*;
import java.util.List;

@CrossOrigin
@RestController
public class HandlerController {

    @PostMapping("handler/create")
    public void createHandler(@RequestBody String jsonString){
        BehaviorHandler handler = HandlerCreator.createHandler(jsonString);
        HandlerList handlerList = SpringUtils.getBean(HandlerList.class);
        handlerList.add(handler);
        GlobalBroadcast globalBroadcast = SpringUtils.getBean(GlobalBroadcast.class);
        globalBroadcast.onAction(new Event(handler, EventType.CreateEvent));
    }

    @PostMapping("handler/delete")
    public void deleteHandler(@RequestBody int index){
        HandlerList handlerList = SpringUtils.getBean(HandlerList.class);
        GlobalBroadcast globalBroadcast = SpringUtils.getBean(GlobalBroadcast.class);
        globalBroadcast.onAction(new Event(handlerList.getHandlerList().get(index), EventType.DeleteEvent));
        handlerList.remove(index);
    }

    @GetMapping("handler/list")
    public String getHandlerList(){
        JsonArray jsonHandlerList = new JsonArray();
        HandlerList handlerList = SpringUtils.getBean(HandlerList.class);
        for(BehaviorHandler handler: handlerList.getHandlerList())
            jsonHandlerList.add(handler.getDesc());

        return AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(jsonHandlerList);
    }


}
