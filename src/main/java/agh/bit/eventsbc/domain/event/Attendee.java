package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class Attendee extends AbstractAnnotatedEntity {

    private AttendeeId attendeeId;
    private String email;
    private String firstname;
    private String lastname;

    public Attendee(AttendeeId attendeeId, String email, String firstname, String lastname) {
        this.attendeeId = attendeeId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public AttendeeId getId() {
        return attendeeId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendee attendee = (Attendee) o;

        if (attendeeId != null ? !attendeeId.equals(attendee.attendeeId) : attendee.attendeeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return attendeeId != null ? attendeeId.hashCode() : 0;
    }
}
