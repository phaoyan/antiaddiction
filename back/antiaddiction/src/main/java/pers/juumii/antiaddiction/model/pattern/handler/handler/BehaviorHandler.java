package pers.juumii.antiaddiction.model.pattern.handler.handler;

import com.google.gson.JsonArray;
import pers.juumii.antiaddiction.model.pattern.handler.event.EventListener;

import java.util.ArrayList;
import java.util.List;

public class BehaviorHandler {

    private String name;
    private JsonArray desc;
    private String material;
    private List<EventListener> listenerList;

    public BehaviorHandler(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonArray getDesc() {
        return desc;
    }

    public void setDesc(JsonArray desc) {
        this.desc = desc;
    }
    public void setMaterial(String jsonString) {
        this.material = jsonString;
    }

    public String getMaterial() {
        return material;
    }

    public List<EventListener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<EventListener> listenerList) {
        this.listenerList = listenerList;
    }

    public void addListener(EventListener listener){
        if(listenerList == null)
            listenerList = new ArrayList<>();
        listenerList.add(listener);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BehaviorHandler && ((BehaviorHandler)obj).material.equals(material);
    }
}
