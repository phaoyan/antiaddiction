package pers.juumii.antiaddiction.model.pattern.handler.handler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.SpringConfig;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HandlerList {


    private List<BehaviorHandler> handlerList;

    @PostConstruct
    public void init(){
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
        return handlerList;
    }

    public void add(BehaviorHandler handler){
        if(handlerList == null)
            handlerList = new ArrayList<>();
        handlerList.add(handler);
        toFile();
    }

    public void remove(int index) {

        handlerList.remove(index);
        toFile();
    }
}
