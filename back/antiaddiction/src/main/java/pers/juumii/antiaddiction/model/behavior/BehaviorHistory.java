package pers.juumii.antiaddiction.model.behavior;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class BehaviorHistory{
    private ArrayList<TimedBehavior> history;
    private LocalDateTime startTime, endTime;
    @Autowired
    private Paths paths;

    @PostConstruct
    public void init(){
        readFile();
    }

    public void readFile(){
        try {
            File src = new File(paths.getBehaviorHistoryRoot() + "/"  + LocalDate.now() + ".json");
            if(!src.exists())
                src.createNewFile();
            Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
            JsonObject json = gson.fromJson(FileUtils.readFileToString(src, StandardCharsets.UTF_8), JsonObject.class);
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
            FileUtils.writeStringToFile(new File(paths.getBehaviorHistoryRoot() + "/"  + LocalDate.now() + ".json"), AdaptedGsonProvider.getGsonWithSerializeAdapter().toJson(this), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public TimedBehavior getLastTimedBehavior(){
        return history.get(history.size()-1);
    }
    public MomentaryBehavior getLastMomentaryBehavior(){
        TimedBehavior lastTimedBehavior = getLastTimedBehavior();
        return lastTimedBehavior.getDetails().get(lastTimedBehavior.getDetails().size()-1);
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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

    public void update(){
        setStartTime(history.get(0).getStartTime());
        setEndTime(history.get(history.size() - 1).getEndTime());
        toFile();
    }

    public ArrayList<TimedBehavior> getHistory() {
        return history;
    }
}
