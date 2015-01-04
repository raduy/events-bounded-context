package agh.bit.eventsbc.domain.event;

import agh.bit.eventsbc.domain.event.commands.CreateEventCommand;
import agh.bit.eventsbc.domain.event.commands.FinishAttendeeGatheringCommand;
import agh.bit.eventsbc.domain.event.commands.SignForEventCommand;
import agh.bit.eventsbc.domain.event.events.*;
import agh.bit.eventsbc.domain.event.valueobjects.AttendeeId;
import agh.bit.eventsbc.domain.event.valueobjects.EventId;
import org.junit.Test;

public class EventTestCase extends EventPreconfiguredTestCase {
    private final EventId eventId = EventId.valueOf( 1L );
    private final String EVENT_NAME = "ddd-workshop";
    private final Integer MAX_ATTENDEES_COUNT = 10;

    @Test
    public void thatCreateEventCommandCreatesNewEventAggregate( ) throws Exception {
        fixture
                .given( )
                .when(
                        new CreateEventCommand( EventId.valueOf( 1L ), "Evt desc", 10 )
                )
                .expectEvents(
                        new EventCreatedEvent( EventId.valueOf( 1L ), "Evt desc", 10 )
                );
    }

    @Test
    public void thatSignForEventCommandAddsNewAttendeeToEventAggregate( ) throws Exception {
        fixture
                .given(
                        new EventCreatedEvent( eventId, EVENT_NAME, MAX_ATTENDEES_COUNT )
                )
                .when(
                        new SignForEventCommand( eventId, AttendeeId.valueOf(2L), "jan.kowalski@domain.com", "Jan", "Kowalski" )
                )
                .expectEvents(
                        new AttendeeAddedEvent( eventId, AttendeeId.valueOf(2L), "jan.kowalski@domain.com", "Jan", "Kowalski" )
                );
    }

    @Test
    public void thatEventAggregateDoesNotAllowDuplicateAttendees( ) throws Exception {
        fixture
                .given(
                        new EventCreatedEvent( eventId, EVENT_NAME, MAX_ATTENDEES_COUNT ),
                        new AttendeeAddedEvent( eventId, AttendeeId.valueOf(2L), "jan.kowalski@domain.com", "Jan", "Kowalski" )
                )
                .when(
                        new SignForEventCommand( eventId, AttendeeId.valueOf(2L), "jan.kowalski@domain.com", "Jan", "Kowalski" )
                )
                .expectEvents(
                        new AttendeeAlreadySignedForEvent( AttendeeId.valueOf(2L), eventId )
                );
    }

    @Test
    public void thatEventAggregateDoesNotAllowTooManyAttendees( ) throws Exception {
        fixture
                .given(
                        new EventCreatedEvent( eventId, EVENT_NAME, 1),
                        new AttendeeAddedEvent( eventId, AttendeeId.valueOf(2L), "jan.kowalski@domain.com", "Jan", "Kowalski" )
                )
                .when(
                        new SignForEventCommand( eventId, AttendeeId.valueOf(3L), "anna.kowalska@domain.com", "Anna", "Kowalska" )
                )
                .expectEvents(
                        new MaxAttendeeCountExceededEvent( eventId, 1 )
                );
    }

    @Test
    public void thatEventAggregateDoesNotAllowSigningAttendeesInAlreadyClosedEvent( ) throws Exception {
        fixture
                .given(
                        new EventCreatedEvent( eventId, EVENT_NAME, 1),
                        new AttendeeListClosedEvent( eventId )
                )
                .when(
                        new SignForEventCommand( eventId, AttendeeId.valueOf(3L), "anna.kowalska@domain.com", "Anna", "Kowalska" )
                )
                .expectEvents(
                        new AttendeeGatheringAlreadyClosedEvent( eventId )
                );
    }

    @Test
    public void thatFinishAttendeeGatheringCommandFinishesGatheringIfPossible( ) throws Exception {
        fixture
                .given(
                        new EventCreatedEvent( eventId, EVENT_NAME, 1),
                        new AttendeeAddedEvent( eventId, AttendeeId.valueOf(2L), "jan.kowalski@domain.com", "Jan", "Kowalski" )
                )
                .when(
                        new FinishAttendeeGatheringCommand( eventId )
                )
                .expectEvents(
                        new AttendeeListClosedEvent( eventId )
                );
    }

}
