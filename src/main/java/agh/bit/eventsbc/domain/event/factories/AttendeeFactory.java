package agh.bit.eventsbc.domain.event.factories;

import agh.bit.eventsbc.domain.event.Attendee;
import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;

import java.util.UUID;
import java.util.regex.Pattern;


public class AttendeeFactory {

    private static final String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static Attendee createAttendee( AttendeeId attendeeId, String email, String firstname, String lastname ) {

        return new Attendee( attendeeId, email, firstname, lastname );
    }

    public static Attendee createAttendee( String email, String firstname, String lastname ) {

        AttendeeId attendeeId = generateAttendeeId();

        return createAttendee( attendeeId, email, firstname, lastname );
    }

    private static AttendeeId generateAttendeeId() {
        return new AttendeeId( UUID.randomUUID().getMostSignificantBits() );
    }

    public static boolean isEmailAddressValid( String email ) {

        Pattern emailPattern = Pattern.compile( EMAIL_REGEX );
        return emailPattern.matcher( email ).matches();
    }

}
