package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class EventCreatedEvent {

    public final EventId eventId;
    public final String name;
    public final int maxAttendeesCount;

    public EventCreatedEvent(EventId eventId, String name, int maxAttendeesCount) {
        this.eventId = eventId;
        this.name = name;
        this.maxAttendeesCount = maxAttendeesCount;
    }
}
