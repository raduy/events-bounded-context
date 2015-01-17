package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.attendee.Attendee;
import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.common.IdentifiedDomainAggregateRoot;
import agh.bit.eventsbc.domain.event.events.*;
import agh.bit.eventsbc.domain.event.factories.AttendeeFactory;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Event extends IdentifiedDomainAggregateRoot<EventId> {

    private String name;
    private List<Attendee> attendees = new ArrayList<>();
    //@TODO dopisać todoList
    //@TODO dopisać EventDescription
    private int maxAttendeesCount;
    //@TODO czy boolean/enum jest tutaj niezbedny?
    private boolean isGatheringFinished = false;

    private Event() {
    }

    public Event(EventId id, String name, int maxAttendeesCount) {
        apply(new EventCreatedEvent(id, name, maxAttendeesCount));
    }

    public List<Attendee> getAttendees() {
        return unmodifiableList(attendees);
    }

    public void addAttendeeToEvent(AttendeeId attendeeId, String email, String firstName, String lastName) {
        if (isGatheringFinished) {
            apply(new AttendeeGatheringAlreadyClosedEvent(id));
            return;
        }

        if (isAttendeeAlreadySignedForEvent(attendeeId)) {
            apply(new AttendeeAlreadySignedForEvent(attendeeId, this.id));
            return;
        }

        if (attendees.size() >= maxAttendeesCount) {
            apply(new MaxAttendeeCountExceededEvent(this.id, maxAttendeesCount));
            return;
        }

        Attendee attendee = null;
        try {
            attendee = AttendeeFactory.create(attendeeId, email, firstName, lastName);
            apply(new AttendeeAddedEvent(id(), attendee));
        } catch (IllegalArgumentException | NullPointerException ex) {
            apply(new InvalidAttendeeGivenEvent(id(), attendee));
        }
    }

    public void finishAttendeeGathering() {
        if (isGatheringFinished) {
            apply(new AttendeeGatheringAlreadyClosedEvent(id));
            return;
        }
        apply(new AttendeeListClosedEvent(id));
    }

    private boolean isAttendeeAlreadySignedForEvent(AttendeeId attendeeId) {
        return attendees.stream().filter(attendee -> attendee.id().equals(attendeeId))
                .findAny()
                .isPresent();
    }

    @EventSourcingHandler
    public void on(EventCreatedEvent event) {
        this.id = event.eventId;
        this.name = event.name;
        this.maxAttendeesCount = event.maxAttendeesCount;
    }

    @EventSourcingHandler
    public void on(AttendeeAddedEvent event) {
        attendees.add(event.attendee());
    }

    @EventSourcingHandler
    public void on(AttendeeListClosedEvent event) {
        this.isGatheringFinished = true;
    }
}
