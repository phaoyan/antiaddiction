package pers.juumii.antiaddiction.model.pattern.handler.event;

import pers.juumii.antiaddiction.model.pattern.handler.impl.BehaviorHandler;

public class EventListener{
    public final String className = getClass().getName();
    public final String simplifiedName = "event listener";

    private EventType accept;
    private BehaviorHandler handler;

    public EventListener() {
    }

    public EventListener(EventType accept) {
        this.accept = accept;
    }

    public EventListener(BehaviorHandler handler) {
        this.handler = handler;
    }

    public EventListener(EventType accept, BehaviorHandler handler) {
        this.accept = accept;
        this.handler = handler;
    }

    public EventType getAccept() {
        return accept;
    }

    public void setAccept(EventType accept) {
        this.accept = accept;
    }

    public BehaviorHandler getHandler() {
        return handler;
    }

    public void setHandler(BehaviorHandler handler) {
        this.handler = handler;
    }

    public String getClassName(){
        return className;
    }
    public String getSimplifiedName(){
        return simplifiedName;
    }
    public void onAction(Event event){
        if(event.getEventType().equals(this.accept))
            handler.handle(event);
    }
}
