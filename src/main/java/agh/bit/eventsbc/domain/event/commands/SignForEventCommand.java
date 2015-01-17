package agh.bit.eventsbc.domain.event.commands;

import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class SignForEventCommand {

    @TargetAggregateIdentifier
    public final EventId eventId;

    public final AttendeeId attendeeId;
    public final String email;
    public final String firstName;
    public final String lastName;

    public SignForEventCommand(EventId eventId, AttendeeId attendeeId, String email, String firstName, String lastName) {
        this.eventId = eventId;
        this.attendeeId = attendeeId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
