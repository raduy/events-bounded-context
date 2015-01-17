package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class AttendeeAlreadySignedForEvent {

    public final AttendeeId attendeeId;
    public final EventId eventId;

    public AttendeeAlreadySignedForEvent(AttendeeId attendeeId, EventId eventId) {
        this.attendeeId = attendeeId;
        this.eventId = eventId;
    }
}
