package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;

public class EmailAddressInvalidEvent {

    public final String emailAddress;
    public final AttendeeId attendeeId;

    public EmailAddressInvalidEvent(String emailAddress, AttendeeId attendeeId) {
        this.emailAddress = emailAddress;
        this.attendeeId = attendeeId;
    }
}
