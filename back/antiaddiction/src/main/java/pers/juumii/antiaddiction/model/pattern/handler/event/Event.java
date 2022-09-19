package pers.juumii.antiaddiction.model.pattern.handler.event;


import com.google.gson.JsonElement;

public class Event {
    private Object source;
    private EventType eventType;

    public Event(Object source, EventType eventType) {
        this.source = source;
        this.eventType = eventType;
    }

    public Event(Object source) {
        this.source = source;
    }

    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    public Object getSource() {
        return source;
    }
    public void setSource(Object source) {
        this.source = source;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Event
                && (((Event)obj).getEventType() == null || ((Event)obj).getEventType().equals(eventType) )
                && (((Event) obj).getSource() == null || ((Event) obj).getSource().equals(source));
    }
}
