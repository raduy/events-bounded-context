package agh.bit.eventsbc.domain.event.factories;

import agh.bit.eventsbc.domain.attendee.Attendee;
import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.event.commands.SignForEventCommand;


public class AttendeeFactory {

    public static Attendee create(AttendeeId attendeeId, String email, String firstName, String lastName) {
        return new Attendee(attendeeId, email, firstName, lastName);
    }

    public static Attendee create(SignForEventCommand command) {
        return new Attendee(command.attendeeId, command.email, command.firstName, command.lastName);
    }
}
