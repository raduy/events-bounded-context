package agh.bit.eventsbc.domain.event.factories;

import agh.bit.eventsbc.domain.event.Attendee;
import agh.bit.eventsbc.domain.event.commands.SignForEventCommand;
import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;

import java.util.UUID;


public class AttendeeFactory {

    public static Attendee create(AttendeeId attendeeId, String email, String firstName, String lastName) {

        return new Attendee(attendeeId, email, firstName, lastName);
    }

    private static AttendeeId generateAttendeeId() {
        return new AttendeeId(UUID.randomUUID().getMostSignificantBits());
    }

    public static Attendee create(SignForEventCommand command) {
        return new Attendee(command.attendeeId, command.email, command.firstName, command.lastName);
    }
}
