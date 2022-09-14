package pers.juumii.antiaddiction.model.pattern.handler.event;


public class Event {
    private Object source;
    private EventType eventType;

    public Event(Object source, EventType eventType) {
        this.source = source;
        this.eventType = eventType;
    }

    public Event(EventType eventType) {
        this.eventType = eventType;
    }

    public Event() {
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
}
