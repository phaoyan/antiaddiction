package pers.juumii.antiaddiction.model.pattern.handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.pattern.handler.event.Event;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventType;
import pers.juumii.antiaddiction.model.util.AdaptedGsonProvider;
import pers.juumii.antiaddiction.model.util.Paths;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalListener{

    @Autowired
    private Paths paths;
    private List<EventListener> listenerList;

    @PostConstruct
    public void init(){
        readFile();
    }

    public List<EventListener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<EventListener> listenerList) {
        this.listenerList = listenerList;
        toFile();
    }

    public void addListener(EventListener listener){
        listenerList.add(listener);
        Event createEvent = new Event(listener, EventType.CreateEvent);
        onAction(createEvent);
        toFile();
    }

    public void removeListener(EventListener listener){
        Event deleteEvent = new Event(listener, EventType.DeleteEvent);
        onAction(deleteEvent);
        listenerList.remove(listener);
        toFile();
    }

    public void removeListener(int index){
        removeListener(listenerList.get(index));
    }

    public void toFile(){
        try {
            File src = new File(paths.getGlobalListenerSrc());
            if(!src.exists())
                src.createNewFile();
            Gson gson = AdaptedGsonProvider.getGsonWithSerializeAdapter();
            FileUtils.writeStringToFile(src, gson.toJson(listenerList),StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readFile(){
        try {
            listenerList = new ArrayList<>();
            File src = new File(paths.getGlobalListenerSrc());
            if(!src.exists())
                src.createNewFile();
            String jsonString = FileUtils.readFileToString(src, StandardCharsets.UTF_8);
            Gson gson = AdaptedGsonProvider.getGsonWithDeserializeAdapter();
            JsonArray jsonList = gson.fromJson(jsonString, JsonArray.class);
            if(jsonList != null)
                for(JsonElement e: jsonList)
                    listenerList.add(gson.fromJson(e, EventListener.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onAction(Event event) {
        for(EventListener listener: listenerList)
            listener.onAction(event);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\coding\\projects\\applicationProjects\\antiaddiction\\back\\antiaddiction\\src\\main\\java\\pers\\juumii\\antiaddiction\\model\\pattern\\handler\\test.bat");
        file.createNewFile();
        FileUtils.writeStringToFile(file, "start \" \" \"D:\\games\\Genshin Impact\\launcher.exe\"", StandardCharsets.UTF_8);
        Runtime.getRuntime().exec("D:\\coding\\projects\\applicationProjects\\antiaddiction\\back\\antiaddiction\\src\\main\\java\\pers\\juumii\\antiaddiction\\model\\pattern\\handler\\test.bat");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        file.delete();
    }
}
