package agh.bit.eventsbc.domain.event.commands;

import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class SignForEventCommand {

    public final AttendeeId attendeeId;
    public final String email;
    public final String firstname;
    public final String lastname;
    @TargetAggregateIdentifier
    public final EventId eventId;

    public SignForEventCommand(EventId eventId, AttendeeId attendeeId, String email, String firstname, String lastname) {
        this.eventId = eventId;
        this.attendeeId = attendeeId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
