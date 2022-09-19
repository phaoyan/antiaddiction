package pers.juumii.antiaddiction.model.pattern.handler.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.pattern.handler.impl.AddToStartupBatImpl;
import pers.juumii.antiaddiction.model.pattern.handler.impl.RemoveFromStartupBatImpl;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HandlerCreator {

    public static BehaviorHandler createAutoRunHandler(JsonObject jsonHandler){

        String type = jsonHandler.get("type").getAsString();
        String uri = jsonHandler.get("uri").getAsString();
        BehaviorHandler behaviorHandler = new BehaviorHandler();
        behaviorHandler.setName("auto run");
        JsonArray desc = new JsonArray();
        desc.add("Auto Run");
        desc.add(type);
        desc.add(uri);
        behaviorHandler.setDesc(desc);
        EventListener listenerOnCreate = new EventListener(new Event(behaviorHandler, EventType.CreateEvent));
        EventListener listenerOnDelete = new EventListener(new Event(behaviorHandler, EventType.DeleteEvent));
        listenerOnCreate.setImplUnit(new AddToStartupBatImpl(uri));
        listenerOnDelete.setImplUnit(new RemoveFromStartupBatImpl(uri));
        behaviorHandler.addListener(listenerOnCreate);
        behaviorHandler.addListener(listenerOnDelete);

        return behaviorHandler;
    }

    public static BehaviorHandler createHandler(JsonObject jsonHandler, JsonObject jsonSchema) {
        String autoRunHandlerSchemaSrc = Paths.getAutoRunHandlerSchemaSrc();
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        JsonObject autoRunHandlerSchema;
        try {
            autoRunHandlerSchema = gson.fromJson(FileUtils.readFileToString(new File(autoRunHandlerSchemaSrc), StandardCharsets.UTF_8), JsonObject.class);
            if(jsonSchema.equals(autoRunHandlerSchema))
                return createAutoRunHandler(jsonHandler);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static BehaviorHandler createHandler(String jsonString){
        Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
        JsonObject json = gson.fromJson(jsonString, JsonObject.class);
        JsonObject schema = json.get("schema").getAsJsonObject();
        JsonObject jsonHandler = json.get("handler").getAsJsonObject();

        BehaviorHandler handler = HandlerCreator.createHandler(jsonHandler, schema);
        if(handler == null)
            return null;
        handler.setMaterial(jsonString);
        return handler;
    }
}
