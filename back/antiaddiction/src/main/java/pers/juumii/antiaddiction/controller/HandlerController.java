package pers.juumii.antiaddiction.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.pattern.handler.event.GlobalBroadcast;
import pers.juumii.antiaddiction.model.pattern.handler.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.handler.handler.HandlerCreator;
import pers.juumii.antiaddiction.model.pattern.handler.handler.HandlerList;
import pers.juumii.antiaddiction.model.util.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


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

    @GetMapping("handler/schema")
    public String getHandlerSchema(@RequestParam String name) throws IOException {
        return FileUtils.readFileToString(new File(Paths.getJsonSchemaPrefix() + name), StandardCharsets.UTF_8);
    }
}
