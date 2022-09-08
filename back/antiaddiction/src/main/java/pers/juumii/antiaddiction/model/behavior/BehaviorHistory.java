package pers.juumii.antiaddiction.model.behavior;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BehaviorHistory{
    private ArrayList<TimedBehavior> history;
    private LocalDateTime startTime, endTime;
    private String src;

    public void readFile(){
        if(src == null)
            return;
        File srcFile = new File(src);
        try {
            Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
            JsonObject json = gson.fromJson(FileUtils.readFileToString(srcFile, StandardCharsets.UTF_8), JsonObject.class);
            if(json == null){
                startTime = LocalDateTime.now();
                endTime = startTime;
                history = new ArrayList<>();
            }else{
                startTime = gson.fromJson(json.get("startTime"),LocalDateTime.class);
                endTime = gson.fromJson(json.get("endTime"), LocalDateTime.class);
                history = new ArrayList<>();
                JsonArray jsonHistory = json.get("history").getAsJsonArray();
                for(JsonElement behavior: jsonHistory)
                    history.add(gson.fromJson(behavior, TimedBehavior.class));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toFile(){
        try {
            FileUtils.writeStringToFile(new File(src), AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(this), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setHistory(ArrayList<TimedBehavior> history) {
        this.history = history;
    }
    public ArrayList<TimedBehavior> getHistory() {
        return history;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
        File srcFile = new File(src);
        if(!srcFile.exists()) {
            try {
                srcFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void append(TimedBehavior behavior){
        if(history == null){
            history = new ArrayList<>();
        }
        if(behavior == null)
            return;
        if(history.size() != 0 && behavior.getStartTime().equals(history.get(history.size()-1).getStartTime()))
            history.remove(history.size()-1);
        history.add(behavior);
        update();
    }

    private void updateTime(){
        setStartTime(history.get(0).getStartTime());
        setEndTime(history.get(history.size() - 1).getEndTime());
    }

    public void update(){
        updateTime();
        toFile();
    }
}
