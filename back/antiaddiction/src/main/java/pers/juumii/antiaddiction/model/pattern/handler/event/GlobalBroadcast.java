package pers.juumii.antiaddiction.model.pattern.handler.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.juumii.antiaddiction.model.pattern.handler.handler.BehaviorHandler;
import pers.juumii.antiaddiction.model.pattern.handler.handler.HandlerList;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GlobalBroadcast {

    @Autowired
    private HandlerList handlerList;
    private List<EventListener> listenerList;

    public List<EventListener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<EventListener> listenerList) {
        this.listenerList = listenerList;
    }

    @PostConstruct
    public void init(){
        //set用于去重
        Set<EventListener> eventListenerSet = new HashSet<>();
        listenerList = new ArrayList<>();
        for(BehaviorHandler handler: handlerList.getHandlerList())
            eventListenerSet.addAll(handler.getListenerList());
        listenerList.addAll(eventListenerSet);
    }

    public void onAction(Event event) {
        for(EventListener listener: listenerList)
            listener.onAction(event);
    }

}
