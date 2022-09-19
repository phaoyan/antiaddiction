package pers.juumii.antiaddiction.model.pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.model.pattern.pattern.BehaviorPattern;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Repository
public class PatternList {
    private ArrayList<BehaviorPattern> patterns;

    @PostConstruct
    public void init(){
        readFile();
    }
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

    public void readFile(){
        try {
            JsonArray json = new Gson().fromJson(FileUtils.readFileToString(new File(Paths.getPatternListSrc()), StandardCharsets.UTF_8), JsonArray.class);
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
            FileUtils.writeStringToFile(new File(Paths.getPatternListSrc()), AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(patterns), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
