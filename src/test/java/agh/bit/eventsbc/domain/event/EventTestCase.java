package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.attendee.Attendee;
import agh.bit.eventsbc.domain.attendee.AttendeeId;
import agh.bit.eventsbc.domain.event.commands.CreateEventCommand;
import agh.bit.eventsbc.domain.event.commands.FinishAttendeeGatheringCommand;
import agh.bit.eventsbc.domain.event.commands.SignForEventCommand;
import agh.bit.eventsbc.domain.event.events.*;
import agh.bit.eventsbc.domain.event.factories.AttendeeFactory;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.junit.Test;

public class EventTestCase extends EventPreconfiguredTestCase {
    private final EventId eventId = new EventId();
    private final String EVENT_NAME = "ddd-workshop";
    private final Integer MAX_ATTENDEES_COUNT = 10;

    private final Attendee attendee = AttendeeFactory.create("jan.kowalski@domain.com", "Jan", "Kowalski");
    private final AttendeeId attendeeId = attendee.id();

    @Test
    public void thatCreateEventCommandCreatesNewEventAggregate() throws Exception {
        fixture
                .given()
                .when(
                        new CreateEventCommand(eventId, "Evt desc", MAX_ATTENDEES_COUNT)
                )
                .expectEvents(
                        new EventCreatedEvent(eventId, "Evt desc", MAX_ATTENDEES_COUNT)
                );
    }

    @Test
    public void thatSignForEventCommandAddsNewAttendeeToEventAggregate() throws Exception {
        fixture
                .given(
                        new EventCreatedEvent(eventId, EVENT_NAME, MAX_ATTENDEES_COUNT)
                )
                .when(
                        new SignForEventCommand(eventId, attendeeId, "jan.kowalski@domain.com", "Jan", "Kowalski")
                )
                .expectEvents(
                        new AttendeeAddedEvent(eventId, attendee)
                );
    }

    @Test
    public void thatEventAggregateDoesNotAllowDuplicateAttendees() throws Exception {
        fixture
                .given(
                        new EventCreatedEvent(eventId, EVENT_NAME, MAX_ATTENDEES_COUNT),
                        new AttendeeAddedEvent(eventId, attendee)
                )
                .when(
                        new SignForEventCommand(eventId, attendeeId, "jan.kowalski@domain.com", "Jan", "Kowalski")
                )
                .expectEvents(
                        new AttendeeAlreadySignedForEvent(attendeeId, eventId)
                );
    }

    @Test
    public void thatEventAggregateDoesNotAllowTooManyAttendees() throws Exception {
        int MAX_ATTENDEES_COUNT = 1;
        fixture
                .given(
                        new EventCreatedEvent(eventId, EVENT_NAME, MAX_ATTENDEES_COUNT),
                        new AttendeeAddedEvent(eventId, attendee)
                )
                .when(
                        new SignForEventCommand(eventId, new AttendeeId(), "anna.kowalska@domain.com", "Anna", "Kowalska")
                )
                .expectEvents(
                        new MaxAttendeeCountExceededEvent(eventId, MAX_ATTENDEES_COUNT)
                );
    }

    @Test
    public void thatEventAggregateDoesNotAllowSigningAttendeesInAlreadyClosedEvent() throws Exception {
        final int MAX_ATTENDEES_COUNT = 1;
        fixture
                .given(
                        new EventCreatedEvent(eventId, EVENT_NAME, MAX_ATTENDEES_COUNT),
                        new AttendeeListClosedEvent(eventId)
                )
                .when(
                        new SignForEventCommand(eventId, new AttendeeId(), "anna.kowalska@domain.com", "Anna", "Kowalska")
                )
                .expectEvents(
                        new AttendeeGatheringAlreadyClosedEvent(eventId)
                );
    }

    @Test
    public void thatFinishAttendeeGatheringCommandFinishesGatheringIfPossible() throws Exception {
        int MAX_ATTENDEES_COUNT = 1;
        fixture
                .given(
                        new EventCreatedEvent(eventId, EVENT_NAME, MAX_ATTENDEES_COUNT),
                        new AttendeeAddedEvent(eventId, attendee)
                )
                .when(
                        new FinishAttendeeGatheringCommand(eventId)
                )
                .expectEvents(
                        new AttendeeListClosedEvent(eventId)
                );
    }
}
