package agh.bit.eventsbc.domain.event.events;

import agh.bit.eventsbc.domain.event.Attendee;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class AttendeeAddedEvent {

    private final EventId eventId;
    private final Attendee attendee;

}
