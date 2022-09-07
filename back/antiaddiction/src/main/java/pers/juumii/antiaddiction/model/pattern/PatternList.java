package pers.juumii.antiaddiction.model.pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.pattern.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class PatternList {
    private static final PatternList INSTANCE = new PatternList();
    private ArrayList<BehaviorPattern> patterns;
    private File src;
    public void setPatterns(ArrayList<BehaviorPattern> patterns) {
        this.patterns = patterns;
    }

    public void setPatterns(JsonArray json) throws ClassNotFoundException {
        if(json == null){
            this.patterns = new ArrayList<>();
            toFile();
        }else{
            this.patterns.clear();
            for(JsonElement e: json){
                addJsonPattern(e);
            }
        }

    }

    public void addJsonPattern(JsonElement jsonPattern) throws ClassNotFoundException {
        patterns.add(AdaptedGsonProvider.getGsonWithDeserializeAdapter().fromJson(jsonPattern, BehaviorPattern.class));
        toFile();
    }

    public ArrayList<BehaviorPattern> getPatterns() {
        return patterns;
    }

    public void setHandler(int index, BehaviorHandler handler){
        patterns.get(index).setHandler(handler);
        toFile();
    }

    public ArrayList<BehaviorHandler> getHandlers(){
        ArrayList<BehaviorHandler> res = new ArrayList<>();

        for(BehaviorPattern pattern: patterns){
            res.add(pattern.getHandler());
        }

        return res;
    }

    public File getSrc() {
        return src;
    }

    public void setSrc(File src) {
        this.src = src;
    }

    public void readFile(){
        try {
            JsonArray json = new Gson().fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), JsonArray.class);
            patterns = new ArrayList<>();
            setPatterns(json);

        } catch (IOException e) {
            patterns = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void toFile(){
        try {
            FileUtils.writeStringToFile(src, AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(patterns), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PatternList getInstance(){
        return INSTANCE;
    }
}
