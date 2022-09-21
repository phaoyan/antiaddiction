package pers.juumii.antiaddiction.model.pattern.handler.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.cptenviroment.ComputerProcess;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.pattern.handler.impl.*;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HandlerCreator {

    private enum SchemaType{
        AUTO_RUN_HANDLER_SCHEMA,
        CEASE_COMPUTER_PROCESS_HANDLER_SCHEMA,
        WEBSITE_REDIRECTION_HANDLER_SCHEMA,
        GUIDANCE_HANDLER_SCHEMA;

        public static JsonObject getSchema(SchemaType schemaType) throws IOException {
            Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
            return switch (schemaType){
                case AUTO_RUN_HANDLER_SCHEMA -> gson.fromJson(FileUtils.readFileToString(new File(Paths.getAutoRunHandlerSchemaSrc()), StandardCharsets.UTF_8), JsonObject.class);
                case CEASE_COMPUTER_PROCESS_HANDLER_SCHEMA -> gson.fromJson(FileUtils.readFileToString(new File(Paths.getCeaseComputerProcessHandlerSchemaSrc()), StandardCharsets.UTF_8), JsonObject.class);
                case WEBSITE_REDIRECTION_HANDLER_SCHEMA -> gson.fromJson(FileUtils.readFileToString(new File(Paths.getWebsiteRedirectionHandlerSchemaSrc()), StandardCharsets.UTF_8), JsonObject.class);
                case GUIDANCE_HANDLER_SCHEMA -> gson.fromJson(FileUtils.readFileToString(new File(Paths.getGuidanceHandlerSchemaSrc()), StandardCharsets.UTF_8), JsonObject.class);
            };
        }
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
    public static BehaviorHandler createHandler(JsonObject jsonHandler, JsonObject jsonSchema) {
        try{
            if(jsonSchema.equals(SchemaType.getSchema(SchemaType.AUTO_RUN_HANDLER_SCHEMA)))
                return createAutoRunHandler(jsonHandler);
            else if(jsonSchema.equals(SchemaType.getSchema(SchemaType.CEASE_COMPUTER_PROCESS_HANDLER_SCHEMA)))
                return createCeaseComputerProcessHandler(jsonHandler);
            else if(jsonSchema.equals(SchemaType.getSchema(SchemaType.WEBSITE_REDIRECTION_HANDLER_SCHEMA)))
                return createWebsiteRedirectionHandler(jsonHandler);
            else if(jsonSchema.equals(SchemaType.getSchema(SchemaType.GUIDANCE_HANDLER_SCHEMA)))
                return createGuidanceHandler(jsonHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("null exception happened in HandlerCreator.createHandler");
        return null;
    }

    private static BehaviorHandler createGuidanceHandler(JsonObject jsonHandler) {

        String guidingBehaviorName = jsonHandler.get("guidingBehavior").getAsString();
        String guidedBehaviorName = jsonHandler.get("guidedBehavior").getAsString();
        String timing = jsonHandler.get("confirming").getAsJsonObject().get("timing").getAsString();
        String form = jsonHandler.get("confirming").getAsJsonObject().get("form").getAsString();

        BehaviorHandler behaviorHandler = new BehaviorHandler();
        behaviorHandler.setName("Guide");
        JsonArray desc = new JsonArray();
        desc.add("Guide");
        desc.add("at " + timing + " of ");
        desc.add(guidingBehaviorName);
        desc.add("try to invoke:");
        desc.add(guidedBehaviorName);
        behaviorHandler.setDesc(desc);
        EventListener listenerOnLoop = new EventListener(new Event(EventType.LoopEvent));
        GuidanceImpl guidance = new GuidanceImpl(guidingBehaviorName, guidedBehaviorName, timing, form);
        listenerOnLoop.setImplUnit(guidance);
        behaviorHandler.addListener(listenerOnLoop);

        return behaviorHandler;
    }

    public static BehaviorHandler createAutoRunHandler(JsonObject jsonHandler){

        String type = jsonHandler.get("type").getAsString();
        String uri = jsonHandler.get("uri").getAsString();
        BehaviorHandler behaviorHandler = new BehaviorHandler();
        behaviorHandler.setName("Auto Run");
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
    private static BehaviorHandler createWebsiteRedirectionHandler(JsonObject jsonHandler) {

        String sourceUrl = jsonHandler.get("sourceUrl").getAsString();
        String targetUrl = jsonHandler.get("targetUrl").getAsString();
        BehaviorHandler behaviorHandler = new BehaviorHandler();
        behaviorHandler.setName("Website Redirection");
        JsonArray desc = new JsonArray();
        desc.add("Website Redirection");
        desc.add(sourceUrl);
        desc.add(targetUrl);
        behaviorHandler.setDesc(desc);
        EventListener listenerOnCreate = new EventListener(new Event(behaviorHandler, EventType.CreateEvent));
        EventListener listenerOnDelete = new EventListener(new Event(behaviorHandler, EventType.DeleteEvent));
        listenerOnCreate.setImplUnit(new AddToWebsiteRedirectionListImpl(sourceUrl,targetUrl));
        listenerOnDelete.setImplUnit(new RemoveFromWebsiteRedirectionListImpl(sourceUrl,targetUrl));
        behaviorHandler.addListener(listenerOnCreate);
        behaviorHandler.addListener(listenerOnDelete);

        return behaviorHandler;
    }

    private static BehaviorHandler createCeaseComputerProcessHandler(JsonObject jsonHandler) {
        String processName = jsonHandler.get("processName").getAsString();
        BehaviorHandler behaviorHandler = new BehaviorHandler();
        behaviorHandler.setName("Cease Computer Process");
        JsonArray desc = new JsonArray();
        desc.add("Cease Computer Process");
        desc.add(processName);
        behaviorHandler.setDesc(desc);
        EventListener listenerOnLoop = new EventListener(new Event(EventType.LoopEvent));
        listenerOnLoop.setImplUnit(new CeaseComputerProcessImpl(new ComputerProcess(processName)));
        behaviorHandler.addListener(listenerOnLoop);

        return behaviorHandler;
    }


}
