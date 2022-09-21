package pers.juumii.antiaddiction.model.pattern.handler.event;

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

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Event))
            return false;
        if(!((Event)obj).getEventType().equals(eventType))
            return false;
        if(((Event) obj).getSource() == null && source == null)
            return true;
        if(((Event) obj).getSource() == null || source == null)
            return false;
        if(((Event) obj).getSource().equals(source))
            return true;
        return false;
    }
}
