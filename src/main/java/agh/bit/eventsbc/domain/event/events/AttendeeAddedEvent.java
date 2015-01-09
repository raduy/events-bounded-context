package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class AttendeeAddedEvent {

    public final EventId eventId;
    public final AttendeeId attendeeId;
    public final String email;
    public final String firstname;
    public final String lastname;

    public AttendeeAddedEvent(EventId eventId, AttendeeId attendeeId, String email, String firstname, String lastname) {
        this.eventId = eventId;
        this.attendeeId = attendeeId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
