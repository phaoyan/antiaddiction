package pers.juumii.antiaddiction.model.pattern.handler.impl;

import com.google.gson.JsonElement;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import java.io.IOException;

public class RunComputerProcessImpl extends RunImpl{

    public final String className = getClass().getName();
    public final String simplifiedName = "run computer process";

    private String path;

    public RunComputerProcessImpl(){}
    public RunComputerProcessImpl(JsonElement json){
        init(json);
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getSimplifiedName() {
        return simplifiedName;
    }

    @Override
    public void init(JsonElement json) {
        path = AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(json.getAsJsonObject().get("path"), String.class);
    }

    @Override
    public void handle(Event event) {
        try {
            Runtime.getRuntime().exec(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
