package pers.juumii.antiaddiction.model.translator.mapperrule;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.SpringConfig;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;
import pers.juumii.antiaddiction.model.util.SpringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


@Service
public class ManualSetRule implements MapperRule{

    private ArrayList<MomentaryBehavior> mapperBehaviorList;

    @PostConstruct
    public void init(){
        try {
            File src = new File(Paths.getManualSetRuleSrc());
            if(!src.exists())
                src.createNewFile();
            mapperBehaviorList = new ArrayList<>();
            JsonArray json = AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), JsonArray.class);
            for(JsonElement jsonBehavior: json){
                mapperBehaviorList.add(AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(jsonBehavior,MomentaryBehavior.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void toFile(){
        File src = new File(Paths.getManualSetRuleSrc());
        try {
            FileUtils.writeStringToFile(src, AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(mapperBehaviorList), StandardCharsets.UTF_8);
        } catch (IOException e) {
            if(!src.exists()) {
                try {
                    src.createNewFile();
                    FileUtils.writeStringToFile(src, AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(mapperBehaviorList), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void setMapperBehaviorList(JsonArray json){
        this.mapperBehaviorList = new ArrayList<>();

        for(JsonElement jsonBehavior: json){
            MomentaryBehavior behavior = new MomentaryBehavior();
            behavior.setName(jsonBehavior.getAsJsonObject().get("name").getAsString());
            OverallEnvironment environment = readJsonEnvironment(jsonBehavior.getAsJsonObject().get("overallEnvironment").getAsJsonObject().get("datum").getAsJsonArray());
            behavior.setOverallEnvironment(environment);
            mapperBehaviorList.add(behavior);
        }

        toFile();
    }

    private OverallEnvironment readJsonEnvironment(JsonArray jsonEnvironment) {
        OverallEnvironment res = new OverallEnvironment();
        for(JsonElement data: jsonEnvironment){
            try {
                String className = data.getAsJsonObject().get("className").getAsString();
                Class<?> cls = Class.forName(className);
                res.mount((EnvironmentData) cls.getConstructor(JsonElement.class).newInstance(data));
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    @Override
    public ArrayList<MomentaryBehavior> getMapperBehaviorList() {
        return mapperBehaviorList;
    }

    public MomentaryBehavior getMapperBehaviorByName(String name){
        return mapperBehaviorList.get(mapperBehaviorList.indexOf(new MomentaryBehavior(name)));
    }

}
