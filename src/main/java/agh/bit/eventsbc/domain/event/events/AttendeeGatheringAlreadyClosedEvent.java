package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class AttendeeGatheringAlreadyClosedEvent {
    public final EventId eventId;

    public AttendeeGatheringAlreadyClosedEvent(EventId eventId) {
        this.eventId = eventId;
    }
}
