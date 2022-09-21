package pers.juumii.antiaddiction.model.pattern.handler.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HandlerList {


    private List<BehaviorHandler> handlerList;

    public void init(){
        if(handlerList == null)
            readFile();
    }

    public void toFile(){
        try {
            JsonArray materialList = new JsonArray();
            for(BehaviorHandler handler: handlerList)
                materialList.add(handler.getMaterial());
            FileUtils.writeStringToFile(new File(Paths.getHandlerListSrc()),AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(materialList), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readFile(){
        try {
            handlerList = new ArrayList<>();
            JsonArray materialList = AdaptedGsonProvider.getGsonWithSerializeAdapter().fromJson(FileUtils.readFileToString(new File(Paths.getHandlerListSrc()),StandardCharsets.UTF_8),JsonArray.class);
            if(materialList == null)
                return;
            for(JsonElement jsonString: materialList)
                handlerList.add(HandlerCreator.createHandler(jsonString.getAsString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<BehaviorHandler> getHandlerList() {
        init();
        return handlerList;
    }

    public void add(BehaviorHandler handler){
        init();
        if(handlerList == null)
            handlerList = new ArrayList<>();
        handlerList.add(handler);
        toFile();
    }

    public void remove(int index) {
        init();
        handlerList.remove(index);
        toFile();
    }
}
