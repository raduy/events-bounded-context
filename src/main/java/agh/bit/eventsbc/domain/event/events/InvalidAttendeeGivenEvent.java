package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.Attendee;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class InvalidAttendeeGivenEvent {

    private final EventId eventId;
    private final Attendee attendee;


    public InvalidAttendeeGivenEvent(EventId eventId, Attendee attendee) {
        this.eventId = eventId;
        this.attendee = attendee;
    }
}
