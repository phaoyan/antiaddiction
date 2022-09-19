package pers.juumii.antiaddiction.model.pattern.handler.event;

import pers.juumii.antiaddiction.model.pattern.handler.impl.ImplUnit;

public class EventListener{
    public final String className = getClass().getName();
    public final String simplifiedName = "event listener";

    private Event accept;
    private ImplUnit implUnit;

    public EventListener() {}
    public EventListener(Event accept) {
        this.accept = accept;
    }

    public EventListener(ImplUnit implUnit) {
        this.implUnit = implUnit;
    }

    public EventListener(Event accept, ImplUnit implUnit) {
        this.accept = accept;
        this.implUnit = implUnit;
    }

    public Event getAccept() {
        return accept;
    }

    public void setAccept(Event accept) {
        this.accept = accept;
    }

    public ImplUnit getImplUnit() {
        return implUnit;
    }

    public void setImplUnit(ImplUnit implUnit) {
        this.implUnit = implUnit;
    }

    public String getClassName(){
        return className;
    }
    public String getSimplifiedName(){
        return simplifiedName;
    }
    public void onAction(Event event){
        if(event.equals(this.accept))
            implUnit.handle();
    }
}
