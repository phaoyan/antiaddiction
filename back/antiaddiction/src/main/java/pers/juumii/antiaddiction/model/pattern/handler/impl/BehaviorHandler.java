package pers.juumii.antiaddiction.model.pattern.handler.impl;


import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;

public interface BehaviorHandler {

    String getClassName();
    String getSimplifiedName();
    void init(JsonElement json);
    void handle(Event event); //核心功能，处理behavior pattern
}
