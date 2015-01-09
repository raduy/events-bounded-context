package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.events.*;
import agh.bit.eventsbc.domain.event.factories.AttendeeFactory;
import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Event extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private EventId eventId;
    private String name;

    @EventSourcedMember
    private List<Attendee> attendees = new LinkedList<>();
    //@TODO dopisać todoList
    //@TODO dopisać EventDescription
    private int maxAttendeesCount;
    //@TODO czy boolean/enum jest tutaj niezbedny?
    private boolean isGatheringFinished = false;

    private Event() {
    }

    public Event(EventId eventId, String name, int maxAttendeesCount) {
        apply( new EventCreatedEvent( eventId, name, maxAttendeesCount ));
    }

    public List<Attendee> getAttendees() {
        return Collections.unmodifiableList( attendees );
    }

    public void addAttendeeToEvent( AttendeeId attendeeId, String email, String firstname, String lastname ) {

        if(isGatheringFinished) {
            apply( new AttendeeGatheringAlreadyClosedEvent( eventId ) );
            return;
        }

        if( isAttendeeAlreadySignedForEvent( attendeeId ) ) {
            apply( new AttendeeAlreadySignedForEvent( attendeeId, this.eventId ));
            return;
        }

        if( attendees.size() >= maxAttendeesCount ) {
            apply( new MaxAttendeeCountExceededEvent( this.eventId, maxAttendeesCount ));
            return;
        }

        if( !AttendeeFactory.isEmailAddressValid( email )) {
            apply( new EmailAddressInvalidEvent( email, attendeeId ));
            return;
        }

        apply( new AttendeeAddedEvent( this.eventId, attendeeId, email, firstname, lastname ));
    }

    public void finishAttendeeGathering( ) {
        if(isGatheringFinished) {
            apply( new AttendeeGatheringAlreadyClosedEvent( eventId ) );
            return;
        }
        apply( new AttendeeListClosedEvent( eventId ));
    }

    private boolean isAttendeeAlreadySignedForEvent( AttendeeId attendeeId ) {

        return attendees.stream().filter( attendee -> attendee.getId().equals( attendeeId ))
                .findAny()
                .isPresent();
    }

    @EventSourcingHandler
    public void on( EventCreatedEvent event ) {
        this.eventId = event.eventId;
        this.name = event.name;
        this.maxAttendeesCount = event.maxAttendeesCount;
    }

    @EventSourcingHandler
    public void on( AttendeeAddedEvent event ) {
        Attendee attendee = AttendeeFactory.create(event.attendeeId, event.email, event.firstname, event.lastname);
        attendees.add( attendee );
    }

    @EventSourcingHandler
    public void on( AttendeeListClosedEvent event ) {
        this.isGatheringFinished = true;
    }
}
