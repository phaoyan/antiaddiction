package pers.juumii.antiaddiction.model.translator.mapperrule;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.behavior.MomentaryBehavior;
import pers.juumii.antiaddiction.model.environment.environment.EnvironmentData;
import pers.juumii.antiaddiction.model.environment.environment.OverallEnvironment;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ManualSetRule implements MapperRule{
    private static final ManualSetRule INSTANCE = new ManualSetRule();

    private File src;
    private JsonArray json;
    private ArrayList<MomentaryBehavior> mapperBehaviorList;

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

    public void readFile(){
        try {
            this.json = new Gson().fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), JsonArray.class);
            setMapperBehaviorList(json);
        } catch (IOException | NullPointerException e) {
            this.json = new JsonArray();
            this.mapperBehaviorList = new ArrayList<>();
        }
    }

    public void toFile(){
        try {
            FileUtils.writeStringToFile(src, json.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            if(!src.exists()) {
                try {
                    src.createNewFile();
                    FileUtils.writeStringToFile(src, json.toString(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void setMapperBehaviorList(JsonArray json){
        this.json = json;
        this.mapperBehaviorList = new ArrayList<>();

        for(JsonElement jsonBehavior: json){
            MomentaryBehavior behavior = new MomentaryBehavior();
            behavior.setName(jsonBehavior.getAsJsonObject().get("name").getAsString());
            OverallEnvironment environment = readJsonEnvironment(jsonBehavior.getAsJsonObject().get("environment").getAsJsonArray());
            behavior.setOverallEnvironment(environment);
            mapperBehaviorList.add(behavior);
        }

        toFile();
    }

    public void setSrc(File src) {
        this.src = src;
    }

    public JsonArray getJson() {
        return json;
    }

    @Override
    public ArrayList<MomentaryBehavior> getMapperBehaviorList() {
        return mapperBehaviorList;
    }

    public static ManualSetRule getInstance(){
        return INSTANCE;
    }
}
