package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class MaxAttendeeCountExceededEvent {

    public final EventId eventId;
    public final int maxAttendeeCount;

    public MaxAttendeeCountExceededEvent(EventId eventId, int maxAttendeeCount) {
        this.eventId = eventId;
        this.maxAttendeeCount = maxAttendeeCount;
    }
}
