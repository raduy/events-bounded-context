package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class AttendeeListClosedEvent {
    public final EventId eventId;

    public AttendeeListClosedEvent( EventId eventId ) {
        this.eventId = eventId;
    }
}
