package agh.bit.eventsbc.domain.event.commands;

import agh.bit.eventsbc.domain.event.Attendee;
import agh.bit.eventsbc.domain.event.Event;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;

public class SignForEventCommand {

    public final Attendee attendee;
    public final EventId eventId;

    public SignForEventCommand(Attendee attendee, EventId eventId) {
        this.attendee = attendee;
        this.eventId = eventId;
    }


}
